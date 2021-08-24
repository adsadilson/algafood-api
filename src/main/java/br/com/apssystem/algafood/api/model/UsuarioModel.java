package br.com.apssystem.algafood.api.model;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import br.com.apssystem.algafood.domain.model.GrupoUsuario;
import lombok.Data;

@Data
public class UsuarioModel {

	private Long id;
	private String nome;
	private String email;
	private LocalDateTime dataCadastro;
	@JsonIgnoreProperties(value = {"permissoes"})
	private List<GrupoUsuario> grupos;

}
