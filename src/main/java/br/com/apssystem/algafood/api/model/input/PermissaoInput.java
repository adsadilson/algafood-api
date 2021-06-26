package br.com.apssystem.algafood.api.model.input;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class PermissaoInput {

	@NotBlank
	private String descricao;

}
