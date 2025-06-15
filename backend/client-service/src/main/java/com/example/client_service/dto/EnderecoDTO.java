package com.example.client_service.dto;

import com.example.client_service.model.Endereco;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

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

    public EnderecoDTO(Endereco endereco) {
        this.cep = endereco.getCep();
        this.uf = endereco.getUf();
        this.cidade = endereco.getCidade();
        this.bairro = endereco.getBairro();
        this.rua = endereco.getRua();
        this.numero = endereco.getNumero();
        this.complemento = endereco.getComplemento();
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