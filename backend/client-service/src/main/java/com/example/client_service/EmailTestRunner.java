package com.example.client_service;

import org.springframework.boot.CommandLineRunner;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class EmailTestRunner implements CommandLineRunner {

    private final JavaMailSender mailSender;

    public EmailTestRunner(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Override
    public void run(String... args) {
        SimpleMailMessage mensagem = new SimpleMailMessage();
        mensagem.setTo("vivianeendomori@gmail.com");
        mensagem.setSubject("Teste de e‑mail");
        mensagem.setText("Se você receber esta mensagem, seu e‑mail está configurado corretamente!");

        mailSender.send(mensagem);
        System.out.println("[EmailTestRunner] Teste de e‑mail enviado!");
    }
}
