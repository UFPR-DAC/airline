package com.example.voo_service.repository;

import com.example.voo_service.model.Voo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.OffsetDateTime;
import java.util.List;

@Repository
public interface VooRepository extends JpaRepository<Voo, String> {
    List<Voo> findByDataBetween(OffsetDateTime inicio, OffsetDateTime fim);
    List<Voo> findByDataBetweenAndAeroportoOrigem_CodigoAndAeroportoDestino_Codigo(
            OffsetDateTime inicio, OffsetDateTime fim, String codigoOrigem, String codigoDestino
    );
}
