package br.com.apssystem.algafood.api.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RestauranteModel {

	private Long id;
	private String nome;
	private BigDecimal frete;
	private boolean ativo;
	@JsonIgnore
	private boolean aberto;
	@JsonIgnore
	private LocalDate dataCadastro;
	private LocalDate dataAtualizacao;
	private CozinhaModel cozinha;
	
	private EnderecoModel endereco;
}
