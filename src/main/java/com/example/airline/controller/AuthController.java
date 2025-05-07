package com.example.airline.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.airline.model.User;
import com.example.airline.service.UserService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private UserService userService;

    @PostMapping("/clientes")
    public User registerUser(@RequestBody User user) {
        return userService.registerUser(user);
    }

    @PostMapping("/login")
    public String loginUser(@RequestBody User user) {
        User existingUser = userService.getUserByCpf((user.getCpf()));
        if (existingUser != null && existingUser.getSenha().equals((user.getSenha()))) {
            return  "Usuário logado com sucesso!";
        } else {
            throw new RuntimeException("Erro ao validar credenciais de usuário.");
        }
    }
}
