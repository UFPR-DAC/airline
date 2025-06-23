package com.example.authservice.controller;

import com.example.authservice.dto.AuthRequest;
import com.example.authservice.dto.AuthResponse;
import com.example.authservice.dto.UsuarioResponse;
import com.example.authservice.model.Usuario;
import com.example.authservice.security.JwtService;

import com.example.authservice.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final AuthService authService;
    private final JwtService jwtService;

    public AuthController(AuthenticationManager authenticationManager, AuthService authService, JwtService jwtService) {
        this.authenticationManager = authenticationManager;
        this.authService = authService;
        this.jwtService = jwtService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest request) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getLogin(), request.getSenha())
            );
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            String token = jwtService.generateToken(userDetails.getUsername(), userDetails.getAuthorities().toString());
            Usuario usuarioResponse = authService.getUsuarioByEmail(userDetails.getUsername());

            AuthResponse authResponse = new AuthResponse();
            authResponse.setAccess_token(token);
            authResponse.setToken_type("Bearer");
            authResponse.setTipo(usuarioResponse.getTipo());
            authResponse.setUsuario(usuarioResponse);

            return ResponseEntity.ok(authResponse);

        } catch (BadCredentialsException e) {
            return ResponseEntity.status(401).body("Credenciais inválidas");
        }
    }
}