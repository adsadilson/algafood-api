package br.com.apssystem.algafood.api.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.apssystem.algafood.api.model.FormaPagtoModel;
import br.com.apssystem.algafood.api.model.input.FormaPagtoInput;
import br.com.apssystem.algafood.domain.model.FormaPagto;

@Component
public class FormaPagtoMapper {

	@Autowired
	ModelMapper modelMapper;

	public FormaPagtoModel toModel(FormaPagto formaPagto) {
		return modelMapper.map(formaPagto, FormaPagtoModel.class);
	}

	public List<FormaPagtoModel> toCollectionModel(List<FormaPagto> formaPagtos) {
		return formaPagtos.stream().map(formaPagto -> toModel(formaPagto)).collect(Collectors.toList());
	}
	
	public FormaPagto toDomainObject(FormaPagtoInput formaPagtoInput) {
		return modelMapper.map(formaPagtoInput, FormaPagto.class);
	}

	public void copyToDomainObject(FormaPagtoInput formaPagtoInput, FormaPagto formaPagto) {
		// Para evitar org.hibernate.HibernateException: identifier of an instance of
		// br.com.apssystem.algafood-api.domain.model.FormaPagto was altered from 1 to 2
		//cidade.setEstado(new Estado());
		modelMapper.map(formaPagtoInput, formaPagto);
	}

}
