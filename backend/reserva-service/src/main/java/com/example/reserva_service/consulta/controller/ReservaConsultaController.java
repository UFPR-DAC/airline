package com.example.reserva_service.consulta.controller;

import com.example.reserva_service.consulta.dto.ReservaConsultaDTO;
import com.example.reserva_service.consulta.service.ReservaConsultaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ReservaConsultaController {

    @Autowired
    private ReservaConsultaService queryService;

    @GetMapping("/clientes/{codigoCliente}/reservas")
    public ResponseEntity<List<ReservaConsultaDTO>> listarReservasPorCliente(@PathVariable Long codigoCliente) {
        List<ReservaConsultaDTO> reservas = queryService.buscarReservasPorCliente(codigoCliente);
        if (reservas.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(reservas);
    }

    @GetMapping("/reservas/{codigoReserva}")
    public ResponseEntity<ReservaConsultaDTO> buscarReserva(@PathVariable String codigoReserva) {
        try {
            return ResponseEntity.ok(queryService.buscarReservaPorCodigo(codigoReserva));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}