package br.com.urbanswift.api.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// Lombok: Gera automaticamente os getters para todos os campos.
@Getter
// Lombok: Gera automaticamente os setters para todos os campos.
@Setter
// Lombok: Gera um construtor sem argumentos, exigido pelo JPA.
@NoArgsConstructor
// Lombok: Gera um construtor com todos os campos como argumentos.
@AllArgsConstructor
// JPA: Marca esta classe como uma entidade, que será gerenciada e mapeada para uma tabela no banco de dados.
@Entity
// JPA: Especifica o nome da tabela no banco de dados, agora em snake_case.
@Table(name = "tipo_usuario")
public class TipoUsuario {

    // JPA: Define este campo como a chave primária (PK) da tabela.
    @Id
    // JPA: Configura a estratégia de geração da PK. IDENTITY delega ao banco de dados a tarefa de auto-incrementar o valor.
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // JPA: Mapeia o campo para a coluna 'tipo_usuario_id' e define que não pode ser nula.
    @Column(name = "tipo_usuario_id", nullable = false)
    private Integer tipoUsuarioId;

    // JPA: Mapeia o campo para a coluna 'descricao', define que não pode ser nula e especifica o tamanho máximo.
    @Column(name = "descricao", nullable = false, length = 50)
    private String descricao;
}