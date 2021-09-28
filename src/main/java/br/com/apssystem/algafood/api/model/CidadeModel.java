package br.com.apssystem.algafood.api.model;

import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CidadeModel extends RepresentationModel<CidadeModel> {

	@ApiModelProperty(example = "1")
	private Long id;

	@ApiModelProperty(example = "Planalto")
	private String nome;

	@JsonIgnoreProperties(value = { "nome", "capital", "regiao" }, allowGetters = false)
	private EstadoModel estado;
}
