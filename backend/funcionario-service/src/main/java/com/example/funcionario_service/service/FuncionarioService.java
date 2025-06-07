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
        return funcionarios.stream().map(FuncionarioDTO::new).collect(Collectors.toList());
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

        // TODO: Lógica SAGA - Enviar mensagem para o RabbitMQ
        // Aqui você adicionaria a chamada para um serviço que publica uma mensagem
        // para o MS de Autenticação criar o usuário com a senha recebida.
        // Ex: rabbitMQProducer.sendNewUserMessage(novoFuncionario.getCodigo(), requestDTO.getEmail(), requestDTO.getSenha());

        return new FuncionarioDTO(novoFuncionario);
    }

    @Transactional
    public FuncionarioDTO alterarFuncionario(Long id, FuncionarioRequestDTO requestDTO) {
        Optional<Funcionario> funcionarioOpt = funcionarioRepository.findById(id);
        if (funcionarioOpt.isPresent()) {
            Funcionario funcionario = funcionarioOpt.get();
            
            // R18: Permite a alteração de dados, menos o CPF 
            funcionario.setNome(requestDTO.getNome());
            funcionario.setEmail(requestDTO.getEmail());
            funcionario.setTelefone(requestDTO.getTelefone());

            Funcionario funcionarioAtualizado = funcionarioRepository.save(funcionario);

            // TODO: Lógica SAGA (se necessário) - Enviar mensagem de atualização
            // para o MS de Autenticação, caso o e-mail (login) mude.

            return new FuncionarioDTO(funcionarioAtualizado);
        }
        return null; // ou lançar uma exceção de "Não Encontrado"
    }

    @Transactional
    public boolean removerFuncionario(Long id) {
        Optional<Funcionario> funcionarioOpt = funcionarioRepository.findById(id);
        if (funcionarioOpt.isPresent()) {
            Funcionario funcionario = funcionarioOpt.get();
            
            // R19: Ao ser removido, seus dados não devem ser apagados, somente inativados 
            funcionario.setAtivo(false);
            funcionarioRepository.save(funcionario);

            // TODO: Lógica SAGA - Enviar mensagem para o RabbitMQ
            // para o MS de Autenticação inativar ou remover o login do usuário.
            // Ex: rabbitMQProducer.sendDeleteUserMessage(funcionario.getCodigo());
            
            return true;
        }
        return false;
    }
}