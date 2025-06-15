package com.example.reserva_service.comando.controller;

import com.example.reserva_service.comando.dto.AlterarEstadoComandoDTO;
import com.example.reserva_service.comando.dto.ReservaComandoDTO;
import com.example.reserva_service.comando.model.ReservaComando;
import com.example.reserva_service.comando.service.ReservaComandoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ReservaComandoController {

    @Autowired
    private ReservaComandoService commandService;

    @PostMapping("/reservas")
    public ResponseEntity<ReservaComando> criarReserva(@RequestBody ReservaComandoDTO dto) {
        try {
            ReservaComando novaReserva = commandService.criarReserva(dto);
            return new ResponseEntity<>(novaReserva, HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @DeleteMapping("/reservas/{codigoReserva}")
    public ResponseEntity<ReservaComando> cancelarReserva(@PathVariable String codigoReserva) {
        try {
            ReservaComando reservaCancelada = commandService.cancelarReserva(codigoReserva);
            return ResponseEntity.ok(reservaCancelada);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping("/reservas/{codigoReserva}/estado")
    public ResponseEntity<ReservaComando> alterarEstadoReserva(@PathVariable String codigoReserva, @RequestBody AlterarEstadoComandoDTO dto) {
        try {
            ReservaComando reservaAtualizada = commandService.alterarEstado(codigoReserva, dto.getEstado());
            return ResponseEntity.ok(reservaAtualizada);
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build(); // Estado inválido para a transição
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}