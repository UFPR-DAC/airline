package com.example.reserva_service.events;

//import com.example.reserva_service.comando.model.ReservaComando;
import com.example.reserva_service.config.RabbitMQConfig;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EventProducer {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    // public void sendReservaCriadaEvent(ReservaComando reserva) {
    //     try {
    //         String reservaJson = objectMapper.writeValueAsString(reserva);
    //         rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE_NAME, "reserva.criada", reservaJson);
    //     } catch (Exception e) {
    //         e.printStackTrace();
    //     }
    // }

    public void sendEvent(String routingKey, Object payload) {
        try {
            String jsonPayload = objectMapper.writeValueAsString(payload);
            rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE_NAME, routingKey, jsonPayload);
            System.out.println("Evento enviado com a chave '" + routingKey + "': " + jsonPayload);
        } catch (JsonProcessingException e) {
            System.err.println("Erro ao serializar o payload para JSON: " + e.getMessage());
            e.printStackTrace();
        }
    }

}