package com.example.client_service.dto;

import com.example.client_service.model.Cliente;

import java.math.BigDecimal;

public class ClienteAuthDTO {
    private Long codigo;
    private String cpf;
    private String email;
    private String nome;
    private Integer saldoMilhas;
    private EnderecoDTO endereco;

    public ClienteAuthDTO(Cliente cliente) {
        this.codigo = cliente.getCodigo();
        this.cpf = cliente.getCpf();
        this.email = cliente.getEmail();
        this.nome = cliente.getNome();
        this.saldoMilhas = cliente.getSaldoMilhas();
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

    public Integer getSaldoMilhas() {
        return saldoMilhas;
    }

    public void setSaldoMilhas(Integer saldoMilhas) {
        this.saldoMilhas = saldoMilhas;
    }

    public EnderecoDTO getEndereco() {
        return endereco;
    }

    public void setEndereco(EnderecoDTO endereco) {
        this.endereco = endereco;
    }
}
