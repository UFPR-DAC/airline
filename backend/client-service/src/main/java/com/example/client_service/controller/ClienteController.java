package com.example.client_service.controller;

import com.example.client_service.dto.*;
import com.example.client_service.model.Cliente;
import com.example.client_service.service.ClienteService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/busca-email")
    public ResponseEntity<ClienteAuthDTO> buscarClientePorEmail(@RequestParam String email) {
        System.out.println("GET em /busca-email?email=" + email);
        try {
            ClienteAuthDTO cliente = clienteService.buscarClientePorEmail(email);
            return ResponseEntity.ok(cliente);
        } catch (RuntimeException e) {
            System.out.println("Erro ao buscar o email " + email);
            return ResponseEntity.notFound().build();
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

    @GetMapping("/{codigoCliente}/milhas")
    public ResponseEntity<ExtratoMilhasDTO> buscarMilhas(
            @PathVariable Long codigoCliente,
            @AuthenticationPrincipal UserDetails usuarioAutenticado) {
        if (usuarioAutenticado == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        if (!clienteService.usuarioTemPermissaoCliente(usuarioAutenticado, codigoCliente)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        try {
            ExtratoMilhasDTO extrato = clienteService.buscarExtratoMilhas(codigoCliente);
            return ResponseEntity.ok(extrato);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/{codigoCliente}/milhas")
    public ResponseEntity<RespostaTransacaoDTO> adicionarMilhas(
            @PathVariable Long codigoCliente,
            @RequestBody AdicionarMilhasDTO adicionarMilhasDTO,
            @AuthenticationPrincipal UserDetails usuarioAutenticado) {
        if (usuarioAutenticado == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        if (!clienteService.usuarioTemPermissaoCliente(usuarioAutenticado, codigoCliente)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        try {
            Cliente clienteAtualizado = clienteService.adicionarMilhas(codigoCliente, adicionarMilhasDTO);
            RespostaTransacaoDTO response = new RespostaTransacaoDTO(
                    clienteAtualizado.getCodigo(),
                    clienteAtualizado.getSaldoMilhas()
            );
            return ResponseEntity.ok(response);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}