package com.example.funcionario_service.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FuncionarioRequestDTO {
    private String cpf;
    private String email;
    private String nome;
    private String telefone;
    private String senha;
}