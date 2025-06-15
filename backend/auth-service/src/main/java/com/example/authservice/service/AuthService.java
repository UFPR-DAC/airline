package com.example.authservice.service;

import com.example.authservice.dto.UsuarioDTO;
import com.example.authservice.model.Customer;
import com.example.authservice.dto.AuthRequest;
import com.example.authservice.dto.AuthResponse;
import com.example.authservice.repository.UsuarioRepository;
import com.example.authservice.security.JwtService;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final JwtService jwtService;
    private final RabbitTemplate rabbitTemplate;

    private static final String FUNCIONARIO_QUEUE = "auth.funcionario";
    private static final String CLIENTE_QUEUE = "auth.cliente";

    public AuthService(JwtService jwtService, RabbitTemplate rabbitTemplate) {
        this.jwtService = jwtService;
        this.rabbitTemplate = rabbitTemplate;
    }

    public AuthResponse login(AuthRequest request) {
        UsuarioDTO usuario = autenticarViaFila(FUNCIONARIO_QUEUE, request);
        if (usuario == null) {
            usuario = autenticarViaFila(CLIENTE_QUEUE, request);
        }
        if (usuario == null || !usuario.isAtivo()) {
            throw new RuntimeException("Usuário não encontrado ou inativo");
        }
        if (!usuario.getSenha().equals(request.getSenha())) {
            throw new RuntimeException("Senha inválida");
        }
        String token = jwtService.generateToken(usuario);
        return new AuthResponse(token, usuario);
    }

    private UsuarioDTO autenticarViaFila(String queue, AuthRequest request) {
        try {
            return (UsuarioDTO) rabbitTemplate.convertSendAndReceive(queue, request.getLogin());
        } catch (Exception e) {
            return null;
        }
    }
}
