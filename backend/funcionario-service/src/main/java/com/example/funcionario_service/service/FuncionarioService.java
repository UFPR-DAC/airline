package com.example.funcionario_service.service;

import com.example.funcionario_service.dto.FuncionarioCriadoRequest;
import com.example.funcionario_service.dto.FuncionarioDTO;
import com.example.funcionario_service.dto.FuncionarioRequestDTO;
import com.example.funcionario_service.model.Funcionario;
import com.example.funcionario_service.repository.FuncionarioRepository;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class FuncionarioService {

    @Autowired
    private FuncionarioRepository funcionarioRepository;
    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    private JavaMailSender mailSender;

    @Transactional(readOnly = true)
    public List<FuncionarioDTO> buscarTodosFuncionarios() {
        List<Funcionario> funcionarios = funcionarioRepository.findAllByAtivoTrueOrderByNomeAsc();
        return funcionarios.stream()
                .map(FuncionarioDTO::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public FuncionarioDTO criarFuncionario(FuncionarioRequestDTO dto) {
        Funcionario funcionario = new Funcionario();
        
        funcionario.setNome(dto.getNome());
        funcionario.setCpf(dto.getCpf());
        funcionario.setEmail(dto.getEmail());
        funcionario.setTelefone(dto.getTelefone());
        funcionario.setAtivo(true);

        Funcionario novoFuncionario = funcionarioRepository.save(funcionario);

        String senha = String.valueOf(new Random().nextInt(9000) + 1000);
        try {
            enviarEmail(funcionario.getEmail(), senha, "FUNCIONARIO");
            notificarAuthService(funcionario.getEmail(), senha, "FUNCIONARIO");
        } catch (Exception e) {
            System.err.println("Erro ao enviar e-mail com senha para " + funcionario.getEmail() + ": " + e.getMessage());
        }

        return new FuncionarioDTO(novoFuncionario);
    }

    private void enviarEmail(String email, String senha, String tipo) {
        SimpleMailMessage mensagem = new SimpleMailMessage();
        mensagem.setTo(email);
        mensagem.setSubject("Sua conta foi criada com sucesso!");
        mensagem.setText("Sua senha de acesso é " + senha);

        mailSender.send(mensagem);
        System.out.println("[EmailTestRunner] E-mail de novo funcionário enviado!");
    }

    private void notificarAuthService(String email, String senha, String tipo) {
        FuncionarioCriadoRequest request = new FuncionarioCriadoRequest();
        request.setEmail(email);
        request.setSenha(senha);
        request.setTipo(tipo);

        rabbitTemplate.convertAndSend("funcionario.criado.save", request);
    }

    @Transactional
    public FuncionarioDTO alterarFuncionario(Long id, FuncionarioRequestDTO requestDTO) {
        Optional<Funcionario> funcionarioOpt = funcionarioRepository.findById(id);
        if (funcionarioOpt.isPresent()) {
            Funcionario funcionario = funcionarioOpt.get();
            
            funcionario.setNome(requestDTO.getNome());
            funcionario.setEmail(requestDTO.getEmail());
            funcionario.setTelefone(requestDTO.getTelefone());

            Funcionario funcionarioAtualizado = funcionarioRepository.save(funcionario);

            return new FuncionarioDTO(funcionarioAtualizado);
        }
        return null;
    }

    @Transactional
    public FuncionarioDTO removerFuncionario(Long id) {
        Optional<Funcionario> funcionarioOpt = funcionarioRepository.findById(id);
        if (funcionarioOpt.isPresent()) {
            Funcionario funcionario = funcionarioOpt.get();
            
            funcionario.setAtivo(false);

            Funcionario funcionarioRemovido = funcionarioRepository.save(funcionario);
            
            return new FuncionarioDTO(funcionarioRemovido);
        }
        return null;
    }
}