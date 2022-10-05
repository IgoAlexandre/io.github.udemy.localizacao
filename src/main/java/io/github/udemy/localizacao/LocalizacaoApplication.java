package io.github.udemy.localizacao;

import io.github.udemy.localizacao.domain.entity.Cidade;
import io.github.udemy.localizacao.domain.repository.CidadeRepository;
import io.github.udemy.localizacao.service.CidadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

@SpringBootApplication
public class LocalizacaoApplication implements CommandLineRunner {

	@Autowired
	private CidadeService cidadeService;

	@Override
	public void run(String... args) throws Exception {
		//System.out.println("Iniciar");
		//salvarCidade();
		//listarCidades();

		System.out.println("###########################IGUAL##########################");
		cidadeService.listarCidadesPorNome("Brasilia");
		//listarCidadesPorNomeLike("%pa%");

		System.out.println("###########################CONTÉM##########################");
		cidadeService.listarCidadesPorNomeContaining("pa");

		System.out.println("###########################ORDENADO##########################");
		//Por default é ascending()
		cidadeService.listarCidadesPorNomeSortDescending("%po%", Sort.by("qtdHabitantes").descending());

		System.out.println("###########################Paginado##########################");
		Pageable pageable = PageRequest.of(0, 3);
		cidadeService.listarCidadesPorNomeSortPaginado("%%%%", pageable);


		System.out.println("###########################Utilizando Example##########################");
		var cidade =  new Cidade(null, "Porto Alegre", null);
		cidadeService.filtroDinamico(cidade).forEach(System.out::println);

		System.out.println("###########################Utilizando Specifcation##########################");
		cidadeService.listarCidadesByNomeCidadeSpecs();

		cidadeService.listarCidadesByQtdHabitantesGreaterThan();

		cidadeService.listarCidadesByNomeCidadeAndQtdHabitantesGreaterThan("Brasilia", 5000L);

		System.out.println("###########################Utilizando Specifcation Dinâmico##########################");
		var cidadeDinamico = new Cidade(1L,null,null);
		cidadeService.listarCidadesSpecsDinamico(cidadeDinamico);

		System.out.println("###########################SQL NATIVO##########################");
		cidadeService.listarCidadesPorNomeSqlNativo("Brasilia");

		System.out.println("###########################SQL NATIVO PROJECTION##########################");
		cidadeService.listarCidadesPorNomeSqlNativoProjection("Brasilia");

	}


	public static void main(String[] args) {
		SpringApplication.run(LocalizacaoApplication.class, args);
	}

}
