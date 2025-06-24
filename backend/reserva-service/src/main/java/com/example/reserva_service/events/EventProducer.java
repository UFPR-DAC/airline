package com.example.reserva_service.events;

import com.example.reserva_service.client.dto.VooDTO;
import com.example.reserva_service.comando.model.ReservaComando;
//import com.example.reserva_service.comando.model.ReservaComando;
import com.example.reserva_service.config.RabbitMQConfig;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Map;

@Service
public class EventProducer {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private ObjectMapper objectMapper;


    public void sendEventComVoo(String routingKey, ReservaComando reserva, VooDTO voo) {
        Map<String, Object> payload = Map.of(
            "reserva", reserva,
            "voo", voo
        );
        sendEvent(routingKey, payload);
    }

    public void sendEvent(String routingKey, Object payload) {
        try {
            String jsonPayload = objectMapper.writeValueAsString(payload);
            rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE_NAME, routingKey, jsonPayload);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

}