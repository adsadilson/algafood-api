package br.com.apssystem.algafood.api.mapper;

import br.com.apssystem.algafood.api.model.CidadeModel;
import br.com.apssystem.algafood.api.model.FotoProdutoModel;
import br.com.apssystem.algafood.api.model.input.CidadeInput;
import br.com.apssystem.algafood.domain.model.Cidade;
import br.com.apssystem.algafood.domain.model.Estado;
import br.com.apssystem.algafood.domain.model.FotoProduto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class FotoProdutoMapper {

	@Autowired
	ModelMapper modelMapper;

	public FotoProdutoModel toModel(FotoProduto foto) {
		return modelMapper.map(foto, FotoProdutoModel.class);
	}

	public List<FotoProdutoModel> toModelList(List<FotoProduto> fotos) {
		return fotos.stream().map(this::toModel).collect(Collectors.toList());
	}

}
