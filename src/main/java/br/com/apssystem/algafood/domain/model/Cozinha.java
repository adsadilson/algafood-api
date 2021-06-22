package br.com.apssystem.algafood.domain.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "cozinha")
@SequenceGenerator(name = "COZINHA_ID", sequenceName = "COZINHA_ID_SEQ")
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Cozinha {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "COZINHA_ID_SEQ")
	@EqualsAndHashCode.Include
	private Long id;

	@NotBlank
	@Column(length = 65, nullable = false, unique = true)
	private String nome;

	@OneToMany(mappedBy = "cozinha")
	private List<Restaurante> restaurantes = new ArrayList<>();

}
