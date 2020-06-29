package com.mercado.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Mercado {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String produto;
	private Double preco;
	private String categoria;
	private String nomeMercado;

	@JsonIgnore
	@ManyToOne()
	@JoinColumn(name = "pessoa_id")
	private Pessoa pessoa;

	public Mercado(Long id, String produto, Double preco, String categoria, String nomeMercado, Pessoa pessoa) {
		super();
		this.id = id;
		this.produto = produto;
		this.preco = preco;
		this.categoria = categoria;
		this.nomeMercado = nomeMercado;
		this.pessoa = pessoa;
	}

	public Mercado() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getProduto() {
		return produto;
	}

	public void setProduto(String produto) {
		this.produto = produto;
	}

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public String getNomeMercado() {
		return nomeMercado;
	}

	public void setNomeMercado(String nomeMercado) {
		this.nomeMercado = nomeMercado;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

}
