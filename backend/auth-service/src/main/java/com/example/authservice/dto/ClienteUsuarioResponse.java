package com.example.authservice.dto;

import java.math.BigDecimal;

public class ClienteUsuarioResponse extends UsuarioResponse {
    private BigDecimal saldoMilhas;
    private EnderecoDTO endereco;

    public BigDecimal getSaldoMilhas() {
        return saldoMilhas;
    }

    public void setSaldoMilhas(BigDecimal saldoMilhas) {
        this.saldoMilhas = saldoMilhas;
    }

    public EnderecoDTO getEndereco() {
        return endereco;
    }

    public void setEndereco(EnderecoDTO endereco) {
        this.endereco = endereco;
    }
}
