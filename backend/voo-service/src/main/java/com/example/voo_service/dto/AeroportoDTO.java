package com.example.voo_service.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AeroportoDTO {
    private String codigo;
    private String nome;
    private String cidade;
    private String uf;

    public AeroportoDTO() {}

    public AeroportoDTO(String codigo, String nome, String cidade, String uf) {
        this.codigo = codigo;
        this.nome = nome;
        this.cidade = cidade;
        this.uf = uf;
    }
}
