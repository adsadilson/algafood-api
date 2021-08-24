package br.com.apssystem.algafood.api.model.input;

import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class UsuarioInput {

	private Long id;
	
	@NotBlank
	private String nome;

	@Email
	@NotBlank
	private String email;

	@NotBlank
	private String senha;

	@NotNull
	private Set<GrupoUsuarioIdInput> grupoUsuarioIdInput;

}
