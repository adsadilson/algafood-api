package br.com.apssystem.algafood.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "cidade")
@SequenceGenerator(name = "CIDADE_ID", sequenceName = "CIDADE_ID_SEQ")
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Cidade {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "CIDADE_ID_SEQ")
	@EqualsAndHashCode.Include
	private Long id;

	@NotBlank
	@Column(nullable = false)
	private String nome;

	@Valid
	@NotNull
	@ManyToOne
	@JoinColumn(name = "estado_id", nullable = false)
	private Estado estado;
}
