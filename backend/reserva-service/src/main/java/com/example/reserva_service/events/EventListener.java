package com.example.reserva_service.events;

import com.example.reserva_service.comando.model.ReservaComando;
import com.example.reserva_service.config.RabbitMQConfig;
import com.example.reserva_service.consulta.model.ReservaConsulta;
import com.example.reserva_service.consulta.repository.ReservaConsultaRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

// @Component
// public class EventListener {
//     @Autowired
//     private ReservaConsultaRepository viewRepository;

//     @Autowired
//     private ObjectMapper objectMapper;

//     @RabbitListener(queues = RabbitMQConfig.QUEUE_NAME)
//     public void handleReservaEvent(String message) {
//         try {
//             ReservaComando reservaCmd = objectMapper.readValue(message, ReservaComando.class);

//             // TODO: Chamar MS Voo para obter os dados do voo (origem, destino, etc.)
//             // TODO: Chamar MS Cliente para obter dados do cliente, se necessário.

//             // Por simplicidade, vamos preencher com dados mockados.
//             ReservaConsulta reservaView = new ReservaConsulta();
//             reservaView.setCodigoReserva(reservaCmd.getCodigo());
//             reservaView.setCodigoCliente(reservaCmd.getCodigoCliente());
//             reservaView.setDataReserva(reservaCmd.getDataReserva());
//             reservaView.setEstadoReserva(reservaCmd.getEstado().name());
//             reservaView.setValor(reservaCmd.getValor());
//             // ... preencher todos os outros campos da ReservaView ...

//             viewRepository.save(reservaView);

//         } catch (Exception e) {
//             e.printStackTrace();
//         }
//     }
// }

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
            ReservaComando reservaPayload = objectMapper.readValue(jsonPayload, ReservaComando.class);
            String codigoReserva = reservaPayload.getCodigo();

            switch (routingKey) {
                case "reserva.criada":
                    // Se o evento é de criação, criamos uma nova entrada na view de leitura.
                    ReservaConsulta novaView = new ReservaConsulta();
                    
                    // TODO: Para popular a view de forma completa, seria necessário
                    // chamar os outros microsserviços (Voo, Cliente, Aeroporto) para buscar os dados
                    // e preencher os campos desnormalizados da ReservaView.
                    // Por simplicidade, preenchemos apenas com os dados do evento.
                    
                    novaView.setCodigoReserva(reservaPayload.getCodigo());
                    novaView.setCodigoCliente(reservaPayload.getCodigoCliente());
                    novaView.setDataReserva(reservaPayload.getDataReserva());
                    novaView.setEstadoReserva(reservaPayload.getEstado().name());
                    novaView.setValor(reservaPayload.getValor());
                    novaView.setMilhasUtilizadas(reservaPayload.getMilhasUtilizadas());
                    novaView.setQuantidadePoltronas(reservaPayload.getQuantidadePoltronas());
                    novaView.setCodigoVoo(reservaPayload.getCodigoVoo());

                    viewRepository.save(novaView);
                    break;

                case "reserva.cancelada":
                case "reserva.estadoalterado":
                    // Se o evento é de alteração ou cancelamento, atualizamos uma entrada existente.
                    ReservaConsulta viewExistente = viewRepository.findById(codigoReserva)
                            .orElse(null); // Em um sistema real, logar se a view não for encontrada.

                    if (viewExistente != null) {
                        viewExistente.setEstadoReserva(reservaPayload.getEstado().name());
                        viewRepository.save(viewExistente);
                    } else {
                         System.err.println("Não foi possível encontrar a reserva(consulta) com código " + codigoReserva + " para atualizar.");
                    }
                    break;
                    
                default:
                    System.err.println("Chave de roteamento desconhecida: " + routingKey);
            }

        } catch (Exception e) {
            System.err.println("Erro ao processar a mensagem do RabbitMQ: " + e.getMessage());
            e.printStackTrace();
        }
    }
}