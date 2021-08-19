package br.com.apssystem.algafood.api.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.apssystem.algafood.api.model.PermissaoModel;
import br.com.apssystem.algafood.api.model.input.PermissaoInput;
import br.com.apssystem.algafood.domain.model.Permissao;

@Component
public class PermissaoMapper {

	@Autowired
	private ModelMapper modelMapper;

	public PermissaoModel toModel(Permissao permissao) {
		return modelMapper.map(permissao, PermissaoModel.class);
	}

	public List<PermissaoModel> toCollectionModel(List<Permissao> objs) {
		return objs.stream().map(permissao -> toModel(permissao)).collect(Collectors.toList());
	}

	public Permissao toDomainObject(PermissaoInput input) {
		return modelMapper.map(input, Permissao.class);
	}

	public void copyToDomainObject(PermissaoInput input, Permissao permissao) {
		// Para evitar org.hibernate.HibernateException: identifier of an instance of
		// br.com.apssystem.algafood-api.domain.model.Permissao was altered from 1 to 2
		modelMapper.map(input, permissao);
	}
}
