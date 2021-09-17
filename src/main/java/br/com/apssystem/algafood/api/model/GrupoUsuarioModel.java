package br.com.apssystem.algafood.api.model;

import java.util.HashSet;
import java.util.Set;

import br.com.apssystem.algafood.domain.model.Permissao;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class GrupoUsuarioModel {

	@ApiModelProperty(example = "1")
	private Long id;

	@ApiModelProperty(example = "Gerente")
	private String nome;

	//@JsonIgnore

	private Set<Permissao> permissoes = new HashSet<>();
}
