package br.com.apssystem.algafood.api.mapper;

import br.com.apssystem.algafood.api.model.PedidoResumoModel;
import br.com.apssystem.algafood.api.model.PedidoStatusResumoModel;
import br.com.apssystem.algafood.api.model.input.PedidoInput;
import br.com.apssystem.algafood.domain.model.Pedido;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PedidoResumoMapper {

	@Autowired
	ModelMapper modelMapper;

	public PedidoResumoModel toModel(Pedido pedido) {
		return modelMapper.map(pedido, PedidoResumoModel.class);
	}
	public PedidoStatusResumoModel toModelResumo(Pedido pedido) {
		return modelMapper.map(pedido, PedidoStatusResumoModel.class);
	}

	public List<PedidoResumoModel> toColletionModel(List<Pedido> pedidos) {
		return pedidos.stream().map(this::toModel).collect(Collectors.toList());
	}

	public Pedido toDomainObject(PedidoInput input) {
		return modelMapper.map(input, Pedido.class);
	}

	public void copyToDomainObject(PedidoInput input, Pedido pedido) {
		modelMapper.map(input, pedido);
	}
}
