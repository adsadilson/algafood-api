package br.com.apssystem.algafood.api.model.input;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CidadeInput {

	@ApiModelProperty(example = "Planalto", required = true)
	@NotBlank
	private String nome;

	@Valid
	@NotNull
	private EstadoIdInput estado;

}
