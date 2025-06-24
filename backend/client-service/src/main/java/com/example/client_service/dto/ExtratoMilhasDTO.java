package com.example.client_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

public class ExtratoMilhasDTO {
    private Long codigo;
    private Integer saldoMilhas;
    private List<TransacaoDTO> transacoes;

    public ExtratoMilhasDTO(Long codigo, Integer saldoMilhas, List<TransacaoDTO> transacoesDTO) {
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

    public Integer getSaldoMilhas() {
        return saldoMilhas;
    }

    public void setSaldoMilhas(Integer saldoMilhas) {
        this.saldoMilhas = saldoMilhas;
    }

    public List<TransacaoDTO> getTransacoes() {
        return transacoes;
    }

    public void setTransacoes(List<TransacaoDTO> transacoes) {
        this.transacoes = transacoes;
    }
}