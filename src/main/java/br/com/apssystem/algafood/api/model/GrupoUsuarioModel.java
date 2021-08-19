package br.com.apssystem.algafood.api.model;

import java.util.HashSet;
import java.util.Set;

import br.com.apssystem.algafood.domain.model.Permissao;
import lombok.Data;

@Data
public class GrupoUsuarioModel {
	
	private Long id;
	private String nome;

	//@JsonIgnore
	private Set<Permissao> permissoes = new HashSet<>();
}
