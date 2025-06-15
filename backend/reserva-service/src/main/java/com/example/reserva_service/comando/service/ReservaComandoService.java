package com.example.reserva_service.comando.service;

import com.example.reserva_service.comando.dto.ReservaComandoDTO;
import com.example.reserva_service.comando.enums.EstadoReservaComando;
import com.example.reserva_service.comando.model.ReservaComando;
import com.example.reserva_service.comando.repository.ReservaComandoRepository;
import com.example.reserva_service.events.EventProducer;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Random;

@Service
public class ReservaComandoService {
    @Autowired
    private ReservaComandoRepository repository;

    @Autowired
    private EventProducer eventProducer;

    @Transactional("commandTransactionManager")
    public ReservaComando criarReserva(ReservaComandoDTO dto) {
        ReservaComando reserva = new ReservaComando();
        reserva.setCodigo(gerarCodigoReserva());
        reserva.setCodigoCliente(dto.getCodigo_cliente());
        reserva.setCodigoVoo(dto.getCodigo_voo());
        reserva.setValor(dto.getValor());
        reserva.setMilhasUtilizadas(dto.getMilhas_utilizadas());
        reserva.setQuantidadePoltronas(dto.getQuantidade_poltronas());
        reserva.setDataReserva(Instant.now());
        reserva.setEstado(EstadoReservaComando.CRIADA); // R07: Reserva inicia como CRIADA

        ReservaComando novaReserva = repository.save(reserva);

        // TODO: SAGA - O ideal seria salvar como PENDENTE,
        //  iniciar a SAGA (debita milhas, ocupa assento) e, se sucesso,
        //  mudar para CRIADA. Se falha, muda para FALHA.

        // Publica o evento para o lado de consulta (e outros serviços interessados)
        //eventProducer.sendReservaCriadaEvent(novaReserva);
        eventProducer.sendEvent("reserva.criada", novaReserva);

        return novaReserva;
    }

    @Transactional("commandTransactionManager")
    public ReservaComando cancelarReserva(String codigoReserva) {
        // R08: Cancelar Reserva
        ReservaComando reserva = repository.findById(codigoReserva)
                .orElseThrow(() -> new EntityNotFoundException("Reserva não encontrada com o código: " + codigoReserva));

        EstadoReservaComando estadoAtual = reserva.getEstado();
        // Uma reserva pode ser cancelada se está CRIADA ou em CHECK-IN 
        if (estadoAtual != EstadoReservaComando.CRIADA && estadoAtual != EstadoReservaComando.CHECK_IN) {
            throw new IllegalStateException("A reserva não pode ser cancelada no estado atual: " + estadoAtual);
        }

        reserva.setEstado(EstadoReservaComando.CANCELADA);
        ReservaComando reservaCancelada = repository.save(reserva);

        // Publica o evento para que outros serviços (como o de Cliente, para estornar as milhas) possam reagir
        eventProducer.sendEvent("reserva.cancelada", reservaCancelada);

        return reservaCancelada;
    }

    @Transactional("commandTransactionManager")
    public ReservaComando alterarEstado(String codigoReserva, String novoEstadoStr) {
        ReservaComando reserva = repository.findById(codigoReserva)
                .orElseThrow(() -> new EntityNotFoundException("Reserva não encontrada com o código: " + codigoReserva));

        EstadoReservaComando estadoAtual = reserva.getEstado();
        EstadoReservaComando novoEstado;
        try {
            novoEstado = EstadoReservaComando.valueOf(novoEstadoStr.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Estado '" + novoEstadoStr + "' é inválido.");
        }

        // Valida as transições de estado permitidas
        switch (novoEstado) {
            case CHECK_IN:
                // R10: Fazer Check-in. Apenas do estado CRIADA
                if (estadoAtual != EstadoReservaComando.CRIADA) {
                    throw new IllegalStateException("Não é possível fazer check-in de uma reserva que não está no estado CRIADA.");
                }
                // A regra de negócio das 48h  deve ser validada antes de chamar este método,
                // geralmente no front-end ou no API Gateway, pois este MS não possui os dados do voo.
                break;

            case EMBARCADA:
                // R12: Confirmação de Embarque. Apenas do estado CHECK-IN 
                if (estadoAtual != EstadoReservaComando.CHECK_IN) {
                    throw new IllegalStateException("Não é possível confirmar o embarque de uma reserva que não está no estado CHECK-IN.");
                }
                break;

            default:
                throw new IllegalStateException("A transição de estado para '" + novoEstado + "' não é permitida por esta operação.");
        }

        reserva.setEstado(novoEstado);
        ReservaComando reservaAtualizada = repository.save(reserva);

        // Publica o evento de alteração para o lado de consulta
        eventProducer.sendEvent("reserva.estadoalterado", reservaAtualizada);

        return reservaAtualizada;
    }

    private String gerarCodigoReserva() {
        // R98: Código com 3 letras e 3 números
        String letras = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 3; i++) {
            sb.append(letras.charAt(random.nextInt(letras.length())));
        }
        for (int i = 0; i < 3; i++) {
            sb.append(random.nextInt(10));
        }
        return sb.toString();
    }
}