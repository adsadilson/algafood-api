package br.com.apssystem.algafood.api.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.apssystem.algafood.api.model.CidadeModel;
import br.com.apssystem.algafood.api.model.input.CidadeInput;
import br.com.apssystem.algafood.domain.model.Cidade;
import br.com.apssystem.algafood.domain.model.Estado;

@Component
public class CidadeMapper {

	@Autowired
	ModelMapper modelMapper;

	public CidadeModel toModel(Cidade cidade) {
		return modelMapper.map(cidade, CidadeModel.class);
	}

	public List<CidadeModel> toCollectionModel(List<Cidade> cozinhas) {
		return cozinhas.stream().map(this::toModel).collect(Collectors.toList());
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
