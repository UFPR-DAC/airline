package com.example.client_service.dto;

import java.math.BigDecimal;

public class RespostaTransacaoDTO {
    private Long codigo;
    private BigDecimal saldoMilhas;

    public RespostaTransacaoDTO(Long codigo, BigDecimal saldoMilhas) {
        this.codigo = codigo;
        this.saldoMilhas = saldoMilhas;
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public BigDecimal getSaldoMilhas() {
        return saldoMilhas;
    }

    public void setSaldoMilhas(BigDecimal saldoMilhas) {
        this.saldoMilhas = saldoMilhas;
    }
}
