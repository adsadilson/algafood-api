package br.com.apssystem.algafood.api.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class EnderecoViaCepModel {

	@ApiModelProperty(example = "451900000")
	private String cep;
	@ApiModelProperty(example = "Rua Eliezer Andrade 713")
	private String logradouro;
	@ApiModelProperty(example = "Casa")
	private String complemento;
	@ApiModelProperty(example = "Centro")
	private String bairro;
	@ApiModelProperty(example = "Planalto")
	private String localidade;
	@ApiModelProperty(example = "BA")
	private String uf;
	@ApiModelProperty(example = "2925006")
	private String ibge;
	@ApiModelProperty(example = "1004")
	private String gia;
	@ApiModelProperty(example = "77")
	private String ddd;
	@ApiModelProperty(example = "3801")
	private String siafi;


}
