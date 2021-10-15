package br.com.apssystem.algafood.api.mapper;

import java.util.List;
import java.util.stream.Collectors;

import br.com.apssystem.algafood.api.controller.CidadeController;
import br.com.apssystem.algafood.api.controller.RestauranteController;
import br.com.apssystem.algafood.api.model.CidadeModel;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import br.com.apssystem.algafood.api.model.RestauranteModel;
import br.com.apssystem.algafood.api.model.input.RestauranteInput;
import br.com.apssystem.algafood.domain.model.Cidade;
import br.com.apssystem.algafood.domain.model.Cozinha;
import br.com.apssystem.algafood.domain.model.Restaurante;

@Component
public class RestauranteMapper extends RepresentationModelAssemblerSupport<Restaurante, RestauranteModel> {

	@Autowired
	ModelMapper modelMapper;

	public RestauranteMapper(){
		super(RestauranteController.class,RestauranteModel.class);
	}

	public RestauranteModel toModel(Restaurante restaurante) {
		RestauranteModel restauranteModel = modelMapper.map(restaurante, RestauranteModel.class);

		restauranteModel.add(WebMvcLinkBuilder.linkTo(RestauranteController.class)
				.slash(restauranteModel.getId()).withSelfRel());

		restauranteModel.add(WebMvcLinkBuilder.linkTo(RestauranteController.class)
				.withRel("restaurantes"));

		return restauranteModel;
	}

	public List<RestauranteModel> toCollectionModel(List<Restaurante> restaurantes) {
		return restaurantes.stream().map(this::toModel).collect(Collectors.toList());
	}

	public Restaurante toDomainObject(RestauranteInput restauranteInput) {
		return modelMapper.map(restauranteInput, Restaurante.class);
	}

	public void copyToDomainObject(RestauranteInput restauranteInput, Restaurante restaurante) {
		// Para evitar org.hibernate.HibernateException: identifier of an instance of
		// br.com.apssystem.algafood-api.domain.model.Cozinha e Endereco was altered from 1 to 2
		restaurante.setCozinha(new Cozinha());

		if (restaurante.getEndereco() != null) {
			restaurante.getEndereco().setCidade(new Cidade());
		}

		modelMapper.map(restauranteInput, restaurante);
	}
}
