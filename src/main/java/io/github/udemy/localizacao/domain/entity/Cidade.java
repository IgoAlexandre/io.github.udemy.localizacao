package io.github.udemy.localizacao.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "cidade")
public class Cidade {

    @Id
    //@GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_cidade")
    private Long idCidade;

    @Column(name = "nome_cidade", length = 50)
    private String nomeCidade;

    @Column(name = "qtd_habitantes" )
    private Long qtdHabitantes;
}
