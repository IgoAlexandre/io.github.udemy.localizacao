package io.github.udemy.localizacao.service;

import io.github.udemy.localizacao.domain.entity.Cidade;
import io.github.udemy.localizacao.domain.repository.CidadeRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
}
