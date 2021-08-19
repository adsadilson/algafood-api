package br.com.apssystem.algafood.api.model.input;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class PermissaoInput {

	private Long id;
	
	@NotBlank
	private String nome;
	
	@NotBlank
	private String descricao;

}
