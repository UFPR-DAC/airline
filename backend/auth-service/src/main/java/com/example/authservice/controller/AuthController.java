package com.example.authservice.controller;

import com.example.authservice.dto.AuthRequest;
import com.example.authservice.dto.AuthResponse;
import com.example.authservice.model.User;
import com.example.authservice.repository.UserRepository;
import com.example.authservice.service.JwtService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
public class AuthController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElse(null);
        if (user == null || !passwordEncoder.matches(request.getSenha(), user.getSenha())) {
            return ResponseEntity.status(401).body("Dados inválidos.")
        }

        String token = jwtService.generateToken(user);
        return ResponseEntity.ok(new AuthResponse(token, user));
    }
}