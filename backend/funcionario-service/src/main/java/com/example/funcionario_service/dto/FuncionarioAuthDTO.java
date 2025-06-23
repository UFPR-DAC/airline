package com.example.funcionario_service.dto;

import com.example.funcionario_service.model.Funcionario;

public class FuncionarioAuthDTO {
    private Long codigo;
    private String cpf;
    private String email;
    private String nome;
    private String telefone;

    public FuncionarioAuthDTO(Funcionario funcionario) {
        this.codigo = funcionario.getCodigo();
        this.cpf = funcionario.getCpf();
        this.email = funcionario.getEmail();
        this.nome = funcionario.getNome();
        this.telefone = funcionario.getTelefone();
    }
}
