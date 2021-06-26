package br.com.apssystem.algafood.api.model.input;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FormaPagtoIdInput {

	@NotNull
	private Long id;

}
