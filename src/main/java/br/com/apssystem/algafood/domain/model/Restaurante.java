package br.com.apssystem.algafood.domain.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

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

	@CreationTimestamp
	@JsonIgnore
	@Column(name = "data_cadastro")
	private LocalDate dataCadastro;

	@UpdateTimestamp
	@JsonIgnore
	@Column(name = "data_atualizacao")
	private LocalDate dataAtualizacao;

	@NotNull
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "cozinha_id", nullable = false)
	private Cozinha cozinha;

	@JsonIgnore
	@ManyToMany
	@JoinTable(name = "restaurante_forma_pagto", joinColumns = @JoinColumn(name = "restaurante_id"), inverseJoinColumns = @JoinColumn(name = "forma_pagto_id"))
	private List<FormaPagto> formasPagtos = new ArrayList<>();

	@JsonIgnore
	@Embedded
	private Endereco endereco;

	@JsonIgnore
	@OneToMany(mappedBy = "restaurante")
	private List<Produto> produtos = new ArrayList<Produto>();

}
