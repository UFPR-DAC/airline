package com.example.client_service.controller;

import com.example.client_service.dto.AdicionarMilhasDTO;
import com.example.client_service.dto.ClienteDTO;
import com.example.client_service.dto.ExtratoMilhasDTO;
import com.example.client_service.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @PostMapping("/clientes")
    public ResponseEntity<ClienteDTO> inserirCliente(@RequestBody ClienteDTO clienteDTO) {
        try {
            ClienteDTO novoCliente = clienteService.criarCliente(clienteDTO);
            return new ResponseEntity<>(novoCliente, HttpStatus.CREATED);
        } catch (Exception e) {
            // Tratamento genérico para conflitos (ex: CPF/email duplicado)
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    @GetMapping("/clientes/{codigoCliente}")
    public ResponseEntity<ClienteDTO> buscarCliente(@PathVariable Long codigoCliente) {
        try {
            ClienteDTO cliente = clienteService.buscarClientePorId(codigoCliente);
            return ResponseEntity.ok(cliente);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/clientes/{codigoCliente}/milhas")
    public ResponseEntity<Map<String, Object>> adicionarMilhas(@PathVariable Long codigoCliente, @RequestBody AdicionarMilhasDTO adicionarMilhasDTO) {
        try {
            ClienteDTO clienteAtualizado = clienteService.adicionarMilhas(codigoCliente, adicionarMilhasDTO);
            // O retorno da API especifica um JSON com "codigo" e "saldo_milhas" 
            Map<String, Object> response = Map.of(
                "codigo", clienteAtualizado.getCodigo(),
                "saldo_milhas", clienteAtualizado.getSaldo_milhas()
            );
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/clientes/{codigoCliente}/milhas")
    public ResponseEntity<ExtratoMilhasDTO> buscarExtrato(@PathVariable Long codigoCliente) {
        try {
            ExtratoMilhasDTO extrato = clienteService.buscarExtratoMilhas(codigoCliente);
            return ResponseEntity.ok(extrato);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}