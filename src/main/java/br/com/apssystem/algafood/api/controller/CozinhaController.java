package br.com.apssystem.algafood.api.controller;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "cozinha")
@SequenceGenerator(name = "COZINHA_ID", sequenceName = "COZINHA_ID_SEQ")
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class CozinhaController {

	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "COZINHA_ID_SEQ")
	private Long id;

	@NotBlank
	@Column(length = 65, nullable = false, unique = true)
	private String nome;

}
