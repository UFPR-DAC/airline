package com.example.funcionario_service.repository;

import com.example.funcionario_service.model.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {
    List<Funcionario> findAllByAtivoTrueOrderByNomeAsc();
    Optional<Funcionario> findByEmail(String email);
}