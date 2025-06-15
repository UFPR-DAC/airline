package com.example.reserva_service.comando.dto;

import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;

@Getter
@Setter
public class ReservaComandoDTO {
    private Long codigo_cliente;
    private BigDecimal valor;
    private Integer milhas_utilizadas;
    private Integer quantidade_poltronas;
    private String codigo_voo;
    private String codigo_aeroporto_origem;
    private String codigo_aeroporto_destino;
}