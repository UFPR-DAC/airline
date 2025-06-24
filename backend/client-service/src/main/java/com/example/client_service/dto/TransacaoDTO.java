package com.example.client_service.dto;

import com.example.client_service.model.TransacaoMilha;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.OffsetDateTime;

@NoArgsConstructor
public class TransacaoDTO {
    private OffsetDateTime data;
    private BigDecimal valorReais;
    private Integer quantidadeMilhas;
    private String descricao;
    private String codigoReserva;
    private String tipo;

    public OffsetDateTime getData() {
        return data;
    }

    public void setData(OffsetDateTime data) {
        this.data = data;
    }

    public BigDecimal getValorReais() {
        return valorReais;
    }

    public void setValorReais(BigDecimal valorReais) {
        this.valorReais = valorReais;
    }

    public Integer getQuantidadeMilhas() {
        return quantidadeMilhas;
    }

    public void setQuantidadeMilhas(Integer quantidadeMilhas) {
        this.quantidadeMilhas = quantidadeMilhas;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getCodigoReserva() {
        return codigoReserva;
    }

    public void setCodigoReserva(String codigoReserva) {
        this.codigoReserva = codigoReserva;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}