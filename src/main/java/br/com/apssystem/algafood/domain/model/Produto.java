package br.com.apssystem.algafood.domain.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "produto")
@SequenceGenerator(name = "PRODUTO_ID", sequenceName = "PRODUTO_ID_SEQ")
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Produto {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "PRODUTO_ID_SEQ")
	@EqualsAndHashCode.Include
	private Long id;

	@Column(nullable = false)
	private String nome;

	private String descricao;

	private BigDecimal preco;

	private boolean ativo;

	@ManyToOne
	private Restaurante restaurante;
}
