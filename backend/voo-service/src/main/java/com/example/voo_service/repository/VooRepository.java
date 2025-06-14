package com.example.voo_service.repository;

import com.example.voo_service.model.Voo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VooRepository extends JpaRepository<Voo, String> {
}
