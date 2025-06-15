package com.example.client_service.dto;

import lombok.AllArgsConstructor;
import java.math.BigDecimal;
import java.util.List;

@AllArgsConstructor
public class ExtratoMilhasDTO {
    private Long codigo;
    private BigDecimal saldo_milhas;
    private List<TransacaoDTO> transacoes;
    
    public Long getCodigo() {
        return codigo;
    }
    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }
    public BigDecimal getSaldo_milhas() {
        return saldo_milhas;
    }
    public void setSaldo_milhas(BigDecimal saldo_milhas) {
        this.saldo_milhas = saldo_milhas;
    }
    public List<TransacaoDTO> getTransacoes() {
        return transacoes;
    }
    public void setTransacoes(List<TransacaoDTO> transacoes) {
        this.transacoes = transacoes;
    }

}