package com.example.authservice.dto;

public class FuncionarioUsuarioResponse extends UsuarioResponse {
    private String telefone;

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
}
