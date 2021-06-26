package br.com.apssystem.algafood.api.model.input;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class SenhaInput {

	@NotBlank
	private String senhaAtual;

	@NotBlank
	private String novaSenha;

}
