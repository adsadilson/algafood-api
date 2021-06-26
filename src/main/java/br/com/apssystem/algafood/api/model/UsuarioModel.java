package br.com.apssystem.algafood.api.model;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class UsuarioModel {

	private Long id;
	private String nome;
	private String email;
	private LocalDateTime dataCadastro;

}
