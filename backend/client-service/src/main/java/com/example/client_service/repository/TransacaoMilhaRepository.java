package com.example.client_service.repository;

import com.example.client_service.model.TransacaoMilha;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface TransacaoMilhaRepository extends JpaRepository<TransacaoMilha, Long> {
    List<TransacaoMilha> findByClienteCodigoOrderByDataDesc(Long clienteCodigo);
}