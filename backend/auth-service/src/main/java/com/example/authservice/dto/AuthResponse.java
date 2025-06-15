package com.example.authservice.dto;

import com.example.authservice.model.Customer;
import com.example.authservice.model.TipoUsuario;

public class AuthResponse {
    private String access_token;
    private String token_type = "Bearer";
    private TipoUsuario tipo;
    private UsuarioDTO usuario;

    public AuthResponse(String token, UsuarioDTO usuario) {
        this.access_token = token;
        this.tipo = TipoUsuario.valueOf(usuario.getTipo());
        this.usuario = usuario;
        this.usuario.setSenha("");
    }

    public String getAccess_token() { return access_token; }
    public String getToken_type() { return token_type; }

    public TipoUsuario getTipo() {
        return tipo;
    }

    public UsuarioDTO getUsuario() {
        return usuario;
    }
}