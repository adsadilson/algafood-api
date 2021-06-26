package br.com.apssystem.algafood.api.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.apssystem.algafood.api.model.ProdutoModel;
import br.com.apssystem.algafood.api.model.input.ProdutoInput;
import br.com.apssystem.algafood.domain.model.Produto;

@Component
public class ProdutoMapper {

	@Autowired
	private ModelMapper modelMapper;

	public ProdutoModel toModel(Produto produto) {
		return modelMapper.map(produto, ProdutoModel.class);
	}

	public List<ProdutoModel> toColletionModel(List<Produto> produtos) {
		return produtos.stream().map(produto -> toModel(produto)).collect(Collectors.toList());
	}

	public Produto toDomainObject(ProdutoInput input) {
		return modelMapper.map(input, Produto.class);
	}

	public void copyToDomainObject(ProdutoInput input, Produto produto) {
		modelMapper.map(input, produto);
	}
}
