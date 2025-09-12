package br.com.urbanswift.api.model;



import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "veiculo")
public class Veiculo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "veiculo_id", nullable = false)
    private Integer veiculoId;

    // ... outros campos ...
    @Column(name = "placa", nullable = false, unique = true, length = 10)
    private String placa;
    @Column(name = "modelo", nullable = false)
    private String modelo;
    @Column(name = "tipo", nullable = false, length = 50)
    private String tipo;

    // Hibernate: Adiciona "ON DELETE CASCADE" à chave estrangeira 'entregador_id'.
    // Efeito: Se o 'usuario' (entregador) for deletado, todos os seus 'veiculos' associados
    // serão removidos em cascata diretamente no banco de dados.
    @OnDelete(action = OnDeleteAction.CASCADE)
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "entregador_id", nullable = false)
    private Usuario entregador;
}