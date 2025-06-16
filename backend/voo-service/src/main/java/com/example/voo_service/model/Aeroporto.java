package com.example.voo_service.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Aeroporto {
    @Id
    private String codigo;
    private String nome;
    private String cidade;
    private String uf;

}
