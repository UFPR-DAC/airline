package com.example.client_service.dto;

import com.example.client_service.model.TransacaoMilha;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.time.Instant;

@NoArgsConstructor
public class TransacaoDTO {
    private Instant data;
    private BigDecimal valor_reais;
    private BigDecimal quantidade_milhas;
    private String descricao;
    private String codigo_reserva;
    private String tipo;

    public TransacaoDTO(TransacaoMilha transacaoMilha) {
        this.data = transacaoMilha.getData();
        this.valor_reais = transacaoMilha.getValorReais();
        this.quantidade_milhas = transacaoMilha.getQuantidadeMilhas();
        this.descricao = transacaoMilha.getDescricao();
        this.codigo_reserva = transacaoMilha.getCodigoReserva();
        this.tipo = transacaoMilha.getTipo().name();
    }

    public Instant getData() {
        return data;
    }

    public void setData(Instant data) {
        this.data = data;
    }

    public BigDecimal getValor_reais() {
        return valor_reais;
    }

    public void setValor_reais(BigDecimal valor_reais) {
        this.valor_reais = valor_reais;
    }

    public BigDecimal getQuantidade_milhas() {
        return quantidade_milhas;
    }

    public void setQuantidade_milhas(BigDecimal quantidade_milhas) {
        this.quantidade_milhas = quantidade_milhas;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getCodigo_reserva() {
        return codigo_reserva;
    }

    public void setCodigo_reserva(String codigo_reserva) {
        this.codigo_reserva = codigo_reserva;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    
}