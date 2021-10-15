package br.com.apssystem.algafood.domain.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.*;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.validator.constraints.br.CNPJ;

@Entity
@Table(name = "restaurante")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Restaurante {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	private Long id;

	@Column(nullable = false, unique = true)
	private String nome;

	@CNPJ
	@Column(length = 18, unique = true)
	private String cnpj;

	@Column(length = 20)
	private String telefone;

	@Column(precision = 12, scale = 2)
	private BigDecimal frete;

	private boolean ativo;

	private boolean aberto;

	@CreationTimestamp
	@Column(name = "data_cadastro")
	private LocalDate dataCadastro;

	@UpdateTimestamp
	@Column(name = "data_atualizacao")
	private LocalDate dataAtualizacao;

	@ManyToOne
	@JoinColumn(name = "cozinha_id", nullable = false)
	private Cozinha cozinha;

	@ManyToMany
	@JoinTable(name = "restaurante_forma_pagto", joinColumns = @JoinColumn(name = "restaurante_id"),
			inverseJoinColumns = @JoinColumn(name = "forma_pagto_id"))
	private Set<FormaPagto> formasPagtos = new HashSet<>();

	@ManyToMany
	@JoinTable(name = "restaurante_usuario_responsavel",
			joinColumns = @JoinColumn(name = "restaurante_id"),
			inverseJoinColumns = @JoinColumn(name = "usuario_id"))
	private Set<Usuario> responsaveis = new HashSet<>();

	@Embedded
	private Endereco endereco;

	@OneToMany(mappedBy = "restaurante")
	private List<Produto> produtos = new ArrayList<>();

	public void ativar() {
		setDataAtualizacao(LocalDate.now());
		setAtivo(true);
	}

	public void inativar() {
		setDataAtualizacao(LocalDate.now());
		setAtivo(false);
	}

	public void abrir() {
		setAberto(true);
	}

	public void fechar() {
		setAberto(false);
	}

	public boolean aceitaFormaPagamento(FormaPagto formaPagto) {
		return getFormasPagtos().contains(formaPagto);
	}

	public boolean naoAceitaFormaPagamento(FormaPagto formaPagto) {
		return !aceitaFormaPagamento(formaPagto);
	}

}
