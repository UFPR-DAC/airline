package com.example.voo_service.controller;

import com.example.voo_service.model.Aeroporto;
import com.example.voo_service.repository.AeroportoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/aeroportos")
public class AeroportoController {
    @Autowired
    private AeroportoRepository aeroportoRepository;

    @GetMapping
    public List<Aeroporto> listarTodos(){
        return aeroportoRepository.findAll();
    }
}
