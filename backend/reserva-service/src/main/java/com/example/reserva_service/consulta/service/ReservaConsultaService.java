package com.example.reserva_service.consulta.service;

import com.example.reserva_service.consulta.dto.ReservaConsultaDTO;
import com.example.reserva_service.consulta.model.ReservaConsulta;
import com.example.reserva_service.consulta.repository.ReservaConsultaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReservaConsultaService {
    @Autowired
    private ReservaConsultaRepository repository;

    @Transactional("queryTransactionManager")
    public List<ReservaConsultaDTO> buscarReservasPorCliente(Long clienteId) {
        return repository.findByCodigoCliente(clienteId).stream()
                .map(ReservaConsultaDTO::new)
                .collect(Collectors.toList());
    }

    @Transactional("queryTransactionManager")
    public ReservaConsultaDTO buscarReservaPorCodigo(String codigoReserva) {
        ReservaConsulta view = repository.findById(codigoReserva)
                .orElseThrow(() -> new RuntimeException("Reserva não encontrada"));
        return new ReservaConsultaDTO(view);
    }
}