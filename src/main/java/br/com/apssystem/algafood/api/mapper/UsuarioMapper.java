package br.com.apssystem.algafood.api.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import br.com.apssystem.algafood.api.controller.UsuarioController;
import br.com.apssystem.algafood.api.model.UsuarioModel;
import br.com.apssystem.algafood.api.model.input.UsuarioAtulizarInput;
import br.com.apssystem.algafood.api.model.input.UsuarioInput;
import br.com.apssystem.algafood.domain.model.Usuario;

@Component
public class UsuarioMapper extends RepresentationModelAssemblerSupport<Usuario, UsuarioModel> {

	@Autowired
	ModelMapper modelMapper;

	public UsuarioMapper(){
		super(UsuarioController.class, UsuarioModel.class);
	}

	public UsuarioModel toModel(Usuario usuario) {
		UsuarioModel usuarioModel = modelMapper.map(usuario, UsuarioModel.class);
		usuarioModel.add(WebMvcLinkBuilder.linkTo(UsuarioController.class).slash(usuarioModel.getId()).withSelfRel());
		usuarioModel.add(WebMvcLinkBuilder.linkTo(UsuarioController.class)
				.withRel("usuarios"));

		/*usuarioModel.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UsuarioController.class)
				.listarTodos(usuario.getId())).withRel("grupos-usuario"));*/

		return usuarioModel;
	}

	@Override
	public CollectionModel<UsuarioModel> toCollectionModel(Iterable<? extends Usuario> entities) {
		return super.toCollectionModel(entities).add(WebMvcLinkBuilder.linkTo(UsuarioController.class).withSelfRel());
	}

	public Usuario toDomainObject(UsuarioInput input) {
		return modelMapper.map(input, Usuario.class);
	}

	public void copyToDomainObject(UsuarioAtulizarInput input, Usuario user) {
		modelMapper.map(input, user);
	}

}
