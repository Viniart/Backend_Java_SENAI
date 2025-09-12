package br.com.urbanswift.api.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "usuario_id", nullable = false)
    private Integer usuarioId;

    @Column(name = "nome_completo", nullable = false)
    private String nomeCompleto;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "senha", nullable = false)
    private String senha;

    /*
     * Nota sobre ON DELETE para este relacionamento:
     * Propositalmente, NÃO adicionamos @OnDelete(action = OnDeleteAction.CASCADE) aqui.
     * O comportamento padrão do banco de dados (RESTRICT/NO ACTION) é o desejado.
     * Isso impede que um 'tipos_usuario' (ex: "Cliente") seja deletado se houver qualquer
     * 'usuario' associado a ele, garantindo a integridade do sistema.
     */
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "tipo_usuario_id", nullable = false)
    private TipoUsuario tipoUsuario;

    /*
     * =====================================================================================
     * EXEMPLO DE MAPEAMENTO BIDIRECIONAL (O LADO "UM") - MANTIDO COMENTADO
     * =====================================================================================
     * Para habilitar a navegação de Usuario para suas entidades filhas (ex: usuario.getEnderecos()),
     * você pode descomentar os blocos abaixo. Isso caracteriza uma relação bidirecional.
     *
     * @OneToMany: Define a relação de "um-para-muitos". Um Usuário para muitos Endereços.
     * mappedBy = "cliente": ESSENCIAL. Informa ao JPA que a relação já está mapeada
     * pelo campo "cliente" na entidade Endereco. Isso evita a criação de uma tabela de junção.
     * O lado com "mappedBy" é sempre o inverso, o que não possui a chave estrangeira.
     * cascade = CascadeType.ALL: Propaga operações (salvar, deletar) do Usuário para seus Endereços.
     * orphanRemoval = true: Remove do banco um Endereço que foi desassociado desta lista de Usuário.
     */
    // @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true)
    // private List<Endereco> enderecos;
}