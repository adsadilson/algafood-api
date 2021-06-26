package br.com.apssystem.algafood.api.model;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class ProdutoModel {

	private Long id;
	private String nome;
	private String descricao;
	private BigDecimal preco;
	private boolean ativo;

	//private Restaurante restaurante;
}
