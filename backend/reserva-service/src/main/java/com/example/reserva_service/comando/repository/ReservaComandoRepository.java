package com.example.reserva_service.comando.repository;

import com.example.reserva_service.comando.model.ReservaComando;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservaComandoRepository extends JpaRepository<ReservaComando, String> {}