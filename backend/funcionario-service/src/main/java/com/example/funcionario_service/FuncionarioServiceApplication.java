package com.example.funcionario_service;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableRabbit
public class FuncionarioServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(FuncionarioServiceApplication.class, args);
	}

}
