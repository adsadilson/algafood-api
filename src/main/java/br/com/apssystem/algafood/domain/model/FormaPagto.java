package br.com.apssystem.algafood.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "forma_pagto")
@SequenceGenerator(name = "FORMA_PAGTO_SEQ", sequenceName = "FORMA_PAGTO_SEQ")
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class FormaPagto {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "FORMA_PAGTO_SEQ")
	@EqualsAndHashCode.Include
	private Long id;

	@Column(nullable = false)
	private String descricao;

}
