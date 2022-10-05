package io.github.udemy.localizacao.service;

import io.github.udemy.localizacao.domain.entity.Cidade;
import io.github.udemy.localizacao.domain.repository.CidadeRepository;
import static io.github.udemy.localizacao.domain.repository.specs.CidadeSpecs.*;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class CidadeService {

    private CidadeRepository cidadeRepository;

    public CidadeService(CidadeRepository cidadeRepository){
        this.cidadeRepository = cidadeRepository;
    }

    @Transactional
    public void salvarCidade(){
        var cidade = new Cidade(1L,"Brasília", 2000000L);
        cidadeRepository.save(cidade);
    }

    public void listarCidades(){
        cidadeRepository.findAll().forEach(cidade ->
                System.out.println("Id:" + cidade.getIdCidade() +
                        " Cidade: " + cidade.getNomeCidade() +
                        " Habitantes: " + cidade.getQtdHabitantes())
        );
    }

    public void listarCidadesPorNome(String nomeCidade){
        cidadeRepository.findByNomeCidade(nomeCidade).forEach(cidade ->
                System.out.println("Id:" + cidade.getIdCidade() +
                        " Cidade: " + cidade.getNomeCidade() +
                        " Habitantes: " + cidade.getQtdHabitantes())
        );
    }

    public void listarCidadesPorNomeLike(String nomeCidade){
        cidadeRepository.findByNomeCidadeLike(nomeCidade).forEach(cidade ->
                System.out.println("Id:" + cidade.getIdCidade() +
                        " Cidade: " + cidade.getNomeCidade() +
                        " Habitantes: " + cidade.getQtdHabitantes())
        );
    }

    public void listarCidadesPorNomeContaining(String nomeCidade){
        cidadeRepository.findByNomeCidadeContaining(nomeCidade).forEach(cidade ->
                System.out.println("Id:" + cidade.getIdCidade() +
                        " Cidade: " + cidade.getNomeCidade() +
                        " Habitantes: " + cidade.getQtdHabitantes())
        );
    }


    public void listarCidadesPorNomeSort(String nomeCidade, Sort sort){
        cidadeRepository.findByNomeCidadeLike(nomeCidade, sort).forEach(cidade ->
                System.out.println("Id:" + cidade.getIdCidade() +
                        " Cidade: " + cidade.getNomeCidade() +
                        " Habitantes: " + cidade.getQtdHabitantes())
        );
    }

    public void listarCidadesPorNomeSortDescending(String nomeCidade, Sort sort){
        cidadeRepository.findByNomeCidadeLike(nomeCidade, sort).forEach(cidade ->
                System.out.println("Id:" + cidade.getIdCidade() +
                        " Cidade: " + cidade.getNomeCidade() +
                        " Habitantes: " + cidade.getQtdHabitantes())
        );


    }

    public void listarCidadesPorNomeSortPaginado(String nomeCidade, Pageable pageable){
        cidadeRepository.findByNomeCidadeLikePaginado(nomeCidade, pageable).forEach(cidade ->
                System.out.println("Id:" + cidade.getIdCidade() +
                        " Cidade: " + cidade.getNomeCidade() +
                        " Habitantes: " + cidade.getQtdHabitantes())
        );


    }

    public List<Cidade> filtroDinamico(Cidade cidade){
        Example<Cidade> example = Example.of(cidade);
        return cidadeRepository.findAll(example);
    }

    public void listarCidadesByNomeCidadeSpecs(){
        Specification<Cidade> spec = nomeCidadeEqual("São Paulo");
        executar(spec);
    }

    public void listarCidadesByQtdHabitantesGreaterThan(){
        Specification<Cidade> spec = qtdHabitantesGreaterThan(7000000L);
        executar(spec);
    }

    public void listarCidadesByNomeCidadeAndQtdHabitantesGreaterThan(String nomeCidade, Long valor){
        Specification<Cidade> spec = nomeCidadeEqual(nomeCidade).and(qtdHabitantesGreaterThan(valor));
        executar(spec);
    }

    public void listarCidadesSpecsDinamico(Cidade filtro){
        //Mesma coisa que select * from Cidade Where 1 = 1, .conjunction() significa 1 = 1
        Specification<Cidade> specs = Specification.where((root, query, criteriaBuilder) -> criteriaBuilder.conjunction());

        // and id_cidade = filtro.getIdCidade()
        if(filtro.getIdCidade() != null){
            specs = specs.and(idCidadeEqual(filtro.getIdCidade()));
        }

        // and nome_cidade = filtro.getNomeCidade()
        if (StringUtils.hasText(filtro.getNomeCidade())) {
            specs = specs.and(nomeCidadeLike(filtro.getNomeCidade()));
        }

        // and qtd_Habitantes >= filtro.getQtdHabitantes()
        if (filtro.getQtdHabitantes() != null) {
            specs = specs.and(qtdHabitantesGreaterThan(filtro.getQtdHabitantes()));
        }

        executar(specs);
    }

    public void executar(Specification specs){
        cidadeRepository.findAll(specs).forEach(System.out::println);
    }

    public void listarCidadesPorNomeSqlNativo(String nomeCidade){
        cidadeRepository.findByNomeCidadeSqlNativo(nomeCidade).forEach(System.out::println);
    }

    public void listarCidadesPorNomeSqlNativoProjection(String nomeCidade){
        cidadeRepository.findByNomeCidadeSqlNativoProjection(nomeCidade)
                .stream().map(cidade -> new Cidade(cidade.getIdCidade(), cidade.getNomeCidade(), null))
                .forEach(System.out::println);
    }

}
