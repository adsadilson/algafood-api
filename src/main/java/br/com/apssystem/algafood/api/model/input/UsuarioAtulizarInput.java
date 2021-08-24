package br.com.apssystem.algafood.api.model.input;

import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class UsuarioAtulizarInput {

	private Long id;
	
	@NotBlank
	private String nome;

	@Email
	@NotBlank
	private String email;

	@NotNull
	private Set<GrupoUsuarioIdInput> grupoUsuarioIdInput;

}
