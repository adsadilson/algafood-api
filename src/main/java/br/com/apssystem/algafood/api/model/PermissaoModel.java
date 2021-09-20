package br.com.apssystem.algafood.api.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class PermissaoModel {

	@ApiModelProperty(example = "1")
	private Long id;

	@ApiModelProperty(example = "")
	private String nome;

	private String descricao;

}
