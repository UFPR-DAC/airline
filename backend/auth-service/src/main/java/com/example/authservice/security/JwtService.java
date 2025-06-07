package com.example.authservice.security;

import com.example.authservice.model.Customer;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JwtService {
    private final String secret = "chave";
    private final long expiration = 86400000L;

    public String generateToken(Customer usuario) {
        return Jwts.builder()
                .setSubject(usuario.getEmail())
                .claim("tipo", usuario.getRole() != null ? usuario.getRole().name() : null)
                .claim("cpf", usuario.getCpf())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    public String extractEmail(String token) {
        return Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public String getTokenType() {
        return "Bearer";
    }
}