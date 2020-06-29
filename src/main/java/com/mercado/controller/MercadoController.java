package com.mercado.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mercado.model.Mercado;
import com.mercado.repository.MercadoRepository;


@RestController
@RequestMapping(value = "/mercado")
public class MercadoController {

	@Autowired
	private MercadoRepository mercadoRepo;

	@PostMapping(value = "/", produces = "application/json")
	private ResponseEntity<Mercado> save(@RequestBody Mercado mercadinho) {

		Mercado salvaMercado = mercadoRepo.save(mercadinho);

		return new ResponseEntity<Mercado>(salvaMercado, HttpStatus.OK);

	}

	@GetMapping(value = "/", produces = "application/json")
	@CachePut("cacheusuarios")
	private ResponseEntity<List<Mercado>> getAll() {

		List<Mercado> lista = mercadoRepo.findAll();

		return new ResponseEntity<List<Mercado>>(lista, HttpStatus.OK);

	}
}
