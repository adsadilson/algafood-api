package br.com.apssystem.algafood.api.model;

import java.math.BigDecimal;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class ProdutoModel {

	@ApiModelProperty(example = "1")
	private Long id;
	@ApiModelProperty(example = "Coca-cola")
	private String nome;
	@ApiModelProperty(example = "Coca-cola 350ml")
	private String descricao;
	@ApiModelProperty(example = "4.50")
	private BigDecimal preco;
	@ApiModelProperty(example = "ATIVO")
	private boolean ativo;

}
