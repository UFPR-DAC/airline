package com.example.reserva_service.comando.model;

import com.example.reserva_service.comando.enums.EstadoReservaComando;
import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Table(name = "tb_reserva")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class ReservaComando {
    @Id
    private String codigo; // Ex: XPT789
    private Long codigoCliente;
    private String codigoVoo;
    private Instant dataReserva;
    private BigDecimal valor;
    private Integer milhasUtilizadas;
    private Integer quantidadePoltronas;
    @Enumerated(EnumType.STRING)
    private EstadoReservaComando estado;
}