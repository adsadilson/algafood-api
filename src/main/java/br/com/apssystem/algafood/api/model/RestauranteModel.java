package br.com.apssystem.algafood.api.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.apssystem.algafood.domain.model.FormaPagto;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

@Relation(collectionRelation = "Restaurantes")
@Getter
@Setter
public class RestauranteModel extends RepresentationModel<RestauranteModel> {

	@ApiModelProperty(example = "1")
	private Long id;
	@ApiModelProperty(example = "Thai Gourmet")
	private String nome;
	@ApiModelProperty(example = "3.75")
	private BigDecimal frete;
	@JsonIgnore
	@ApiModelProperty(example = "true")
	private boolean ativo;
	@JsonIgnore
	@ApiModelProperty(example = "true")
	private boolean aberto;
	@JsonIgnore
	@ApiModelProperty(example = "2019-10-30T00:00:00Z",
			value = "Data/hora de criação")
	private LocalDate dataCadastro;
	@JsonIgnore
	@ApiModelProperty(example = "2019-10-30T00:00:00Z",
			value = "Data/hora de atualizacao")
	private LocalDate dataAtualizacao;

	@JsonIgnore
	private CozinhaModel cozinha;
	@JsonIgnore
	private List<FormaPagto> formasPagtos = new ArrayList<>();
	@JsonIgnore
	private EnderecoModel endereco;
}
