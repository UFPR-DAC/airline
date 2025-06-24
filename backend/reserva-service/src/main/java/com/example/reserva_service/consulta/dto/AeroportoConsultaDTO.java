package com.example.reserva_service.consulta.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AeroportoConsultaDTO {
    private String codigo;
    private String nome;
    private String cidade;
    private String uf;
}