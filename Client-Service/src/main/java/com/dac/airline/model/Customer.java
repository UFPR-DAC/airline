package com.dac.airline.model;

@Document(collection = "customers")
public class Customer {
    @Id
    private String cpf;
    private String email;
    private String nome;
    private int saldoMilhas;
    private Endereco endereco;
    private String senha;

    public Client(String cpf, String email, String nome, int saldoMilhas, Endereco endereco, String senha) {
        this.cpf = cpf;
        this.email = email;
        this.nome = nome;
        this.saldoMilhas = saldoMilhas;
        this.endereco = endereco;
        this.senha = senha;
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

    public int getSaldoMilhas() {
        return saldoMilhas;
    }

    public void setSaldoMilhas(int saldoMilhas) {
        this.saldoMilhas = saldoMilhas;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
