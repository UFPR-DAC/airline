package com.example.authservice.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;

@Service
public class JwtService {
    private final Key key;
    private final long expiration;

    public JwtService(
            @Value("tadsUFPR2025DesenvolvimentoAplicacoesCorporativasGrupo6AndersonRafaelViviane") String secret,
            @Value("86400000") long expiration) {
        System.out.println("jwt.secret: " + secret);
        System.out.println("jwt.expiration: " + expiration);
        if (secret == null || secret.trim().isEmpty() || secret.length() < 10) {
            throw new IllegalArgumentException("Secret não pode ser nulo, vazio ou menor que 32 caracteres");
        }
        this.key = Keys.hmacShaKeyFor(secret.getBytes());
        this.expiration = expiration;
    }

    public String generateToken(String email, String tipo) {
        return Jwts.builder()
                .setSubject(email)
                .claim("tipo", tipo)
                .claim("email", email)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(key, SignatureAlgorithm.HS512)
                .compact();
    }

    public String extractEmail(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public String getTokenType() {
        return "Bearer";
    }
}