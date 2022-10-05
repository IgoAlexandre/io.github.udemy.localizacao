package io.github.udemy.localizacao.domain.repository;

import io.github.udemy.localizacao.domain.entity.Cidade;
import io.github.udemy.localizacao.domain.repository.projections.CidadeProjection;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CidadeRepository extends JpaRepository<Cidade, Long>, JpaSpecificationExecutor<Cidade> {

    @Query(nativeQuery = true, value = "Select * from Cidade c where c.nome_cidade =:nomeCidade")
    List<Cidade> findByNomeCidadeSqlNativo(@Param("nomeCidade") String nomeCidade);

    @Query(nativeQuery = true, value = "Select id_cidade IdCidade, nome_cidade NomeCidade from Cidade c where c.nome_cidade =:nomeCidade")
    List<CidadeProjection> findByNomeCidadeSqlNativoProjection(@Param("nomeCidade") String nomeCidade);

    List<Cidade> findByNomeCidade(String nomeCidade);

    // busca pelo nome comecando por aquele pedaço
    List<Cidade> findByNomeCidadeStartingWith(String nome);

    // busca pelo nome terminando por aquele pedaço
    List<Cidade> findByNomeCidadeEndingWith(String nome);

    // busca pelo nome contendo aquele pedaço
    List<Cidade> findByNomeCidadeContaining(String nome);

    List<Cidade> findByNomeCidadeLike(String nomeCidade);

    // busca pelo nome like ordenado
    @Query(" select c from Cidade c where upper(c.nomeCidade) like upper(?1) ")
    List<Cidade> findByNomeCidadeLike(String nome, Sort sort);

    // busca pelo nome like ordenado
    @Query(" select c from Cidade c where upper(c.nomeCidade) like upper(?1) ")
    List<Cidade> findByNomeCidadeLikePaginado(String nome, Pageable pageable);

    List<Cidade> findByQtdHabitantesLessThan(Long habitantes);

    List<Cidade> findByQtdHabitantesGreaterThan(Long habitantes);

    List<Cidade> findByQtdHabitantesLessThanEqual(Long habitantes);

    List<Cidade> findByQtdHabitantesLessThanAndNomeCidadeLike(Long habitantes, String nome);

}
