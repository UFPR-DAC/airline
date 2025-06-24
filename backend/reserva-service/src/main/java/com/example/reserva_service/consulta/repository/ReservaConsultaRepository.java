package com.example.reserva_service.consulta.repository;

import com.example.reserva_service.consulta.model.ReservaConsulta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservaConsultaRepository extends JpaRepository<ReservaConsulta, String> {
    List<ReservaConsulta> findByCodigoCliente(Long codigoCliente);
}