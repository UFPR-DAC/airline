package com.example.client_service.dto;

import com.example.client_service.model.TransacaoMilha;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
public class TransacaoDTO {
    private Instant data;
    private BigDecimal valor_reais;
    private BigDecimal quantidade_milhas;
    private String descricao;
    private String codigo_reserva;
    private String tipo;

    public TransacaoDTO(TransacaoMilha entity) {
        this.data = entity.getData();
        this.valor_reais = entity.getValorReais();
        this.quantidade_milhas = entity.getQuantidadeMilhas();
        this.descricao = entity.getDescricao();
        this.codigo_reserva = entity.getCodigoReserva();
        this.tipo = entity.getTipo().name();
    }
}