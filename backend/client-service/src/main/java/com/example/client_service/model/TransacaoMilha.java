package com.example.client_service.model;

import com.example.client_service.enums.TipoTransacao;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Table(name = "tb_transacao_milha")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TransacaoMilha {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_codigo", nullable = false)
    private Cliente cliente;

    @Column(nullable = false)
    private Instant data;

    private BigDecimal valorReais;

    @Column(nullable = false)
    private BigDecimal quantidadeMilhas;

    @Column(nullable = false)
    private String descricao;

    private String codigoReserva;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoTransacao tipo;
}