package com.example.airline.service;

import com.example.airline.model.User;
import com.example.airline.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User registerUser(User user) {
        return userRepository.save(user);
    }

    public User getUserByCpf(String cpf) {
        return userRepository.findByCpf((cpf));
    }
}
