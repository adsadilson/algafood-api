package br.com.apssystem.algafood.domain.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.*;
import org.springframework.data.annotation.Transient;

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

	@Transient
	private String localidade;
	@Transient
	private String uf;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "end_cidade_id")
	private Cidade cidade;
}
