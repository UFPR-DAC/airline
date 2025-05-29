package com.dac.airline.Client_Service.model;

import java.util.List;

//@Document(collection = "clientes")

public class Cliente {

    private int clienteID;
    private String cpf;
    private String nome;
    private String email;
    private Long saldoMilhas;
    private List<TransacaoMilhas> transacoes = List.of();
    private Endereco endereco;

    public Cliente(String cpf, String nome, String email, Long saldoMilhas, List<TransacaoMilhas> transacoes, Endereco endereco) {
        this.cpf = cpf;
        this.nome = nome;
        this.email = email;
        this.saldoMilhas = saldoMilhas;
        this.transacoes = transacoes;
        this.endereco = endereco;
    }

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
    public List<TransacaoMilhas> getTransacoes() {
        return transacoes;
    }
    public void setTransacoes(List<TransacaoMilhas> transacoes) {
        this.transacoes = transacoes;
    }
    public Endereco getEndereco() {
        return endereco;
    }
    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }
    
}
