package com.example.client_service.service;

import com.example.client_service.dto.*;
import com.example.client_service.enums.TipoTransacao;
import com.example.client_service.model.Cliente;
import com.example.client_service.model.Endereco;
import com.example.client_service.model.TransacaoMilha;
import com.example.client_service.repository.ClienteRepository;
import com.example.client_service.repository.TransacaoMilhaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private TransacaoMilhaRepository transacaoMilhaRepository;

    private static final BigDecimal FATOR_CONVERSAO_MILHAS = new BigDecimal("5.00");

    @Transactional
    public ClienteDTO criarCliente(ClienteDTO dto) {
        // R01: Autocadastro de cliente 
        Cliente cliente = new Cliente();
        cliente.setCpf(dto.getCpf());
        cliente.setEmail(dto.getEmail());
        cliente.setNome(dto.getNome());
        cliente.setSaldoMilhas(BigDecimal.ZERO); // Cliente inicia com 0 milhas 
        if (dto.getEndereco() != null) {
            cliente.setEndereco(new Endereco(dto.getEndereco()));
        }
        Cliente novoCliente = clienteRepository.save(cliente);

        // TODO: SAGA - Enviar mensagem para o RabbitMQ para o MS de Autenticação criar o usuário.
        // O requisito R77 diz para gerar senha aleatória e enviar por e-mail,
        // essa lógica seria iniciada aqui, enviando os dados na mensagem.

        return new ClienteDTO(novoCliente);
    }

    @Transactional(readOnly = true)
    public ClienteDTO buscarClientePorId(Long id) {
        Cliente cliente = clienteRepository.findById(id).orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
        return new ClienteDTO(cliente);
    }

    @Transactional
    public ClienteDTO adicionarMilhas(Long clienteId, AdicionarMilhasDTO dto) {
        // R05: Comprar de Milhas 
        Cliente cliente = clienteRepository.findById(clienteId).orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
        
        BigDecimal quantidadeMilhas = dto.getQuantidade();
        BigDecimal valorEmReais = quantidadeMilhas.multiply(FATOR_CONVERSAO_MILHAS); // Proporção é 1 milha a cada R$ 5,00 

        // Atualiza o saldo do cliente
        cliente.setSaldoMilhas(cliente.getSaldoMilhas().add(quantidadeMilhas));
        
        // R06: Registra a transação
        TransacaoMilha transacao = new TransacaoMilha();
        transacao.setCliente(cliente);
        transacao.setData(Instant.now());
        transacao.setQuantidadeMilhas(quantidadeMilhas);
        transacao.setValorReais(valorEmReais);
        transacao.setDescricao("COMPRA DE MILHAS"); // Descrição padrão para compra 
        transacao.setTipo(TipoTransacao.ENTRADA);
        
        transacaoMilhaRepository.save(transacao);
        Cliente clienteAtualizado = clienteRepository.save(cliente);

        return new ClienteDTO(clienteAtualizado);
    }

    @Transactional(readOnly = true)
    public ExtratoMilhasDTO buscarExtratoMilhas(Long clienteId) {
        // R06: Consultar Extrato de Milhas 
        Cliente cliente = clienteRepository.findById(clienteId).orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
        List<TransacaoMilha> transacoes = transacaoMilhaRepository.findByClienteCodigoOrderByDataDesc(clienteId);
        
        List<TransacaoDTO> transacoesDTO = transacoes.stream()
                .map(TransacaoDTO::new)
                .collect(Collectors.toList());

        return new ExtratoMilhasDTO(cliente.getCodigo(), cliente.getSaldoMilhas(), transacoesDTO);
    }
}