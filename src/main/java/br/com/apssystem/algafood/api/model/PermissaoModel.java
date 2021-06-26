package br.com.apssystem.algafood.api.model;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class PermissaoModel {

	private Long id;

	@NotBlank
	private String descricao;

}
