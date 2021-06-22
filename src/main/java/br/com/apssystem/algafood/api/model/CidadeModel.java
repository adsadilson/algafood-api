package br.com.apssystem.algafood.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CidadeModel {

	private Long id;

	private String nome;

	@JsonIgnoreProperties(value = { "nome", "capital", "regiao" }, allowGetters = false)
	private EstadoModel estado;
}
