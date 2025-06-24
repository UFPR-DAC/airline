package com.example.reserva_service.consulta.model;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Table(name = "vw_reserva", schema = "consulta") // Aponta para o schema 'consulta'
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class ReservaConsulta {
    @Id
    private String codigoReserva;
    private Instant dataReserva;
    private BigDecimal valor;
    private Integer milhasUtilizadas;
    private Integer quantidadePoltronas;
    private Long codigoCliente;
    private String estadoReserva;

    // Dados desnormalizados do Voo
    private String codigoVoo;
    private Instant dataVoo;
    private BigDecimal valorPassagem;
    private String origemCodigoAeroporto;
    private String origemNomeAeroporto;
    private String origemCidade;
    private String origemUf;
    private String destinoCodigoAeroporto;
    private String destinoNomeAeroporto;
    private String destinoCidade;
    private String destinoUf;
}