package br.com.ecommerce.api;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "medico", schema = "clinica")
public class Medico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_medico", nullable = false)
    private Integer id;

    @Column(name = "nome", nullable = false, length = Integer.MAX_VALUE)
    private String nome;

    @Column(name = "crm", nullable = false, length = Integer.MAX_VALUE)
    private String crm;

    @Column(name = "especialidade", nullable = false, length = Integer.MAX_VALUE)
    private String especialidade;

}