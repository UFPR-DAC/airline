package com.example.reserva_service.comando.dto;

import java.math.BigDecimal;

public class ReservaComandoDTO {
    private Long codigo_cliente;
    private BigDecimal valor;
    private Integer milhas_utilizadas;
    private Integer quantidade_poltronas;
    private String codigo_voo;
    private String codigo_aeroporto_origem;
    private String codigo_aeroporto_destino;

    public Long getCodigo_cliente() {
        return codigo_cliente;
    }

    public void setCodigo_cliente(Long codigo_cliente) {
        this.codigo_cliente = codigo_cliente;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public Integer getMilhas_utilizadas() {
        return milhas_utilizadas;
    }

    public void setMilhas_utilizadas(Integer milhas_utilizadas) {
        this.milhas_utilizadas = milhas_utilizadas;
    }

    public Integer getQuantidade_poltronas() {
        return quantidade_poltronas;
    }

    public void setQuantidade_poltronas(Integer quantidade_poltronas) {
        this.quantidade_poltronas = quantidade_poltronas;
    }

    public String getCodigo_voo() {
        return codigo_voo;
    }

    public void setCodigo_voo(String codigo_voo) {
        this.codigo_voo = codigo_voo;
    }

    public String getCodigo_aeroporto_origem() {
        return codigo_aeroporto_origem;
    }

    public void setCodigo_aeroporto_origem(String codigo_aeroporto_origem) {
        this.codigo_aeroporto_origem = codigo_aeroporto_origem;
    }

    public String getCodigo_aeroporto_destino() {
        return codigo_aeroporto_destino;
    }

    public void setCodigo_aeroporto_destino(String codigo_aeroporto_destino) {
        this.codigo_aeroporto_destino = codigo_aeroporto_destino;
    }
}