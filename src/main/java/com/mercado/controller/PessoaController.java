package com.mercado.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.mercado.model.Pessoa;

import com.mercado.repository.MercadoRepository;
import com.mercado.repository.PessoaRepository;

@RestController
@RequestMapping(value = "/pessoa")
public class PessoaController {

	@Autowired
	private PessoaRepository pessoaRepo;

	@Autowired
	private MercadoRepository mercadoRepo;

	

	@GetMapping(value = "/{id}", produces = "application/json")
	@CachePut("cacheusuarios")
	public ResponseEntity<Pessoa> getId(@PathVariable(value = "id") Long id) {
		Optional<Pessoa> pessoa = pessoaRepo.findById(id);

		return new ResponseEntity<Pessoa>(pessoa.get(), HttpStatus.OK);
	}

	@PostMapping(value = "/", produces = "application/json")
	public ResponseEntity<Pessoa> savePeople(@RequestBody Pessoa pessoa) {

		for (int i = 0; i < pessoa.getMercadinho().size(); i++) {
			pessoa.getMercadinho().get(i).setPessoa(pessoa);
		}

		String senhaEncriptada = new BCryptPasswordEncoder().encode(pessoa.getSenha());
		pessoa.setSenha(senhaEncriptada);

		Pessoa pessoaSalva = pessoaRepo.save(pessoa);

		// userDetailServicos.insereAcessoPadrao(pessoaSalva.getId());

		return new ResponseEntity<Pessoa>(pessoaSalva, HttpStatus.OK);
	}

	@GetMapping(value = "/", produces = "application/json")
	@CachePut("cacheusuarios")
	public ResponseEntity<List<Pessoa>> findAllPeople() {
		List<Pessoa> buscaPessoas = pessoaRepo.findAll();

		return new ResponseEntity<List<Pessoa>>(buscaPessoas, HttpStatus.OK);
	}

	@PutMapping(value = "/", produces = "application/json")
	public ResponseEntity<Pessoa> update(@RequestBody Pessoa pessoa) {

		for (int i = 0; i < pessoa.getMercadinho().size(); i++) {
			pessoa.getMercadinho().get(i).setPessoa(pessoa);
		}
		Pessoa userTemporario = pessoaRepo.findById(pessoa.getId()).get();
		if(!userTemporario.getSenha().equals(pessoa.getSenha())) {
			String senhaCriptografada = new BCryptPasswordEncoder().encode(pessoa.getSenha());
			pessoa.setSenha(senhaCriptografada);
		}
		Pessoa pessoaSalva = pessoaRepo.save(pessoa);
		return new ResponseEntity<Pessoa>(pessoaSalva, HttpStatus.OK);

	}

	@DeleteMapping(value = "/{id}", produces = "application/text")
	public String deletePessoa(@PathVariable("id") Long id) {
		pessoaRepo.deleteById(id);

		return "Excluído com sucesso!";
	}

	@DeleteMapping(value = "/deletarMercado/{id}", produces = "application/text")
	public String deletarMercado(@PathVariable("id") Long id) {
		mercadoRepo.deleteById(id);
		return "Mercado deletado!";
	}
	
	

	
}
