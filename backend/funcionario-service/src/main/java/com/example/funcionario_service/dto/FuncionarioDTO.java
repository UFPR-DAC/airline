package com.example.funcionario_service.dto;

import com.example.funcionario_service.model.Funcionario;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
public class FuncionarioDTO {
    private Long codigo;
    private String cpf;
    private String email;
    private String nome;
    private String telefone;

    public FuncionarioDTO(Funcionario funcionario) {
        this.codigo = funcionario.getCodigo();
        this.cpf = funcionario.getCpf();
        this.email = funcionario.getEmail();
        this.nome = funcionario.getNome();
        this.telefone = funcionario.getTelefone();
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

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }  

}