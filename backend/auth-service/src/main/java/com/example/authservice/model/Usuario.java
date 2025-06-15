package com.example.authservice.model;

import org.springframework.data.annotation.Id;

public class Usuario {
    @Id
    private String cpf;
    private String nome;
    private String email;
    private String senha;
    private TipoUsuario tipo;
}
