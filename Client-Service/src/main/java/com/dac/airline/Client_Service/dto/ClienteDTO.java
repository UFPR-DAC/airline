package com.dac.airline.Client_Service.dto;

public class ClienteDTO {
    private int clienteID;
    private String cpf;
    private String nome;
    private String email;
    private Long saldoMilhas;
    private EnderecoDTO endereco;

    public int getClienteID() {
        return clienteID;
    }
    public String getCpf() {
        return cpf;
    }
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public Long getSaldoMilhas() {
        return saldoMilhas;
    }
    public void setSaldoMilhas(Long saldoMilhas) {
        this.saldoMilhas = saldoMilhas;
    }
    public EnderecoDTO getEndereco() {
        return endereco;
    }
    public void setEndereco(EnderecoDTO endereco) {
        this.endereco = endereco;
    }
}
