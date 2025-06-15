package com.example.reserva_service.consulta.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.math.BigDecimal;
import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
public class VooConsultaDTO {
    private String codigo;
    private Instant data;
    private BigDecimal valor_passagem;
    private Integer quantidade_poltronas_total;
    private Integer quantidade_poltronas_ocupadas;
    private String estado;
    private AeroportoConsultaDTO aeroporto_origem;
    private AeroportoConsultaDTO aeroporto_destino;
}