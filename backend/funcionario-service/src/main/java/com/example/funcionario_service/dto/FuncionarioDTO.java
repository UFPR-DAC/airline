package com.example.funcionario_service.dto;

import com.example.funcionario_service.model.Funcionario;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FuncionarioDTO {
    private Long codigo;
    private String cpf;
    private String email;
    private String nome;
    private String telefone;

    public FuncionarioDTO(Funcionario entity) {
        this.codigo = entity.getCodigo();
        this.cpf = entity.getCpf();
        this.email = entity.getEmail();
        this.nome = entity.getNome();
        this.telefone = entity.getTelefone();
    }
}