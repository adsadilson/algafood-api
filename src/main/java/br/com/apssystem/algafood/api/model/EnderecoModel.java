package br.com.apssystem.algafood.api.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class EnderecoModel	 {

	@ApiModelProperty(example = "41001000")
	private String cep;
	@ApiModelProperty(example = "Av. Sete de Setembro")
	private String logradouro;
	@ApiModelProperty(example = "21")
	private String numero;
	@ApiModelProperty(example = "Pituba")
	private String bairro;
	@ApiModelProperty(example = "Proximo prefeitura")
	private String complemento;
	private CidadeModel cidade;
}
