package com.example.client_service.model;

import com.example.client_service.dto.EnderecoDTO;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Endereco {
    private String cep;
    private String uf;
    private String cidade;
    private String bairro;
    private String rua;
    private String numero;
    private String complemento;
    
    public Endereco(EnderecoDTO dto) {
        this.cep = dto.getCep();
        this.uf = dto.getUf();
        this.cidade = dto.getCidade();
        this.bairro = dto.getBairro();
        this.rua = dto.getRua();
        this.numero = dto.getNumero();
        this.complemento = dto.getComplemento();
    }
}