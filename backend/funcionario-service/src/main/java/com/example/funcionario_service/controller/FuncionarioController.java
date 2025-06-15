package com.example.funcionario_service.controller;

import com.example.funcionario_service.dto.FuncionarioDTO;
import com.example.funcionario_service.dto.FuncionarioRequestDTO;
import com.example.funcionario_service.model.Funcionario;
import com.example.funcionario_service.repository.FuncionarioRepository;
import com.example.funcionario_service.service.FuncionarioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/funcionarios")
public class FuncionarioController {

    @Autowired
    private FuncionarioService funcionarioService;

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @GetMapping
    public List<Funcionario> listar() {
        return funcionarioRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<FuncionarioDTO> criarFuncionario(@RequestBody FuncionarioRequestDTO requestDTO) {
        try {
            FuncionarioDTO novoFuncionario = funcionarioService.criarFuncionario(requestDTO);
            return new ResponseEntity<>(novoFuncionario, HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    @PutMapping("/{codigoFuncionario}")
    public ResponseEntity<FuncionarioDTO> alterarFuncionario(@PathVariable Long codigoFuncionario, @RequestBody FuncionarioRequestDTO requestDTO) {
        FuncionarioDTO funcionarioAtualizado = funcionarioService.alterarFuncionario(codigoFuncionario, requestDTO);
        if (funcionarioAtualizado != null) {
            return ResponseEntity.ok(funcionarioAtualizado);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{codigoFuncionario}")
    public ResponseEntity<Void> removerFuncionario(@PathVariable Long codigoFuncionario) {
        boolean removido = funcionarioService.removerFuncionario(codigoFuncionario);
        if (removido) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}