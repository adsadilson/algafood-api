package br.com.apssystem.algafood.api.model.input;

import java.math.BigDecimal;

import javax.validation.Valid;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CNPJ;

@Getter
@Setter
public class RestauranteInput {

	@ApiModelProperty(example = "Comida Mineira")
	@NotBlank
	private String nome;

	@ApiModelProperty(example = "91.801.854/0001-22")
	@CNPJ
	@NotBlank
	private String cnpj;

	@ApiModelProperty(example = "77 98801-5450")
	@NotBlank
	private String telefone;

	@ApiModelProperty(example = "3.75")
	//@NotNull
	@DecimalMin("0")
	private BigDecimal frete;

	@ApiModelProperty(example = "true")
	@NotNull
	private boolean ativo;

	@ApiModelProperty(example = "true")
	@NotNull
	private boolean aberto;

	@ApiModelProperty(example = "1")
	@NotNull
	@Valid
	private CozinhaIdInput cozinha;
	
	@NotNull
	@Valid
	private EnderecoInput endereco;
}
