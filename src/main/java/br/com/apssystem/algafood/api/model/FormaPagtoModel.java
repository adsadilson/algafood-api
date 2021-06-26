package br.com.apssystem.algafood.api.model;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FormaPagtoModel {

	private Long id;

	@NotBlank
	private String descricao;

}
