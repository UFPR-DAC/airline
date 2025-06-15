package com.example.client_service.dto;

import com.example.client_service.model.Cliente;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClienteDTO {
    private Long codigo;
    private String cpf;
    private String email;
    private String nome;
    private BigDecimal saldo_milhas;
    private EnderecoDTO endereco;

    public ClienteDTO(Cliente entity) {
        this.codigo = entity.getCodigo();
        this.cpf = entity.getCpf();
        this.email = entity.getEmail();
        this.nome = entity.getNome();
        this.saldo_milhas = entity.getSaldoMilhas();
        if (entity.getEndereco() != null) {
            this.endereco = new EnderecoDTO(entity.getEndereco());
        }
    }
}