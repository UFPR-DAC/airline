package com.example.client_service.model;

import com.example.client_service.dto.EnderecoDTO;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Embeddable
public class Endereco {
    private String cep;
    private String rua;
    private String numero;
    private String complemento;
    private String bairro;
    private String cidade;
    private String uf;

    public Endereco () {}
    
    public Endereco(EnderecoDTO dto) {
        this.cep = dto.getCep();
        this.uf = dto.getUf();
        this.cidade = dto.getCidade();
        this.bairro = dto.getBairro();
        this.rua = dto.getRua();
        this.numero = dto.getNumero();
        this.complemento = dto.getComplemento();
    }

    public Endereco(String cep, String rua, String numero, String complemento, String bairro, String cidade, String uf) {
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    
}