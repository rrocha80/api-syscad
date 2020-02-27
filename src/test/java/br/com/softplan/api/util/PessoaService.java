package br.com.softplan.api.util;

import java.util.List;

import br.com.softplan.api.domain.Pessoa;

public interface PessoaService {
	List<Pessoa> findByCpf(String cpf);

}
