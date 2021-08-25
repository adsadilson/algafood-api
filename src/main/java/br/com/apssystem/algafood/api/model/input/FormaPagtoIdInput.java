package br.com.apssystem.algafood.api.model.input;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class FormaPagtoIdInput {

	@NotNull
	private Long id;

}
