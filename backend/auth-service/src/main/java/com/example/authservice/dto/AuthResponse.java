package com.example.authservice.dto;

import com.example.authservice.model.Customer;
import com.example.authservice.model.Role;

public class AuthResponse {
    private String access_token;
    private String token_type = "Bearer";
    private Role tipo;
    private Customer usuario;

    public AuthResponse(String token, Customer usuario) {
        this.access_token = token;
        this.tipo = usuario.getRole();
        this.usuario = usuario;
    }

    public String getAccess_token() { return access_token; }
    public String getToken_type() { return token_type; }

    public Role getTipo() {
        return tipo;
    }

    public Customer getUsuario() {
        return usuario;
    }
}