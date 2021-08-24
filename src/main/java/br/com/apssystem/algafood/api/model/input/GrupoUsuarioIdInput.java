package br.com.apssystem.algafood.api.model.input;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class GrupoUsuarioIdInput {

	@NotNull
	private Long id;

}
