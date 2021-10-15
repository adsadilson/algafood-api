package br.com.apssystem.algafood.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.*;
import org.jfree.util.ResourceBundleSupport;
import org.springframework.hateoas.RepresentationModel;

@Entity
@Table(name = "estado")
@SequenceGenerator(name = "ESTADO_ID", sequenceName = "ESTADO_ID_SEQ")
@Setter
@Getter
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
