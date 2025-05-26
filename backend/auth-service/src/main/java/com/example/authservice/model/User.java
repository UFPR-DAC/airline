package com.example.authservice.model;

@Document(collection = "users")
public class Customer {
    @Id
    private String cpf;
    private String email;
    private String nome;
    private String senha;
    private Role role;

    private Integer saldoMilhas;
    private Endereco endereco;

    private String telefone;

    public User(){}

    public User(String cpf, String email, String nome, String senha, Role role, Integer saldoMilhas, Endereco endereco, String telefone) {
        this.cpf = cpf;
        this.email = email;
        this.nome = nome;
        this.senha = senha;
        this.role = role;
        this.saldoMilhas = saldoMilhas;
        this.endereco = endereco;
        this.telefone = telefone;
    }

    public static User createCliente(String cpf, String email, String nome, String senha, Integer saldoMilhas, Endereco endereco) {
        return new User(cpf, email, nome, senha, UserRole.CLIENTE, saldoMilhas, endereco, null);
    }

    public static User createFuncionario(String cpf, String email, String nome, String senha, String telefone) {
        return new User(cpf, email, nome, senha, UserRole.FUNCIONARIO, null, null, telefone);
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

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Role getRole() { this.role = role; }

    public void setRole(Role role) { this.role = role; }

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

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
}