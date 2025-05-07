package com.example.airline.repository;

import com.example.airline.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {
    User findByCpf(String cpf);
}
