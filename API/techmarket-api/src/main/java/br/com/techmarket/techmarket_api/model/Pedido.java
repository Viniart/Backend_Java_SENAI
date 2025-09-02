package br.com.techmarket.techmarket_api.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Getter
@Setter
@Entity
@Table(name = "pedido", schema = "techmarket")
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pedido_id", nullable = false)
    private Integer id;

    @NotNull
    @Column(name = "data_pedido", nullable = false)
    private OffsetDateTime dataPedido;

    @NotNull
    @Column(name = "valor_total", nullable = false, precision = 18, scale = 4)
    private BigDecimal valorTotal;

    @NotNull
    @Column(name = "status", nullable = false, length = Integer.MAX_VALUE)
    private String status;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;

}