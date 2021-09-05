package br.com.apssystem.algafood.api.model.input;

import java.math.BigDecimal;

import javax.validation.Valid;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CNPJ;

@Getter
@Setter
public class RestauranteInput {

	@NotBlank
	private String nome;

	@CNPJ
	@NotBlank
	private String cnpj;

	@NotBlank
	private String telefone;

	//@NotNull
	@DecimalMin("0")
	private BigDecimal frete;
	
	@NotNull
	private boolean ativo;
	
	@NotNull
	private boolean aberto;

	@NotNull
	@Valid
	private CozinhaIdInput cozinha;
	
	@NotNull
	@Valid
	private EnderecoInput endereco;
}
