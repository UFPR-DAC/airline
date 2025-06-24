package com.example.reserva_service.comando.model;

import com.example.reserva_service.comando.enums.EstadoReserva;
import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Table(name = "tb_reserva")
@NoArgsConstructor 
@AllArgsConstructor
public class ReservaComando {
    @Id
    private String codigo;
    private Long codigoCliente;
    private String codigoVoo;
    private Instant dataReserva;
    private BigDecimal valor;
    private Integer milhasUtilizadas;
    private Integer quantidadePoltronas;
    @Enumerated(EnumType.STRING)
    private EstadoReserva estado;
    
    public String getCodigo() {
        return codigo;
    }
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
    public Long getCodigoCliente() {
        return codigoCliente;
    }
    public void setCodigoCliente(Long codigoCliente) {
        this.codigoCliente = codigoCliente;
    }
    public String getCodigoVoo() {
        return codigoVoo;
    }
    public void setCodigoVoo(String codigoVoo) {
        this.codigoVoo = codigoVoo;
    }
    public Instant getDataReserva() {
        return dataReserva;
    }
    public void setDataReserva(Instant dataReserva) {
        this.dataReserva = dataReserva;
    }
    public BigDecimal getValor() {
        return valor;
    }
    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }
    public Integer getMilhasUtilizadas() {
        return milhasUtilizadas;
    }
    public void setMilhasUtilizadas(Integer milhasUtilizadas) {
        this.milhasUtilizadas = milhasUtilizadas;
    }
    public Integer getQuantidadePoltronas() {
        return quantidadePoltronas;
    }
    public void setQuantidadePoltronas(Integer quantidadePoltronas) {
        this.quantidadePoltronas = quantidadePoltronas;
    }
    public EstadoReserva getEstado() {
        return estado;
    }
    public void setEstado(EstadoReserva estado) {
        this.estado = estado;
    }

    
}