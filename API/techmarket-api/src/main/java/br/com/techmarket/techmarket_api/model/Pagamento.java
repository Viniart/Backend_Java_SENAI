package br.com.techmarket.techmarket_api.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;

@Getter
@Setter
@Entity
@Table(name = "pagamento", schema = "techmarket")
public class Pagamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pagamento_id", nullable = false)
    private Integer id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "pedido_id", nullable = false)
    private Pedido pedido;

    @NotNull
    @Column(name = "forma_pagamento", nullable = false, length = Integer.MAX_VALUE)
    private String formaPagamento;

    @NotNull
    @Column(name = "status", nullable = false, length = Integer.MAX_VALUE)
    private String status;

    @NotNull
    @Column(name = "data_pagamento", nullable = false)
    private OffsetDateTime dataPagamento;

}