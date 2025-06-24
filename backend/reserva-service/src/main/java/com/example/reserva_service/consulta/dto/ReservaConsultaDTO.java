package com.example.reserva_service.consulta.dto;

import com.example.reserva_service.consulta.model.ReservaConsulta;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
public class ReservaConsultaDTO {
    private String codigo;
    private Instant data;
    private BigDecimal valor;
    private Integer milhas_utilizadas;
    private Integer quantidade_poltronas;
    private Long codigo_cliente;
    private String estado;
    private VooConsultaDTO voo;

    // Construtor que transforma a entidade de Leitura (View) em DTO de resposta
    public ReservaConsultaDTO(ReservaConsulta view) {
        this.codigo = view.getCodigoReserva();
        this.data = view.getDataReserva();
        this.valor = view.getValor();
        this.milhas_utilizadas = view.getMilhasUtilizadas();
        this.quantidade_poltronas = view.getQuantidadePoltronas();
        this.codigo_cliente = view.getCodigoCliente();
        this.estado = view.getEstadoReserva();

        VooConsultaDTO vooDTO = new VooConsultaDTO();
        vooDTO.setCodigo(view.getCodigoVoo());
        vooDTO.setData(view.getDataVoo());
        vooDTO.setValor_passagem(view.getValorPassagem());
        // ... outros campos do voo a partir da view

        AeroportoConsultaDTO origem = new AeroportoConsultaDTO();
        origem.setCodigo(view.getOrigemCodigoAeroporto());
        origem.setNome(view.getOrigemNomeAeroporto());
        origem.setCidade(view.getOrigemCidade());
        origem.setUf(view.getOrigemUf());

        AeroportoConsultaDTO destino = new AeroportoConsultaDTO();
        destino.setCodigo(view.getDestinoCodigoAeroporto());
        destino.setNome(view.getDestinoNomeAeroporto());
        destino.setCidade(view.getDestinoCidade());
        destino.setUf(view.getDestinoUf());

        vooDTO.setAeroporto_origem(origem);
        vooDTO.setAeroporto_destino(destino);

        this.voo = vooDTO;
    }
}