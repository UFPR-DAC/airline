package com.example.authservice.rabbit;

import com.example.authservice.dto.FuncionarioCriadoRequest;
import com.example.authservice.model.Usuario;
import com.example.authservice.repository.UsuarioRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class FuncionarioCriadoListener {
    private final UsuarioRepository usuarioRepository;

    public FuncionarioCriadoListener(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @RabbitListener(queues = "funcionario.criado.save")
    public void onFuncionarioCriado(FuncionarioCriadoRequest request) {
        String senha = new org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder()
                .encode(request.getSenha());
        Usuario usuario = new Usuario();
        usuario.setEmail(request.getEmail());
        usuario.setSenha(senha);
        usuario.setTipo(request.getTipo());

        usuarioRepository.save(usuario);
        System.out.println("Funcionário criado: " + usuario.getEmail());
    }
}
