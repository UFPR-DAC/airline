package com.example.reserva_service.client.dto;

import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;
import java.time.Instant;

@Getter
@Setter
public class VooDTO {
    private String codigo;
    private Instant data;
    private BigDecimal valorPassagem;
    private Integer quantidadePoltronasTotal;
    private Integer quantidadePoltronasOcupadas;
    private String estado;
    private AeroportoDTO aeroportoOrigem;
    private AeroportoDTO aeroportoDestino;

    @Getter
    @Setter
    public static class AeroportoDTO {
        private String codigo;
        private String nome;
        private String cidade;
        private String uf;
    }
}
