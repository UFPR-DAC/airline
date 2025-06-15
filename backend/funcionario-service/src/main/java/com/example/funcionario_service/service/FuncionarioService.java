package com.example.funcionario_service.service;

import com.example.funcionario_service.dto.FuncionarioDTO;
import com.example.funcionario_service.dto.FuncionarioRequestDTO;
import com.example.funcionario_service.model.Funcionario;
import com.example.funcionario_service.repository.FuncionarioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FuncionarioService {

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Transactional(readOnly = true)
    public List<FuncionarioDTO> buscarTodosFuncionarios() {
        List<Funcionario> funcionarios = funcionarioRepository.findAllByAtivoTrueOrderByNomeAsc();
        return funcionarios.stream()
                .map(FuncionarioDTO::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public FuncionarioDTO criarFuncionario(FuncionarioRequestDTO requestDTO) {
        Funcionario funcionario = new Funcionario();
        
        funcionario.setNome(requestDTO.getNome());
        funcionario.setCpf(requestDTO.getCpf());
        funcionario.setEmail(requestDTO.getEmail());
        funcionario.setTelefone(requestDTO.getTelefone());
        funcionario.setAtivo(true);

        Funcionario novoFuncionario = funcionarioRepository.save(funcionario);

        // TODO: Lógica SAGA - Enviar mensagem de inserir para o RabbitMQ
        // para o MS de Autenticação criar o usuário com a senha recebida.

        return new FuncionarioDTO(novoFuncionario);
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

            // TODO: Lógica SAGA - Enviar mensagem de atualização para o RabbitMQ
            // para o MS de Autenticação, caso o e-mail (login) mude.

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

            // TODO: Lógica SAGA - Enviar mensagem de remover para o RabbitMQ
            // para o MS de Autenticação inativar ou remover o login do usuário.
            
            return new FuncionarioDTO(funcionarioRemovido);
        }
        return null;
    }
}