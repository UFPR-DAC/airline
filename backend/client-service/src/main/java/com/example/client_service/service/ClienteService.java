package com.example.client_service.service;

import com.example.client_service.dto.*;
import com.example.client_service.enums.TipoTransacao;
import com.example.client_service.model.Cliente;
import com.example.client_service.model.Endereco;
import com.example.client_service.model.TransacaoMilha;
import com.example.client_service.repository.ClienteRepository;
import com.example.client_service.repository.TransacaoMilhaRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.security.SecureRandom;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private TransacaoMilhaRepository transacaoMilhaRepository;

    @Transactional
    public ClienteDTO criarCliente(ClienteDTO dto) {
        System.out.println("Criando um cliente");
        System.out.println("endereco dto: " + dto.getEndereco());
        System.out.println("cep dto: " + dto.getEndereco().getCep());

        Cliente cliente = new Cliente();
        cliente.setCpf(dto.getCpf());
        cliente.setEmail(dto.getEmail());
        cliente.setNome(dto.getNome());
        cliente.setSaldoMilhas(0);
        Endereco endereco = new Endereco(
                dto.getEndereco().getCep(),
                dto.getEndereco().getRua(),
                dto.getEndereco().getNumero(),
                dto.getEndereco().getComplemento(),
                dto.getEndereco().getBairro(),
                dto.getEndereco().getCidade(),
                dto.getEndereco().getUf()
        );
        System.out.println("endereco criado com cep: " + endereco.getCep());
        cliente.setEndereco(endereco);
        System.out.println("cliente criado com endereço cep " + cliente.getEndereco().getCep());
        Cliente novoCliente = clienteRepository.save(cliente);

        return new ClienteDTO(novoCliente);
    }

    @Transactional(readOnly = true)
    public ClienteDTO buscarClientePorId(Long id) {
        Cliente cliente = clienteRepository.findById(id).orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
        return new ClienteDTO(cliente);
    }

    @Transactional(readOnly = true)
    public ClienteAuthDTO buscarClientePorEmail(String email) {
        Cliente cliente = clienteRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
        return new ClienteAuthDTO(cliente);
    }

    @Transactional
    public Cliente adicionarMilhas(Long codigoCliente, AdicionarMilhasDTO dto) {
        Cliente cliente = clienteRepository.findById(codigoCliente).orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
        
        int quantidadeAtualMilhas = cliente.getSaldoMilhas() != null ? cliente.getSaldoMilhas() : 0;
        int milhasParaAdicionar = dto.getQuantidade() != null ? dto.getQuantidade() : 0;

        SecureRandom random = new SecureRandom();
        //1 milha vai custar entre 2 e 3 centavos, então o fator de conversão deve ser entre 33 e 50
        int fatorConversao = 33 + random.nextInt(18);
        BigDecimal bigDecimalMilhas = BigDecimal.valueOf(milhasParaAdicionar);
        BigDecimal bigDecimalFator = BigDecimal.valueOf(fatorConversao);
        BigDecimal valorEmReais = bigDecimalMilhas.divide(bigDecimalFator, 2, BigDecimal.ROUND_HALF_UP);

        cliente.setSaldoMilhas(quantidadeAtualMilhas + milhasParaAdicionar);
        clienteRepository.save(cliente);
        
        TransacaoMilha transacao = new TransacaoMilha();
        transacao.setCliente(cliente);
        transacao.setData(OffsetDateTime.now());
        transacao.setQuantidadeMilhas(milhasParaAdicionar);
        transacao.setValorReais(valorEmReais);
        transacao.setDescricao("COMPRA DE MILHAS");
        transacao.setTipo(TipoTransacao.ENTRADA);
        
        transacaoMilhaRepository.save(transacao);

        return cliente;
    }

    @Transactional(readOnly = true)
    public ExtratoMilhasDTO buscarExtratoMilhas(Long codigoCliente) {
        Cliente cliente = clienteRepository.findById(codigoCliente).orElseThrow(() -> new EntityNotFoundException("Cliente não encontrado"));
        List<TransacaoMilha> transacoes = transacaoMilhaRepository.findByClienteCodigoOrderByDataDesc(codigoCliente);
        
        List<TransacaoDTO> transacoesDTO = transacoes.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());

        ExtratoMilhasDTO extrato = new ExtratoMilhasDTO();
        extrato.setCodigo(cliente.getCodigo());
        extrato.setSaldoMilhas(cliente.getSaldoMilhas());
        extrato.setTransacoes(transacoesDTO);

        return extrato;
    }

    private TransacaoDTO convertToDTO(TransacaoMilha transacao) {
        TransacaoDTO dto = new TransacaoDTO();
        dto.setData(transacao.getData() != null ? transacao.getData() : OffsetDateTime.now());
        dto.setValorReais(transacao.getValorReais() != null ? BigDecimal.ZERO : transacao.getValorReais());
        dto.setQuantidadeMilhas(transacao.getQuantidadeMilhas() != null ? transacao.getQuantidadeMilhas() : 0);
        dto.setDescricao(transacao.getDescricao() != null ? transacao.getDescricao() : "");
        dto.setCodigoReserva(transacao.getCodigoReserva() != null ? transacao.getCodigoReserva() : "");
        dto.setTipo(transacao.getTipo().toString() != null ? transacao.getTipo().toString() : "");
        return dto;
    }

    public boolean usuarioTemPermissaoCliente(UserDetails usuarioAutenticado, Long codigoCliente) {
        if (usuarioAutenticado.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN"))) {
            return true;
        }
        return usuarioAutenticado.getUsername().equals(buscarCpfPorCodigo(codigoCliente));
    }

    private String buscarCpfPorCodigo(Long codigoCliente) {
        Cliente cliente = clienteRepository.findById(codigoCliente).orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
        return cliente.getCpf();
    }
}