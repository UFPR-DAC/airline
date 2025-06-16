package com.example.voo_service.dto;

import java.time.OffsetDateTime;
import java.util.List;

public class VoosPorRotaDTO {
    private OffsetDateTime data;
    private String origem;
    private String destino;
    private List<VooDTO> voos;

    public OffsetDateTime getData() {
        return data;
    }

    public void setData(OffsetDateTime data) {
        this.data = data;
    }

    public String getOrigem() {
        return origem;
    }

    public void setOrigem(String origem) {
        this.origem = origem;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public List<VooDTO> getVoos() {
        return voos;
    }

    public void setVoos(List<VooDTO> voos) {
        this.voos = voos;
    }
}
