package com.example.client_service.service;

import com.example.client_service.dto.*;
import com.example.client_service.enums.EnumTipoTransacao;
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

    private static final BigDecimal CONVERSAO_MILHAS = new BigDecimal("5.00");

    @Transactional
    public ClienteDTO criarCliente(ClienteDTO dto) {
        Cliente cliente = new Cliente();
        cliente.setCpf(dto.getCpf());
        cliente.setEmail(dto.getEmail());
        cliente.setNome(dto.getNome());
        cliente.setSaldoMilhas(BigDecimal.ZERO);
        if (dto.getEndereco() != null) {
            cliente.setEndereco(new Endereco(dto.getEndereco()));
        }
        Cliente novoCliente = clienteRepository.save(cliente);

        return new ClienteDTO(novoCliente);
    }

    @Transactional(readOnly = true)
    public ClienteDTO buscarClientePorId(Long id) {
        Cliente cliente = clienteRepository.findById(id).orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
        return new ClienteDTO(cliente);
    }

    @Transactional
    public ClienteDTO adicionarMilhas(Long clienteId, AdicionarMilhasDTO dto) {
        Cliente cliente = clienteRepository.findById(clienteId).orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
        
        BigDecimal quantidadeMilhas = dto.getQuantidade();
        BigDecimal valorEmReais = quantidadeMilhas.multiply(CONVERSAO_MILHAS);

        cliente.setSaldoMilhas(cliente.getSaldoMilhas().add(quantidadeMilhas));
        
        TransacaoMilha transacao = new TransacaoMilha();
        transacao.setCliente(cliente);
        transacao.setData(Instant.now());
        transacao.setQuantidadeMilhas(quantidadeMilhas);
        transacao.setValorReais(valorEmReais);
        transacao.setDescricao("COMPRA DE MILHAS");
        transacao.setTipo(EnumTipoTransacao.ENTRADA);
        
        transacaoMilhaRepository.save(transacao);
        Cliente clienteAtualizado = clienteRepository.save(cliente);

        return new ClienteDTO(clienteAtualizado);
    }

    @Transactional(readOnly = true)
    public ExtratoMilhasDTO buscarExtratoMilhas(Long clienteId) {
        Cliente cliente = clienteRepository.findById(clienteId).orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
        List<TransacaoMilha> transacoes = transacaoMilhaRepository.findByClienteCodigoOrderByDataDesc(clienteId);
        
        List<TransacaoDTO> transacoesDTO = transacoes.stream()
                .map(TransacaoDTO::new)
                .collect(Collectors.toList());

        return new ExtratoMilhasDTO(cliente.getCodigo(), cliente.getSaldoMilhas(), transacoesDTO);
    }
}