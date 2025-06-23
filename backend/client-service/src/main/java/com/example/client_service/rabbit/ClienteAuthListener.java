package com.example.client_service.rabbit;

import com.example.client_service.dto.ClienteAuthDTO;
import com.example.client_service.messaging.UserLookupRequest;
import com.example.client_service.repository.ClienteRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class ClienteAuthListener {
    private final ClienteRepository clienteRepository;

    public ClienteAuthListener(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @RabbitListener(queues = "cliente.auth.lookup")
    public ClienteAuthDTO lookupByEmail(UserLookupRequest request) {
        return clienteRepository.findByEmail(request.getEmail())
                .map(ClienteAuthDTO::new)
                .orElse(null);
    }
}
