package com.example.client_service.dto;

import java.math.BigDecimal;

public class RespostaTransacaoDTO {
    private Long codigo;
    private Integer saldoMilhas;

    public RespostaTransacaoDTO(Long codigo, Integer saldoMilhas) {
        this.codigo = codigo;
        this.saldoMilhas = saldoMilhas;
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public Integer getSaldoMilhas() {
        return saldoMilhas;
    }

    public void setSaldoMilhas(Integer saldoMilhas) {
        this.saldoMilhas = saldoMilhas;
    }
}
