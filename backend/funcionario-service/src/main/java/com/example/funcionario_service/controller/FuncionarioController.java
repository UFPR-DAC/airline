package com.example.funcionario_service.controller;

import com.example.funcionario_service.dto.FuncionarioAuthDTO;
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

//     @GetMapping
//     public List<Funcionario> listarFuncionario() {
//         return funcionarioRepository.findAll();
//     }

    @GetMapping
    public ResponseEntity<List<FuncionarioDTO>> listarFuncionarios() {
        List<FuncionarioDTO> funcionarios = funcionarioService.buscarTodosFuncionarios();
        return ResponseEntity.ok(funcionarios);
    }

    @GetMapping("/busca-email")
    public ResponseEntity<FuncionarioAuthDTO> buscarFuncionarioPorEmail(@RequestParam String email) {
        System.out.println("GET em /busca-email?email=" + email);
        try {
            FuncionarioAuthDTO cliente = funcionarioService.buscarFuncionarioPorEmail(email);
            return ResponseEntity.ok(cliente);
        } catch (RuntimeException e) {
            System.out.println("Erro ao buscar o email " + email);
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<FuncionarioDTO> criarFuncionario(@RequestBody FuncionarioRequestDTO requestDTO) {
        FuncionarioDTO novoFuncionario = funcionarioService.criarFuncionario(requestDTO);
        if(novoFuncionario != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(novoFuncionario);
        }
        return ResponseEntity.notFound().build();
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
    public ResponseEntity<FuncionarioDTO> removerFuncionario(@PathVariable Long codigoFuncionario) {
        FuncionarioDTO funcionarioRemovido = funcionarioService.removerFuncionario(codigoFuncionario);
        if (funcionarioRemovido != null) {
            return ResponseEntity.ok(funcionarioRemovido);
        }
        return ResponseEntity.notFound().build();
    }
}