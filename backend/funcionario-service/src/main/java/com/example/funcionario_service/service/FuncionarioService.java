package com.example.funcionario_service.service;

import com.example.funcionario_service.dto.FuncionarioAuthDTO;
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

        return new FuncionarioDTO(novoFuncionario);
    }

    @Transactional(readOnly = true)
    public FuncionarioAuthDTO buscarFuncionarioPorEmail(String email) {
        Funcionario funcionario = funcionarioRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("Funcionário não encontrado"));
        return new FuncionarioAuthDTO(funcionario);
    }

    private void notificarAuthService(String email, String senha, String tipo) {
        FuncionarioCriadoRequest request = new FuncionarioCriadoRequest();
        request.setEmail(email);
        request.setSenha(senha);
        request.setTipo(tipo);

        rabbitTemplate.convertAndSend("funcionario.criado.save", request);
    }

    @Transactional
    public FuncionarioDTO alterarFuncionario(Long id, FuncionarioDTO requestDTO) {
        Optional<Funcionario> funcionarioOpt = funcionarioRepository.findById(id);
        if (funcionarioOpt.isPresent()) {
            Funcionario funcionario = funcionarioOpt.get();

            if (requestDTO.getNome() != null) {
                funcionario.setNome(requestDTO.getNome());
            }
            if (requestDTO.getEmail() != null) {
                funcionario.setEmail(requestDTO.getEmail());
            }
            if (requestDTO.getTelefone() != null) {
                funcionario.setTelefone(requestDTO.getTelefone());
            }
            funcionario.setCodigo(requestDTO.getCodigo());
            funcionario.setCpf(requestDTO.getCpf());

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