package com.example.client_service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class ExtratoMilhasDTO {
    private Long codigo;
    private BigDecimal saldo_milhas;
    private List<TransacaoDTO> transacoes;
}