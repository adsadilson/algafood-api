package br.com.apssystem.algafood.api.model;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import br.com.apssystem.algafood.domain.model.GrupoUsuario;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

@Relation(collectionRelation = "Usuários")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioModel extends RepresentationModel<UsuarioModel> {

	@ApiModelProperty(example = "1")
	private Long id;
	@ApiModelProperty(example = "João da Silva")
	private String nome;
	@ApiModelProperty(example = "joaos21@apssystem.com")
	private String email;
	@ApiModelProperty(example = "2019-10-30T00:00:00Z",
			value = "Data/hora de criação")
	private LocalDateTime dataCadastro;

	@ApiModelProperty(example = "CLIENTE", value = "Grupo a qual o usuário pertence ")
	@JsonIgnoreProperties(value = {"permissoes"})
	private List<GrupoUsuario> grupos;

}
