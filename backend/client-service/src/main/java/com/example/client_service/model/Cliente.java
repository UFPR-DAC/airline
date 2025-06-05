package com.example.client_service.model;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cliente {
    @Id
    private Long id;
    private String cpf;
    private String nome;
    private String email;
    @Column(name = "saldo_milhas")
    private int saldoMilhas;
    @OneToOne(cascade = CascadeType.ALL
    @JoinColumn(name = "endereco_id", referencedColumnName = "id")
    private Endereco endereco;
    private String telefone;
}