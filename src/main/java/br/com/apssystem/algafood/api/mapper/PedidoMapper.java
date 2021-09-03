package br.com.apssystem.algafood.api.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.apssystem.algafood.api.model.PedidoModel;
import br.com.apssystem.algafood.api.model.input.PedidoInput;
import br.com.apssystem.algafood.domain.model.Pedido;

@Component
public class PedidoMapper {

	@Autowired
	ModelMapper modelMapper;

	public PedidoModel toModel(Pedido pedido) {
		return modelMapper.map(pedido, PedidoModel.class);
	}

	public List<PedidoModel> toColletionModel(List<Pedido> pedidos) {
		return pedidos.stream().map(pedido -> toModel(pedido)).collect(Collectors.toList());
	}

	public Pedido toDomainObject(PedidoInput input) {
		return modelMapper.map(input, Pedido.class);
	}

	public void copyToDomainObject(PedidoInput input, Pedido pedido) {
		modelMapper.map(input, pedido);
	}
}
