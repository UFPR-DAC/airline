package com.example.client_service.model;

import com.example.client_service.enums.EnumTipoTransacao;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Table(name = "tb_transacao_milha")
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
    private EnumTipoTransacao tipo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Instant getData() {
        return data;
    }

    public void setData(Instant data) {
        this.data = data;
    }

    public BigDecimal getValorReais() {
        return valorReais;
    }

    public void setValorReais(BigDecimal valorReais) {
        this.valorReais = valorReais;
    }

    public BigDecimal getQuantidadeMilhas() {
        return quantidadeMilhas;
    }

    public void setQuantidadeMilhas(BigDecimal quantidadeMilhas) {
        this.quantidadeMilhas = quantidadeMilhas;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getCodigoReserva() {
        return codigoReserva;
    }

    public void setCodigoReserva(String codigoReserva) {
        this.codigoReserva = codigoReserva;
    }

    public EnumTipoTransacao getTipo() {
        return tipo;
    }

    public void setTipo(EnumTipoTransacao tipo) {
        this.tipo = tipo;
    }
}