package br.com.apssystem.algafood.api.model.input;

import java.util.Set;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class GrupoUsuarioInput {

	private Long id;

	@NotBlank
	private String nome;

	@NotNull
	private Set<PermissaoIdInput> permissaoIdInputs;
}
