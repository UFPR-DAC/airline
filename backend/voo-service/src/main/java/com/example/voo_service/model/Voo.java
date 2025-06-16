package com.example.voo_service.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Voo {
    @Id
    private String codigo;
    private OffsetDateTime data;
    @JsonProperty("valor_passagem")
    private BigDecimal valorPassagem;
    @JsonProperty("quantidade_poltronas_total")
    private Integer quantidadePoltronasTotal;
    @JsonProperty("quantidade_poltronas_ocupadas")
    private Integer quantidadePoltronasOcupadas;
    @Enumerated(EnumType.STRING)
    private Estado estado;
    @ManyToOne
    @JoinColumn(name = "codigo_aeroporto_origem", nullable = false)
    @JsonProperty("aeroporto_origem")
    private Aeroporto aeroportoOrigem;
    @ManyToOne
    @JoinColumn(name = "codigo_aeroporto_destino", nullable = false)
    @JsonProperty("aeroporto_destino")
    private Aeroporto aeroportoDestino;

}