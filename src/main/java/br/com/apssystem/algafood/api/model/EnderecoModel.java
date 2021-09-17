package br.com.apssystem.algafood.api.model;

import lombok.Data;

@Data
public class EnderecoModel	 {

	private String cep;
	private String logradouro;
	private String numero;
	private String bairro;
	private String complemento;
	private CidadeModel cidade;
}
