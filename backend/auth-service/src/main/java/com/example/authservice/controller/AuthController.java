package com.example.authservice.controller;

import com.example.authservice.dto.AuthRequest;
import com.example.authservice.dto.AuthResponse;
import com.example.authservice.dto.CadastroRequest;
import com.example.authservice.dto.UsuarioResponse;
import com.example.authservice.model.Usuario;
import com.example.authservice.security.JwtService;

import com.example.authservice.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

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
        System.out.println("Chamando ms auth com request: " + request.getLogin());
        try {
            System.out.println("recebi o request: " + request);
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getLogin(), request.getSenha())
            );
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            Usuario usuario = authService.getUsuarioByEmail(userDetails.getUsername());
            String token = jwtService.generateToken(userDetails.getUsername(), usuario.getTipo());

            //chamada pro API gateway
            RestTemplate restTemplate = new RestTemplate();
            String gatewayUrl = "http://localhost:3000/internal/usuario?email=" + userDetails.getUsername();

            ResponseEntity<Object> usuarioResponse = restTemplate.getForEntity(gatewayUrl, Object.class);
            Object usuarioCompleto = usuarioResponse.getBody();

            AuthResponse authResponse = new AuthResponse();
            authResponse.setAccess_token(token);
            authResponse.setToken_type("Bearer");
            authResponse.setTipo(usuario.getTipo());
            authResponse.setUsuario((Usuario) usuarioCompleto);

            return ResponseEntity.ok(authResponse);

        } catch (BadCredentialsException e) {
            return ResponseEntity.status(401).body("Credenciais inválidas");
        }
    }

    @PostMapping("/cadastro")
    public ResponseEntity<?> cadastrarUsuario(@RequestBody CadastroRequest request) {
        System.out.println("Recebendo novo cadastro de usuário: " + request.getLogin());
        try {
            Usuario usuario = authService.cadastrarUsuario(request.getLogin(), request.getTipo());
            return ResponseEntity.status(HttpStatus.CREATED).body(usuario);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro ao cadastrar novo usuário no auth service." + e.getMessage());
        }
    }
}