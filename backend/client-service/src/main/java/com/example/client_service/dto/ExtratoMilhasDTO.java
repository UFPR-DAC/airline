package com.example.client_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

public class ExtratoMilhasDTO {
    private Long codigo;
    private BigDecimal saldoMilhas;
    private List<TransacaoDTO> transacoes;

    public ExtratoMilhasDTO(Long codigo, BigDecimal saldoMilhas, List<TransacaoDTO> transacoesDTO) {
        this.codigo = codigo;
        this.saldoMilhas = saldoMilhas;
        this.transacoes = transacoesDTO;
    }

    public ExtratoMilhasDTO() {

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

    public List<TransacaoDTO> getTransacoes() {
        return transacoes;
    }

    public void setTransacoes(List<TransacaoDTO> transacoes) {
        this.transacoes = transacoes;
    }
}