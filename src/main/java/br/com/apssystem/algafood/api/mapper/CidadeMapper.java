package br.com.apssystem.algafood.api.mapper;

import java.util.List;
import java.util.stream.Collectors;

import br.com.apssystem.algafood.api.controller.CidadeController;
import br.com.apssystem.algafood.api.controller.EstadoController;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import br.com.apssystem.algafood.api.model.CidadeModel;
import br.com.apssystem.algafood.api.model.input.CidadeInput;
import br.com.apssystem.algafood.domain.model.Cidade;
import br.com.apssystem.algafood.domain.model.Estado;

@Component
public class CidadeMapper extends RepresentationModelAssemblerSupport<Cidade,CidadeModel> {

	@Autowired
	ModelMapper modelMapper;

	public CidadeMapper(){
		super(CidadeController.class, CidadeModel.class);
	}

	@Override
	public CidadeModel toModel(Cidade cidade) {
		CidadeModel cidadeModel = modelMapper.map(cidade, CidadeModel.class);

		cidadeModel.add(WebMvcLinkBuilder.linkTo(CidadeController.class)
				.slash(cidadeModel.getId()).withSelfRel());

		cidadeModel.add(WebMvcLinkBuilder.linkTo(CidadeController.class)
				.withRel("cidades"));

		cidadeModel.getEstado().add(WebMvcLinkBuilder.linkTo(EstadoController.class)
				.slash(cidadeModel.getEstado().getId()).withSelfRel());

		return  cidadeModel;
	}

	@Override
	public CollectionModel<CidadeModel> toCollectionModel(Iterable<? extends Cidade> entities) {
		return super.toCollectionModel(entities).add(WebMvcLinkBuilder.linkTo(CidadeController.class).withSelfRel());
	}

	public Cidade toDomainObject(CidadeInput cidadeInput) {
		return modelMapper.map(cidadeInput, Cidade.class);
	}

	public void copyToDomainObject(CidadeInput cidadeInput, Cidade cidade) {
		// Para evitar org.hibernate.HibernateException: identifier of an instance of
		// br.com.apssystem.algafood-api.domain.model.Cidade was altered from 1 to 2
		cidade.setEstado(new Estado());
		modelMapper.map(cidadeInput, cidade);
	}
}
