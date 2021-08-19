package br.com.apssystem.algafood.api.model.input;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class PermissaoIdInput {

	@NotNull
	private Long id;
	
}
