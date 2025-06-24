package com.example.reserva_service.events;

import com.example.reserva_service.client.dto.VooDTO;
import com.example.reserva_service.comando.model.ReservaComando;
import com.example.reserva_service.config.RabbitMQConfig;
import com.example.reserva_service.consulta.model.ReservaConsulta;
import com.example.reserva_service.consulta.repository.ReservaConsultaRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import java.util.Map;

@Component
public class EventListener {
    @Autowired
    private ReservaConsultaRepository viewRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @RabbitListener(queues = RabbitMQConfig.QUEUE_NAME)
    @Transactional("queryTransactionManager")
    public void handleReservaEvents(Message message) {
        String routingKey = message.getMessageProperties().getReceivedRoutingKey();
        String jsonPayload = new String(message.getBody());

        System.out.println("Evento recebido com a chave '" + routingKey + "': " + jsonPayload);

        try {
            switch (routingKey) {
                case "reserva.criada":
                    TypeReference<Map<String, Object>> typeRef = new TypeReference<>() {};
                    Map<String, Object> payload = objectMapper.readValue(jsonPayload, typeRef);
                    ReservaComando reservaCriada = objectMapper.convertValue(payload.get("reserva"), ReservaComando.class);
                    VooDTO vooDaReserva = objectMapper.convertValue(payload.get("voo"), VooDTO.class);

                    ReservaConsulta novaView = new ReservaConsulta();
                    
                    novaView.setCodigoReserva(reservaCriada.getCodigo());
                    novaView.setDataReserva(reservaCriada.getDataReserva());
                    novaView.setValor(reservaCriada.getValor());
                    novaView.setMilhasUtilizadas(reservaCriada.getMilhasUtilizadas());
                    novaView.setQuantidadePoltronas(reservaCriada.getQuantidadePoltronas());
                    novaView.setCodigoCliente(reservaCriada.getCodigoCliente());
                    novaView.setEstadoReserva(reservaCriada.getEstado().name());

                    novaView.setCodigoVoo(vooDaReserva.getCodigo());
                    novaView.setDataVoo(vooDaReserva.getData());
                    novaView.setValorPassagem(vooDaReserva.getValorPassagem());

                    VooDTO.AeroportoDTO origem = vooDaReserva.getAeroportoOrigem();
                    novaView.setOrigemCodigoAeroporto(origem.getCodigo());
                    novaView.setOrigemNomeAeroporto(origem.getNome());
                    novaView.setOrigemCidade(origem.getCidade());
                    novaView.setOrigemUf(origem.getUf());

                    VooDTO.AeroportoDTO destino = vooDaReserva.getAeroportoDestino();
                    novaView.setDestinoCodigoAeroporto(destino.getCodigo());
                    novaView.setDestinoNomeAeroporto(destino.getNome());
                    novaView.setDestinoCidade(destino.getCidade());
                    novaView.setDestinoUf(destino.getUf());

                    viewRepository.save(novaView);
                    break;

                case "reserva.cancelada":
                case "reserva.estado.alterado":
                    ReservaComando reservaAtualizada = objectMapper.readValue(jsonPayload, ReservaComando.class);
                    
                    ReservaConsulta viewExistente = viewRepository.findById(reservaAtualizada.getCodigo())
                            .orElse(null);

                    if (viewExistente != null) {
                        viewExistente.setEstadoReserva(reservaAtualizada.getEstado().name());
                        viewRepository.save(viewExistente);
                    } else {
                        System.err.println("ALERTA DE CONSISTÊNCIA: Recebido evento para a reserva '" +
                                reservaAtualizada.getCodigo() + "' mas a view de leitura não foi encontrada.");
                    }
                    break;

                default:
                    System.err.println("Chave de roteamento desconhecida recebida: " + routingKey);
            }

        } catch (Exception e) {
            System.err.println("Erro fatal ao processar a mensagem do RabbitMQ: " + e.getMessage());
            e.printStackTrace();
        }
    }
}