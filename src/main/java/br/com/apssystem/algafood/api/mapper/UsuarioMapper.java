package br.com.apssystem.algafood.api.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.apssystem.algafood.api.model.UsuarioModel;
import br.com.apssystem.algafood.api.model.input.UsuarioAtulizarInput;
import br.com.apssystem.algafood.api.model.input.UsuarioInput;
import br.com.apssystem.algafood.domain.model.Usuario;

@Component
public class UsuarioMapper {

	@Autowired
	private ModelMapper modelMapper;

	public UsuarioModel toModel(Usuario usuario) {
		return modelMapper.map(usuario, UsuarioModel.class);
	}

	public List<UsuarioModel> toCollectionModel(List<Usuario> usuarios) {
		return usuarios.stream().map(user -> toModel(user)).collect(Collectors.toList());
	}

	public Usuario toDomainObject(UsuarioInput input) {
		return modelMapper.map(input, Usuario.class);
	}

	public void copyToDomainObject(UsuarioAtulizarInput input, Usuario user) {
		modelMapper.map(input, user);
	}

}
