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
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @PostMapping()
    public ResponseEntity<ClienteDTO> criarCliente(@RequestBody ClienteDTO clienteDTO) {
        try {
            ClienteDTO novoCliente = clienteService.criarCliente(clienteDTO);
            return new ResponseEntity<>(novoCliente, HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    @GetMapping("/{codigoCliente}")
    public ResponseEntity<ClienteDTO> buscarCliente(@PathVariable Long codigoCliente) {
        try {
            ClienteDTO cliente = clienteService.buscarClientePorId(codigoCliente);
            return ResponseEntity.ok(cliente);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{codigoCliente}/reservas")
    public ResponseEntity<ClienteDTO> buscarResevas(@PathVariable Long codigoCliente) {
        try {
            ClienteDTO cliente = clienteService.buscarClientePorId(codigoCliente);
            return ResponseEntity.ok(cliente);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }


    @PutMapping("/{codigoCliente}/milhas")
    public ResponseEntity<Map<String, Object>> adicionarMilhas(@PathVariable Long codigoCliente, @RequestBody AdicionarMilhasDTO adicionarMilhasDTO) {
        try {
            ClienteDTO clienteAtualizado = clienteService.adicionarMilhas(codigoCliente, adicionarMilhasDTO);
            Map<String, Object> response = Map.of(
                "codigo", clienteAtualizado.getCodigo(),
                "saldo_milhas", clienteAtualizado.getSaldo_milhas()
            );
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{codigoCliente}/milhas")
    public ResponseEntity<ExtratoMilhasDTO> buscarMilhas(@PathVariable Long codigoCliente) {
        try {
            ExtratoMilhasDTO extrato = clienteService.buscarExtratoMilhas(codigoCliente);
            return ResponseEntity.ok(extrato);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}