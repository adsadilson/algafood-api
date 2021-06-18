package br.com.apssystem.algafood.api.model.mixin;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RestauranteModel {

	private Long id;

	private String nome;

	private BigDecimal frete;

	private boolean ativo;

	private boolean aberto;

	private LocalDate dataCadastro;

	private LocalDate dataAtualizacao;

	private CozinhaModel cozinha;
}
