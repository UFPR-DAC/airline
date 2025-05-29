package com.dac.airline.Client_Service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.dac.airline.Client_Service.repository.ClienteRepository;
import com.dac.airline.Client_Service.service.ClienteService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RequestMapping
@RestController
public class ClientController {
    
    @Autowired
    private ClienteService clientService;

    @Autowired
    private ClienteRepository clienteRepository;

    @PostMapping("/cliente")
    public String postMethodName(@RequestBody String entity) {
        //TODO: process POST request
        
        return entity;
    }
    


    
}
