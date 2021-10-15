package br.com.apssystem.algafood.api.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.apssystem.algafood.domain.model.Restaurante;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

@Relation(collectionRelation = "Cozinhas")
@Getter
@Setter
public class CozinhaModel extends RepresentationModel<CozinhaModel> {

	@ApiModelProperty(example = "1")
	private Long id;

	@ApiModelProperty(example = "Brasileira")
	private String nome;

	@JsonIgnore
	private List<Restaurante> restaurantes = new ArrayList<>();
}
