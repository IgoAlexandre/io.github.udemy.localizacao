package io.github.udemy.localizacao.domain.repository.specs;

import io.github.udemy.localizacao.domain.entity.Cidade;
import org.springframework.data.jpa.domain.Specification;

public abstract class CidadeSpecs {

    public static Specification<Cidade> nomeCidadeEqual(String nomeCidade){
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("nomeCidade"), nomeCidade);
    }

    public static Specification<Cidade> qtdHabitantesGreaterThan(Long valor){
        return (root, query, criteriaBuilder) -> criteriaBuilder.greaterThan(root.get("qtdHabitantes"), valor);
    }

    public static Specification<Cidade> qtdHabitantesBeetwen(Long min, Long max){
        return (root, query, criteriaBuilder) -> criteriaBuilder.between(root.get("qtdHabitantes"), min, max);
    }

    public static Specification<Cidade> idCidadeEqual(Long idCidade){
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("idCidade"), idCidade);
    }

    public static Specification<Cidade> nomeCidadeLike(String nomeCidade){
        System.out.println(nomeCidade.toUpperCase());
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(criteriaBuilder.upper(root.get("nomeCidade")), "%" + nomeCidade + "%".toUpperCase());
    }
}
