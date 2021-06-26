package br.com.apssystem.algafood.api.model.input;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class GrupoUsuarioInput {

	@NotBlank
	private String nome;

	//@NotNull
   //rivate List<Permissao> permissoes = new ArrayList<>();
}
