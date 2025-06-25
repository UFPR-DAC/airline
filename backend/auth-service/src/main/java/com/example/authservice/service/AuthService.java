package com.example.authservice.service;

import com.example.authservice.dto.*;
import com.example.authservice.model.Usuario;
import com.example.authservice.repository.UsuarioRepository;
import com.example.authservice.security.JwtService;
import com.mongodb.DuplicateKeyException;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;

@Service
public class AuthService implements UserDetailsService {

    private final RabbitTemplate rabbitTemplate;
    private final JwtService jwtService;
    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    @Autowired
    private JavaMailSender mailSender;

    public AuthService(RabbitTemplate rabbitTemplate,
                       JwtService jwtService, UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.rabbitTemplate = rabbitTemplate;
        this.jwtService = jwtService;
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public AuthResponse login(AuthRequest authRequest) {
        String email = authRequest.getLogin();
        String senhaInput = authRequest.getSenha();

        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new BadCredentialsException("Usuário não encontrado"));

        if (!passwordEncoder.matches(senhaInput, usuario.getSenha())) {
            throw new BadCredentialsException("Senha inválida");
        }
        String token = jwtService.generateToken(email, usuario.getTipo());
        Object usuarioResponse;

        if ("CLIENTE".equalsIgnoreCase(usuario.getTipo())) {
            try {
                ClienteAuthDTO cliente = (ClienteAuthDTO) rabbitTemplate.convertSendAndReceive(
                        "cliente.auth.lookup", new UsuarioLookupRequest(email)
                );
                if (cliente == null) {
                    throw new BadCredentialsException("Cliente não encontrado");
                }
                usuarioResponse = mapClienteToResponse(cliente);
            } catch (Exception e) {
                e.printStackTrace();
                throw new BadCredentialsException("Erro ao consultar tabela de clientes por RabbitMQ");
            }
        } else if ("FUNCIONARIO".equalsIgnoreCase(usuario.getTipo())) {
            try {
                FuncionarioAuthDTO funcionario = (FuncionarioAuthDTO) rabbitTemplate.convertSendAndReceive(
                        "funcionario.auth.lookup", new UsuarioLookupRequest(email)
                );
                if (funcionario == null) {
                    throw new BadCredentialsException("Funcionário não encontrado");
                }
                usuarioResponse = mapFuncionarioToResponse(funcionario);
            } catch (Exception e) {
                e.printStackTrace();
                throw new BadCredentialsException("Erro ao consultar tabela de funcionários por RabbitMQ");
            }
        } else {
            throw new BadCredentialsException("Tipo de usuário não existe");
        }
        return buildResponse(token, usuario.getTipo(), null);
    }

    private AuthResponse buildResponse(String token, String tipo, Usuario usuarioResponse) {
        AuthResponse authResponse = new AuthResponse();
        authResponse.setAccess_token(token);
        authResponse.setToken_type(jwtService.getTokenType());
        authResponse.setTipo(tipo);
        return authResponse;
    }

    private ClienteUsuarioResponse mapClienteToResponse(ClienteAuthDTO cliente) {
        ClienteUsuarioResponse resp = new ClienteUsuarioResponse();
        resp.setCodigo(cliente.getCodigo());
        resp.setCpf(cliente.getCpf());
        resp.setEmail(cliente.getEmail());
        resp.setNome(cliente.getNome());
        resp.setSaldoMilhas(cliente.getSaldoMilhas());
        resp.setEndereco(cliente.getEndereco());
        return resp;
    }

    private FuncionarioUsuarioResponse mapFuncionarioToResponse(FuncionarioAuthDTO func) {
        FuncionarioUsuarioResponse resp = new FuncionarioUsuarioResponse();
        resp.setCodigo(func.getCodigo());
        resp.setCpf(func.getCpf());
        resp.setEmail(func.getEmail());
        resp.setNome(func.getNome());
        resp.setTelefone(func.getTelefone());
        return resp;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));
        return org.springframework.security.core.userdetails.User
                .withUsername(usuario.getEmail())
                .password(usuario.getSenha())
                .roles(usuario.getTipo())
                .build();
    }

    private UsuarioResponse montarUsuarioResponsePorTipo(Usuario usuario) {
        if ("CLIENTE".equalsIgnoreCase(usuario.getTipo())) {
            ClienteAuthDTO cliente = (ClienteAuthDTO) rabbitTemplate.convertSendAndReceive(
                    "cliente.auth.lookup", new UsuarioLookupRequest(usuario.getEmail())
            );
            if (cliente == null) {
                throw new BadCredentialsException("Cliente não encontrado");
            }
            return mapClienteToResponse(cliente);
        } else if ("FUNCIONARIO".equalsIgnoreCase(usuario.getTipo())) {
            FuncionarioAuthDTO func = (FuncionarioAuthDTO) rabbitTemplate.convertSendAndReceive(
                    "funcionario.auth.lookup", new UsuarioLookupRequest(usuario.getEmail())
            );
            if (func == null) {
                throw new BadCredentialsException("Funcionário não encontrado");
            }
            return mapFuncionarioToResponse(func);
        } else {
            throw new BadCredentialsException("Tipo de usuário não encontrado");
        }
    }

    public UsuarioResponse getUsuarioResponseByEmail(String email) {
        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new BadCredentialsException("Usuário não encontrado"));
        return montarUsuarioResponsePorTipo(usuario);
    }

    public Usuario getUsuarioByEmail(String email) {
        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new BadCredentialsException("Usuário não encontrado"));
        return usuario;
    }

    private void enviarEmail(String email, String senha) {
        System.out.println("Função enviar email chamada...");
        SimpleMailMessage mensagem = new SimpleMailMessage();
        mensagem.setTo(email);
        mensagem.setSubject("Sua conta foi criada com sucesso!");
        mensagem.setText("Sua senha de acesso é " + senha);

        mailSender.send(mensagem);
        System.out.println("[EmailTestRunner] E-mail de novo funcionário enviado!");
        System.out.println("Sua senha é "+ senha);
    }

    public Usuario cadastrarUsuario(String login, String tipo) {
        System.out.println("Função cadastrar usuário chamada...");
        if (usuarioRepository.findByEmail(login).isPresent()) {
            throw new RuntimeException("Login com esse e-mail já existe");
        }

        int numeroAleatorio = 1000 + new SecureRandom().nextInt(9000);
        String senhaAleatoria = String.valueOf(numeroAleatorio);
        String senhaHash = passwordEncoder.encode(senhaAleatoria);
        Usuario usuario = new Usuario(login, senhaHash, tipo.toUpperCase());

        try {
            usuarioRepository.save(usuario);
        } catch (DuplicateKeyException e) {
            e.printStackTrace();
            throw new RuntimeException("Login com esse ID já existe");
        }

        try {
            enviarEmail(usuario.getEmail(), senhaAleatoria);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Senha aleatória é " + senhaAleatoria);
            System.err.println("Erro ao enviar e-mail para novo usuário: " + login + ". " + e.getMessage());
        }

        return usuario;
    }
}
