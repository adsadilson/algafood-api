package br.com.apssystem.algafood.api.model.input;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FormaPagtoInput {

	@NotBlank
	private String descricao;

}
