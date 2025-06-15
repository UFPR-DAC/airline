package com.example.client_service.dto;

import com.example.client_service.model.Cliente;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
public class ClienteDTO {
    private Long codigo;
    private String cpf;
    private String email;
    private String nome;
    private BigDecimal saldo_milhas;
    private EnderecoDTO endereco;

    public ClienteDTO(Cliente cliente) {
        this.codigo = cliente.getCodigo();
        this.cpf = cliente.getCpf();
        this.email = cliente.getEmail();
        this.nome = cliente.getNome();
        this.saldo_milhas = cliente.getSaldoMilhas();
        if (cliente.getEndereco() != null) {
            this.endereco = new EnderecoDTO(cliente.getEndereco());
        }
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public BigDecimal getSaldo_milhas() {
        return saldo_milhas;
    }

    public void setSaldo_milhas(BigDecimal saldo_milhas) {
        this.saldo_milhas = saldo_milhas;
    }

    public EnderecoDTO getEndereco() {
        return endereco;
    }

    public void setEndereco(EnderecoDTO endereco) {
        this.endereco = endereco;
    }

    
}