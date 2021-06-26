package br.com.apssystem.algafood.api.model;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.apssystem.algafood.domain.model.Permissao;
import lombok.Data;

@Data
public class GrupoUsuarioModel {
	
	private Long id;

	@NotBlank
	private String nome;

	@NotNull
	@JsonIgnore
	private List<Permissao> permissoes = new ArrayList<>();
}
