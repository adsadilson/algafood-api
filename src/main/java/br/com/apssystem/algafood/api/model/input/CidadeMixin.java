package br.com.apssystem.algafood.api.model.input;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import br.com.apssystem.algafood.domain.model.Estado;

public class CidadeMixin {

	@JsonIgnoreProperties(value = { "nome", "sigla", "capital", "regiao" }, allowGetters = true)
	private Estado estado;
}
