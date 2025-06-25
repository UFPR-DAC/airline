package com.example.authservice.controller;

import com.example.authservice.dto.AuthRequest;
import com.example.authservice.dto.AuthResponse;
import com.example.authservice.dto.CadastroRequest;
import com.example.authservice.dto.UsuarioEmailDTO;
import com.example.authservice.model.Usuario;
import com.example.authservice.security.JwtService;

import com.example.authservice.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@RestController
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final AuthService authService;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;

    public AuthController(AuthenticationManager authenticationManager, AuthService authService, JwtService jwtService, PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.authService = authService;
        this.jwtService = jwtService;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest request) {
        System.out.println("Chamando ms auth com request: " + request.getLogin());
        try {
            System.out.println("recebi o request: " + request);
            Usuario usuario = authService.getUsuarioByEmail(request.getLogin());
            if (!passwordEncoder.matches(request.getSenha(), usuario.getSenha())) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Senha incorreta");
            }
            String token = jwtService.generateToken(usuario.getEmail(), usuario.getTipo());
            UsuarioEmailDTO usuarioEmail = new UsuarioEmailDTO(usuario.getEmail());

            AuthResponse authResponse = new AuthResponse();
            authResponse.setAccess_token(token);
            authResponse.setToken_type("Bearer");
            authResponse.setTipo(usuario.getTipo());
            authResponse.setUsuario(usuarioEmail);

            return ResponseEntity.ok(authResponse);
        } catch(UsernameNotFoundException | NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuário não encontrado");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro interno no login");
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