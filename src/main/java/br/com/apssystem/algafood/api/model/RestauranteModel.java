package br.com.apssystem.algafood.api.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.apssystem.algafood.domain.model.FormaPagto;
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
	@JsonIgnore
	private LocalDate dataCadastro;
	private LocalDate dataAtualizacao;
	private CozinhaModel cozinha;
	
	private List<FormaPagto> formasPagtos = new ArrayList<>();
	
	private EnderecoModel endereco;
}
