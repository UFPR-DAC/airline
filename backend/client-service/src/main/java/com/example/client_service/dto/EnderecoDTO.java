package com.example.client_service.dto;

import com.example.client_service.model.Endereco;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EnderecoDTO {
    private String cep;
    private String uf;
    private String cidade;
    private String bairro;
    private String rua;
    private String numero;
    private String complemento;

    public EnderecoDTO(Endereco entity) {
        this.cep = entity.getCep();
        this.uf = entity.getUf();
        this.cidade = entity.getCidade();
        this.bairro = entity.getBairro();
        this.rua = entity.getRua();
        this.numero = entity.getNumero();
        this.complemento = entity.getComplemento();
    }
}