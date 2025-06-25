package com.example.authservice.dto;

public class AuthResponse {
    private String access_token;
    private String token_type = "Bearer";
    private String tipo;
    private UsuarioEmailDTO usuario;

    public String getAccess_token() { return access_token; }
    public void setAccess_token(String access_token) { this.access_token = access_token; }

    public String getToken_type() { return token_type; }
    public void setToken_type(String token_type) { this.token_type = token_type; }

    public String getTipo() {
        return tipo;
    }
    public void setTipo(String tipo) { this.tipo = tipo; }

    public UsuarioEmailDTO getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioEmailDTO usuarioEmail) {
        this.usuario = usuarioEmail;
    }
}