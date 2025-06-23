package com.example.authservice.dto;

import java.io.Serializable;

public class UsuarioLookupRequest implements Serializable {
    private String email;

    public UsuarioLookupRequest() {}

    public UsuarioLookupRequest(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
