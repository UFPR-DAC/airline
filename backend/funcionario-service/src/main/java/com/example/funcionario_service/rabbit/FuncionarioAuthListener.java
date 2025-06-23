package com.example.funcionario_service.rabbit;

import com.example.funcionario_service.dto.FuncionarioAuthDTO;
import com.example.funcionario_service.messaging.UserLookupRequest;
import com.example.funcionario_service.repository.FuncionarioRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class FuncionarioAuthListener {
    private final FuncionarioRepository funcionarioRepository;

    public FuncionarioAuthListener(FuncionarioRepository funcionarioRepository) {
        this.funcionarioRepository = funcionarioRepository;
    }

    @RabbitListener(queues = "funcionario.auth.lookup")
    public FuncionarioAuthDTO lookupByEmail(UserLookupRequest request) {
        return funcionarioRepository.findByEmail(request.getEmail())
                .map(FuncionarioAuthDTO::new)
                .orElse(null);
    }
}
