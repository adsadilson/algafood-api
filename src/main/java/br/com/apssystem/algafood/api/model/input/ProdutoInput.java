package br.com.apssystem.algafood.api.model.input;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class ProdutoInput {


	@Column(nullable = false)
	private String nome;

	@NotNull
	private String descricao;

	@DecimalMin("0")
	private BigDecimal preco;

	@NotNull
	private boolean ativo;

	@NotNull
	private RestauranteIdInput restaurante;
}
