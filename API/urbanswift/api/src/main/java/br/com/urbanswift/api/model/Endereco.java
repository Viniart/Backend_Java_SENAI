package br.com.urbanswift.api.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "endereco")
public class Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "endereco_id", nullable = false)
    private Integer enderecoId;

    @Column(name = "logradouro", nullable = false, columnDefinition = "TEXT")
    private String logradouro;

    @Column(name = "numero", nullable = false, length = 20)
    private String numero;

    @Column(name = "cidade", nullable = false)
    private String cidade;

    @Column(name = "cep", nullable = false, length = 9)
    private String cep;

    // JPA: Define um relacionamento MUITOS-PARA-UM. Muitos endereços podem pertencer a um cliente (Usuário).
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    // JPA: Especifica a coluna da chave estrangeira ('cliente_id') nesta tabela, que referencia a PK da tabela 'usuarios'.
    @JoinColumn(name = "cliente_id",
            nullable = false,
            foreignKey = @ForeignKey(name = "fk_endereco_usuario") )
    private Usuario cliente;
}