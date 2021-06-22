package br.com.apssystem.algafood.api.model.input;

import java.math.BigDecimal;

import javax.validation.Valid;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;

import com.sun.istack.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RestauranteInput {

	@NotBlank
	private String nome;

	@NotNull
	@DecimalMin("0")
	private BigDecimal frete;

	@Valid
	@NotNull
	private CozinhaIdInput cozinha;
}
