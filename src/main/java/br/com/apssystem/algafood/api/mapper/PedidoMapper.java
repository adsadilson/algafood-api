package br.com.apssystem.algafood.api.mapper;

import java.util.List;
import java.util.stream.Collectors;

import br.com.apssystem.algafood.api.controller.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import br.com.apssystem.algafood.api.model.PedidoModel;
import br.com.apssystem.algafood.api.model.input.PedidoInput;
import br.com.apssystem.algafood.domain.model.Pedido;

@Component
public class PedidoMapper extends RepresentationModelAssemblerSupport<Pedido,PedidoModel> {

	@Autowired
	ModelMapper modelMapper;

	public PedidoMapper(){
		super(PedidoController.class, PedidoModel.class);
	}

	public PedidoModel toModel(Pedido pedido) {
		PedidoModel pedidoModel = createModelWithId(pedido.getCodigo(),pedido);
		modelMapper.map(pedido,pedidoModel);

		pedidoModel.add(WebMvcLinkBuilder.linkTo(PedidoController.class).withRel("pedidos"));

		pedidoModel.getRestaurante().add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(RestauranteController.class)
				.buscarPorId(pedido.getRestaurante().getId())).withSelfRel());

		pedidoModel.getCliente().add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UsuarioController.class)
				.buscarPorId(pedido.getCliente().getId())).withSelfRel());

		return pedidoModel;
	}

	public Pedido toDomainObject(PedidoInput input) {
		return modelMapper.map(input, Pedido.class);
	}

	public void copyToDomainObject(PedidoInput input, Pedido pedido) {
		modelMapper.map(input, pedido);
	}
}
