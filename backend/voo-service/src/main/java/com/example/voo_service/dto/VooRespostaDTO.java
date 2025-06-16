package com.example.voo_service.dto;

import com.example.voo_service.model.Estado;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

public class VooRespostaDTO {
    private String codigo;
    private OffsetDateTime data;
    @JsonProperty("valor_passagem")
    private BigDecimal valorPassagem;
    @JsonProperty("quantidade_poltronas_total")
    private Integer quantidadePoltronasTotal;
    @JsonProperty("quantidade_poltronas_ocupadas")
    private Integer quantidadePoltronasOcupadas;
    private Estado estado;
    @JsonProperty("codigo_aeroporto_origem")
    private String codigoAeroportoOrigem;
    @JsonProperty("codigo_aeroporto_destino")
    private String codigoAeroportoDestino;

    public VooRespostaDTO(
            String codigo, OffsetDateTime data, BigDecimal valorPassagem,
            Integer quantidadePoltronasTotal, Integer quantidadePoltronasOcupadas,
            Estado estado, String codigoAeroportoOrigem, String codigoAeroportoDestino
    ) {
        this.codigo = codigo;
        this.data = data;
        this.valorPassagem = valorPassagem;
        this.quantidadePoltronasTotal = quantidadePoltronasTotal;
        this.quantidadePoltronasOcupadas = quantidadePoltronasOcupadas;
        this.estado = estado;
        this.codigoAeroportoOrigem = codigoAeroportoOrigem;
        this.codigoAeroportoDestino = codigoAeroportoDestino;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public OffsetDateTime getData() {
        return data;
    }

    public void setData(OffsetDateTime data) {
        this.data = data;
    }

    public BigDecimal getValorPassagem() {
        return valorPassagem;
    }

    public void setValorPassagem(BigDecimal valorPassagem) {
        this.valorPassagem = valorPassagem;
    }

    public Integer getQuantidadePoltronasTotal() {
        return quantidadePoltronasTotal;
    }

    public void setQuantidadePoltronasTotal(Integer quantidadePoltronasTotal) {
        this.quantidadePoltronasTotal = quantidadePoltronasTotal;
    }

    public Integer getQuantidadePoltronasOcupadas() {
        return quantidadePoltronasOcupadas;
    }

    public void setQuantidadePoltronasOcupadas(Integer quantidadePoltronasOcupadas) {
        this.quantidadePoltronasOcupadas = quantidadePoltronasOcupadas;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public String getCodigoAeroportoOrigem() {
        return codigoAeroportoOrigem;
    }

    public void setCodigoAeroportoOrigem(String codigoAeroportoOrigem) {
        this.codigoAeroportoOrigem = codigoAeroportoOrigem;
    }

    public String getCodigoAeroportoDestino() {
        return codigoAeroportoDestino;
    }

    public void setCodigoAeroportoDestino(String codigoAeroportoDestino) {
        this.codigoAeroportoDestino = codigoAeroportoDestino;
    }
}
