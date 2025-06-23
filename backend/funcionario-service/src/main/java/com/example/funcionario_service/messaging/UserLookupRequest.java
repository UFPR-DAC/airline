package com.example.funcionario_service.messaging;

import java.io.Serializable;

public class UserLookupRequest implements Serializable {
    private String email;

    public UserLookupRequest() {}

    public UserLookupRequest(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
