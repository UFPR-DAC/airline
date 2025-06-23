package com.example.client_service.rabbit;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
    @Bean
    public Queue clienteAuthLookupQueue() {
        return new Queue("cliente.auth.lookup", true);
    }
}
