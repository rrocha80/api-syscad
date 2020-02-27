package br.com.softplan.api.util;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Component;

import br.com.softplan.api.dao.PessoaDao;
import br.com.softplan.api.domain.Pessoa;

@Component
public class PessoaServiceImpl {
	@Autowired
	private PessoaDao pessoaDao;

	
	@SuppressWarnings("unused")
	private Iterable<Pessoa> findByCpf(String cpf) {
		Pessoa pessoa = new Pessoa();
		pessoa.setCpf(cpf);
		
		Example<Pessoa> pessoaBuascarList = Example.of(pessoa);
		
		Iterable<Pessoa> pessoaList = pessoaDao.findAll(pessoaBuascarList);
		
		return pessoaList;
		
		
	}


	public PessoaServiceImpl() {
		super();
		// TODO Auto-generated constructor stub
		
		
	}

}
