package br.com.techmarket.techmarket_api.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "produto", schema = "techmarket")
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "produto_id", nullable = false)
    private Integer id;

    @NotNull
    @Column(name = "nome_produto", nullable = false, length = Integer.MAX_VALUE)
    private String nomeProduto;

    @Column(name = "descricao", length = Integer.MAX_VALUE)
    private String descricao;

    @NotNull
    @Column(name = "preco", nullable = false, precision = 14, scale = 4)
    private BigDecimal preco;

    @NotNull
    @Column(name = "estoque_disponivel", nullable = false)
    private Integer estoqueDisponivel;

    @Column(name = "imagem_url", length = Integer.MAX_VALUE)
    private String imagemUrl;

}