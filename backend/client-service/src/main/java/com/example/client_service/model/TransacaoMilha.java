package com.example.client_service.model;

import com.example.client_service.enums.TipoTransacao;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.OffsetDateTime;

@Entity
@Table(name = "tb_transacao_milha")
@NoArgsConstructor
@AllArgsConstructor
public class TransacaoMilha {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_codigo", nullable = false)
    private Cliente cliente;

    @Column(nullable = false)
    private OffsetDateTime data;

    @JsonProperty("valor_reais")
    private BigDecimal valorReais;

    @Column(nullable = false)
    @JsonProperty("quantidade_milhas")
    private Integer quantidadeMilhas;

    @Column(nullable = false)
    private String descricao;

    @JsonProperty("codigo_reserva")
    private String codigoReserva;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoTransacao tipo;

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }


    public OffsetDateTime getData() {
        return data;
    }

    public void setData(OffsetDateTime data) {
        this.data = data;
    }

    public BigDecimal getValorReais() {
        return valorReais;
    }

    public void setValorReais(BigDecimal valorReais) {
        this.valorReais = valorReais;
    }

    public Integer getQuantidadeMilhas() {
        return quantidadeMilhas;
    }

    public void setQuantidadeMilhas(Integer quantidadeMilhas) {
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

    public TipoTransacao getTipo() {
        return tipo;
    }

    public void setTipo(TipoTransacao tipo) {
        this.tipo = tipo;
    }
}