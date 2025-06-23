package com.example.authservice.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class TesteHash {
    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String rawPassword = "1234";
        String encodedPassword = encoder.encode(rawPassword);
        System.out.println("senha armazenada com sha256 e salt: " + encodedPassword);
    }
}
