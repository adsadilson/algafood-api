package br.com.apssystem.algafood.api.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.apssystem.algafood.api.model.GrupoUsuarioModel;
import br.com.apssystem.algafood.api.model.input.GrupoUsuarioInput;
import br.com.apssystem.algafood.domain.model.GrupoUsuario;

@Component
public class GrupoUsuarioMapper {

	@Autowired
	private ModelMapper modelMapper;

	public GrupoUsuarioModel toModel(GrupoUsuario grupo) {
		return modelMapper.map(grupo, GrupoUsuarioModel.class);
	}

	public List<GrupoUsuarioModel> toCollectionModel(List<GrupoUsuario> grupos) {
		return grupos.stream().map(this::toModel).collect(Collectors.toList());
	}

	public GrupoUsuario toDomainObject(GrupoUsuarioInput input) {
		return modelMapper.map(input, GrupoUsuario.class);
	}

	public void copyToDomainObject(GrupoUsuarioInput input, GrupoUsuario grupo) {
		modelMapper.map(input, grupo);
	}

}
