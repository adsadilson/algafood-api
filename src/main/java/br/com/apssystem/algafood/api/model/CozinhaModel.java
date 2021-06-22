package br.com.apssystem.algafood.api.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.apssystem.algafood.domain.model.Restaurante;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CozinhaModel {

	private Long id;

	private String nome;

	@JsonIgnore
	private List<Restaurante> restaurantes = new ArrayList<>();
}
