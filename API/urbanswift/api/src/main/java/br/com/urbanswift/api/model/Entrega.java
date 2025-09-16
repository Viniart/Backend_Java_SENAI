package br.com.urbanswift.api.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import java.time.OffsetDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "entrega")
public class Entrega {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "entrega_id", nullable = false)
    private Integer entregaId;

    // ... outros campos ...
    @Column(name = "descricao_produto", nullable = false)
    private String descricaoProduto;
    @Column(name = "status", nullable = false, length = 30)
    private String status;
    @Column(name = "data_pedido", nullable = false)
    private OffsetDateTime dataPedido;

    // Hibernate: Adiciona "ON DELETE CASCADE" à chave estrangeira 'cliente_id'.
    // Efeito: Se o 'usuario' (cliente) for deletado, todos os registros de 'entregas'
    // feitos por ele serão também deletados.
    @OnDelete(action = OnDeleteAction.CASCADE)
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "cliente_id", nullable = false)
    private Usuario cliente;

    // Hibernate: Adiciona "ON DELETE CASCADE" à chave estrangeira 'endereco_id'.
    // Efeito: Se um 'endereco' for deletado (o que já aconteceria em cascata se o cliente fosse deletado),
    // qualquer registro de 'entregas' apontando para ele também será deletado.
    @OnDelete(action = OnDeleteAction.CASCADE)
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "endereco_id", nullable = false)
    private Endereco endereco;
}