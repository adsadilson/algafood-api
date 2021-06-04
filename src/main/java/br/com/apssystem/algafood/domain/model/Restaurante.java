package br.com.apssystem.algafood.domain.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "restaurante")
@SequenceGenerator(name = "RESTAURANTE_ID", sequenceName = "RESTAURANTE_ID_SEQ")
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Restaurante {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "RESTAURANTE_ID_SEQ")
	@EqualsAndHashCode.Include
	private Long id;

	@NotBlank
	@Column(nullable = false, unique = true)
	private String nome;

	@Column(precision = 12, scale = 2)
	private BigDecimal frete;

	private boolean ativo;

	private boolean aberto;

	@Column(name = "data_cadastro")
	private LocalDate dataCadastro;

	@Column(name = "data_atualizacao")
	private LocalDate dataAtualizacao;

	@NotNull
	@ManyToOne
	@JoinColumn(name = "cozinha_id", nullable = false)
	private Cozinha cozinha;

}
