package br.com.apssystem.algafood.domain.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "grupo_usuario")
@SequenceGenerator(name = "GRUPO_USUARIO_ID", sequenceName = "GRUPO_USUARIO_ID_SEQ")
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class GrupoUsuario {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "GRUPO_USUARIO_ID_SEQ")
	@EqualsAndHashCode.Include
	private Long id;

	@NotBlank
	@Column(nullable = false, unique = true)
	private String nome;

	@ManyToMany
	@JoinTable(name = "grupo_usuario_permissao", 
	joinColumns = @JoinColumn(name = "grupo_usuario_id"), 
	inverseJoinColumns = @JoinColumn(name = "permissao_id"))
	private List<Permissao> permissoes = new ArrayList<>();
}
