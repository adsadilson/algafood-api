package br.com.apssystem.algafood.api.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EstadoModel {

	private Long id;

	private String nome;

	private String sigla;

	private String capital;

	private String regiao;

}
