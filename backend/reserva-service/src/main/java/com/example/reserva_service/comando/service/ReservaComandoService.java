package com.example.reserva_service.comando.service;

import com.example.reserva_service.client.dto.VooDTO;
import com.example.reserva_service.client.VooClient;
import com.example.reserva_service.comando.dto.ReservaComandoDTO;
import com.example.reserva_service.comando.enums.EstadoReserva;
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
     
    @Autowired
    private VooClient vooClient;

     @Transactional("commandTransactionManager")
    public ReservaComando criarReserva(ReservaComandoDTO dto) {
        VooDTO voo = vooClient.buscarVooPorCodigo(dto.getCodigo_voo())
                .orElseThrow(() -> new IllegalArgumentException("Voo não encontrado com o código: " + dto.getCodigo_voo()));

        int assentosDisponiveis = voo.getQuantidadePoltronasTotal() - voo.getQuantidadePoltronasOcupadas();
        if (dto.getQuantidade_poltronas() > assentosDisponiveis) {
            throw new IllegalStateException("Não há assentos disponíveis suficientes para esta reserva.");
        }

        ReservaComando reserva = new ReservaComando();
        reserva.setCodigo(gerarCodigoReserva());
        reserva.setCodigoCliente(dto.getCodigo_cliente());
        reserva.setCodigoVoo(dto.getCodigo_voo());
        reserva.setValor(dto.getValor());
        reserva.setMilhasUtilizadas(dto.getMilhas_utilizadas());
        reserva.setQuantidadePoltronas(dto.getQuantidade_poltronas());
        reserva.setDataReserva(Instant.now());
        reserva.setEstado(EstadoReserva.CRIADA);
        
        ReservaComando novaReserva = repository.save(reserva);

        // TODO SAGA: A chamada para ocupar o assento no MS Voo deve ser feita aqui,
        // via mensageria, em uma transação distribuída (SAGA).

        eventProducer.sendEventComVoo("reserva.criada", novaReserva, voo);

        return novaReserva;
    }

    @Transactional("commandTransactionManager")
    public ReservaComando cancelarReserva(String codigoReserva) {
        ReservaComando reserva = repository.findById(codigoReserva)
                .orElseThrow(() -> new EntityNotFoundException("Reserva não encontrada com o código: " + codigoReserva));

        EstadoReserva estadoAtual = reserva.getEstado();

        if (estadoAtual != EstadoReserva.CRIADA && estadoAtual != EstadoReserva.CHECK_IN) {
            throw new IllegalStateException("A reserva não pode ser cancelada no estado atual: " + estadoAtual);
        }

        reserva.setEstado(EstadoReserva.CANCELADA);
        ReservaComando reservaCancelada = repository.save(reserva);

        eventProducer.sendEvent("reserva.cancelada", reservaCancelada);

        return reservaCancelada;
    }

    @Transactional("commandTransactionManager")
    public ReservaComando alterarEstado(String codigoReserva, String novoEstadoStr) {
        ReservaComando reserva = repository.findById(codigoReserva)
                .orElseThrow(() -> new EntityNotFoundException("Reserva não encontrada com o código: " + codigoReserva));

        EstadoReserva estadoAtual = reserva.getEstado();
        EstadoReserva novoEstado;
        try {
            novoEstado = EstadoReserva.valueOf(novoEstadoStr.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Estado '" + novoEstadoStr + "' é inválido.");
        }

        switch (novoEstado) {
            case CHECK_IN:
                if (estadoAtual != EstadoReserva.CRIADA) {
                    throw new IllegalStateException("Não é possível fazer check-in de uma reserva que não está no estado CRIADA.");
                }
                break;

            case EMBARCADA:
                if (estadoAtual != EstadoReserva.CHECK_IN) {
                    throw new IllegalStateException("Não é possível confirmar o embarque de uma reserva que não está no estado CHECK-IN.");
                }
                break;

            default:
                throw new IllegalStateException("A transição de estado para '" + novoEstado + "' não é permitida por esta operação.");
        }

        reserva.setEstado(novoEstado);
        ReservaComando reservaAtualizada = repository.save(reserva);

        eventProducer.sendEvent("reserva.estadoalterado", reservaAtualizada);

        return reservaAtualizada;
    }

    private String gerarCodigoReserva() {
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