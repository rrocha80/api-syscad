package br.com.softplan.api.dao;

import java.util.List;

import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.softplan.api.domain.Pessoa;

public interface PessoaDao extends JpaRepository<Pessoa, Long>{
	
	public default List<Pessoa> findByAttributes(Pessoa pessoaByCpf) {
		
		Example<Pessoa> pessoaBuscarList = Example.of(pessoaByCpf);
		List<Pessoa> pessoaList = findAll(pessoaBuscarList);
		
		return pessoaList;
		
	}

	
}
