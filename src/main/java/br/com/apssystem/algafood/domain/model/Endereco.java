package br.com.apssystem.algafood.domain.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class Endereco	 {


	@Column(name = "end_cep")
	private String cep;
	
	@Column(name = "end_logradouro")
	private String logradouro;

	@Column(name = "end_numero")
	private String numero;

	@Column(name = "end_bairro")
	private String bairro;

	@Column(name = "end_complemento")
	private String complemento;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "end_cidade_id")
	private Cidade cidade;
}
