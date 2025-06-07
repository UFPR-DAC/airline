package com.example.authservice.controller;

import com.example.authservice.dto.AuthRequest;
import com.example.authservice.dto.AuthResponse;
import com.example.authservice.model.Customer;
import com.example.authservice.repository.UserRepository;
import com.example.authservice.security.JwtService;

import java.beans.Customizer;

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

    @PostMapping("/cadastrar")
    public ResponseEntity<?> register(@RequestBody AuthRequest request) {
        if (userRepository.existsByEmail(request.getLogin())) {
            return ResponseEntity.status(400).body("Email já cadastrado.");
        }

        Customer user = new Customer();
        user.setEmail(request.getLogin());
        user.setSenha(passwordEncoder.encode(request.getSenha()));
        userRepository.save(user);

        String token = jwtService.generateToken(user);
        return ResponseEntity.ok(new AuthResponse(token, user));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest request) {
        Customer user = userRepository.findByEmail(request.getLogin())
                .orElse(null);
        if (user == null || !passwordEncoder.matches(request.getSenha(), user.getSenha())) {
            return ResponseEntity.status(401).body("Dados inválidos.");
        }

        String token = jwtService.generateToken(user);
        return ResponseEntity.ok(new AuthResponse(token, user));
    }
}