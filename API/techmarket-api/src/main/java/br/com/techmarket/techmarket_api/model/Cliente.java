package br.com.techmarket.techmarket_api.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;

@Getter
@Setter
@Entity
@Table(name = "cliente", schema = "techmarket")
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cliente_id", nullable = false)
    private Integer id;

    @NotNull
    @Column(name = "nome_completo", nullable = false, length = Integer.MAX_VALUE)
    private String nomeCompleto;

    @NotNull
    @Column(name = "senha", nullable = false, length = Integer.MAX_VALUE)
    private String senha;

    @Column(name = "telefone", length = Integer.MAX_VALUE)
    private String telefone;

    @Column(name = "data_cadastro")
    private OffsetDateTime dataCadastro;

}