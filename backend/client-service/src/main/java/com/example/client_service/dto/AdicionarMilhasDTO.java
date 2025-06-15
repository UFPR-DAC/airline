package com.example.client_service.dto;

import java.math.BigDecimal;


public class AdicionarMilhasDTO {
    private BigDecimal quantidade;

    public BigDecimal getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(BigDecimal quantidade) {
        this.quantidade = quantidade;
    }
}