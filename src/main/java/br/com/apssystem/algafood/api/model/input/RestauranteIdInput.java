package br.com.apssystem.algafood.api.model.input;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class RestauranteIdInput {

	@ApiModelProperty(example = "1")
	@NotNull
	private Long id;

}
