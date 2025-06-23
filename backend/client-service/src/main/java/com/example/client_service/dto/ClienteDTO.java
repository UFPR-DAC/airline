package com.example.client_service.dto;

import com.example.client_service.model.Cliente;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;

@AllArgsConstructor
public class ClienteDTO {
    private String cpf;
    private String email;
    private String nome;
    private EnderecoDTO endereco;

    public ClienteDTO() {}

    public ClienteDTO(Cliente cliente) {
        this.cpf = cliente.getCpf();
        this.email = cliente.getEmail();
        this.nome = cliente.getNome();
        if (cliente.getEndereco() != null) {
            this.endereco = new EnderecoDTO(cliente.getEndereco());
        }
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

    public EnderecoDTO getEndereco() {
        return endereco;
    }

    public void setEndereco(EnderecoDTO endereco) {
        this.endereco = endereco;
    }

    
}