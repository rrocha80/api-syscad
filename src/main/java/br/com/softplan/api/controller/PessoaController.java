package br.com.softplan.api.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.softplan.api.dao.PessoaDao;
import br.com.softplan.api.domain.Pessoa;
import br.com.softplan.api.domain.Usuario;
import br.com.softplan.api.error.ResourceNotFoundException;
import br.com.softplan.api.util.ClasseValidacoes;

@RestController
@RequestMapping("/api-syscad/pessoa")
public class PessoaController {

	@Autowired
	private PessoaDao pessoaDao;

	private List<Pessoa> pessoaList = new ArrayList<>();
	private Pessoa buscarPessoa;
	private Pessoa pessoa = new Pessoa();

	@CrossOrigin
	@RequestMapping(value = "/buscar/{id}", method = RequestMethod.GET)
	public ResponseEntity<Pessoa> GetById(@PathVariable(value = "id") long id) {
		Optional<Pessoa> pessoa = pessoaDao.findById(id);
		if (pessoa != null)
			return new ResponseEntity<Pessoa>(HttpStatus.OK);
		else
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@CrossOrigin
	@RequestMapping("/pesquisar")
	public void GetByCpf(@RequestBody Pessoa pessoa) {
		pessoaList = new ArrayList<>();
		pessoaList = pessoaDao.findByAttributes(pessoa);
		listar();

	}

	@CrossOrigin
	@RequestMapping(value = "/deletar/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Object> Delete(@PathVariable(value = "id") long id) {
		Optional<Pessoa> pessoa = pessoaDao.findById(id);
		if (pessoa != null) {
			pessoaDao.deleteById(id);
			return new ResponseEntity<>(HttpStatus.OK);
		} else
			throw new ResourceNotFoundException("Pessoa não encontrada!");
	}

	@CrossOrigin
	@RequestMapping(value = "/atualizar/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Pessoa> Put(@PathVariable(value = "id") long id, @Valid @RequestBody Pessoa newPessoa) {
		ClasseValidacoes validar = new ClasseValidacoes();
		Optional<Pessoa> pessoa = pessoaDao.findById(id);

		buscarPessoa = new Pessoa();
		buscarPessoa.setCpf(newPessoa.getCpf());

		pessoaList = pessoaDao.findByAttributes(buscarPessoa);

		if (!pessoaList.isEmpty()) {
			if (pessoaList.get(0).getId() != newPessoa.getId()) {
				throw new ResourceNotFoundException("Pessoa já existe!");
			}
		}

		if (pessoa != null) {
			// Validar Email
			if (pessoa.get().getEmail() != null && pessoa.get().getEmail() != "") {
				validar = new ClasseValidacoes();
				if (!validar.isValidEmailAddress(pessoa.get().getEmail())) {
					return new ResponseEntity<>(HttpStatus.NOT_FOUND);
				}
			}

			// validar CPF
			if (!validar.ValidarCPF(pessoa.get().getCpf().replace(".", "").replace("-", ""))) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
			newPessoa.setDataCadastro(pessoa.get().getDataCadastro());
			newPessoa.setDataAtualizacao(new Date());
			pessoaDao.save(newPessoa);
			return new ResponseEntity<Pessoa>(newPessoa, HttpStatus.OK);
		} else
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@CrossOrigin
	@PostMapping("/adicionar")
	public ResponseEntity<Pessoa> adicionar(@RequestBody Pessoa pessoa) {

		buscarPessoa = new Pessoa();
		buscarPessoa.setCpf(pessoa.getCpf());

		List<Pessoa> pessoaList = pessoaDao.findByAttributes(buscarPessoa);
		ClasseValidacoes validar = new ClasseValidacoes();

		if (!pessoaList.isEmpty()) {
			throw new ResourceNotFoundException("Pessoa já existe!");
		}

		// Validar Email
		if (buscarPessoa.getEmail() != null && buscarPessoa.getEmail() != "") {
			validar = new ClasseValidacoes();
			if (!validar.isValidEmailAddress(pessoa.getEmail())) {
				throw new ResourceNotFoundException("e-mail inválido!");
			}
		}

		// validar CPF
		if (!validar.ValidarCPF(buscarPessoa.getCpf().replace(".", "").replace("-", ""))) {
			throw new ResourceNotFoundException("CPF inválido!");
		}

		pessoa.setDataCadastro(new Date());
		try {
			// pessoa.setId(null);
			this.pessoaDao.save(pessoa);
		} catch (Exception e) {
			// TODO: handle exception
			throw new ResourceNotFoundException(e.getMessage());
		}

		return new ResponseEntity<Pessoa>(pessoa, HttpStatus.OK);

	}

	@CrossOrigin
	@GetMapping("/listar_todos")
	public Iterable<Pessoa> listar() {

		if (!pessoaList.isEmpty()) {
			return this.pessoaList;
		}

		return this.pessoaDao.findAll();
	}

	@CrossOrigin
	@PostMapping("/auth")
	public ResponseEntity<Usuario> auth(@RequestBody Usuario usuario) {

		if (!usuario.getUser().equals("rodrigo")) {
			throw new ResourceNotFoundException("Usuário inválido!");
		} else if (!usuario.getPassword().equals("user")) {
			throw new ResourceNotFoundException("Senha inválida!");
		}

		String senha = new String(
				java.util.Base64.getEncoder().encode((usuario.getUser() + ":" + usuario.getPassword()).getBytes()));

		usuario.setPassword(senha);

		return new ResponseEntity<Usuario>(usuario, HttpStatus.OK);
	}
	
	@CrossOrigin
	@GetMapping("/source")
	public String exibirRepositorio() {

		String repo = "https://github.com/rrocha80/api-syscad.git && https://github.com/rrocha80/syscad.git";

		return repo;
	}
	

}
