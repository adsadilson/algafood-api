package br.com.apssystem.algafood.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.springframework.hateoas.RepresentationModel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "estado")
@SequenceGenerator(name = "ESTADO_ID", sequenceName = "ESTADO_ID_SEQ")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Estado extends RepresentationModel<Estado> {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "ESTADO_ID_SEQ")
	@EqualsAndHashCode.Include
	private Long id;

	private String nome;

	@Column(length = 2)
	private String sigla;

	private String capital;

	private String regiao;

}
