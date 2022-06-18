package br.com.apssystem.algafood.api.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.apssystem.algafood.api.model.PedidoModel;
import br.com.apssystem.algafood.api.model.PedidoStatusResumoModel;
import br.com.apssystem.algafood.api.model.input.PedidoInput;
import br.com.apssystem.algafood.domain.model.Pedido;

@Component
public class PedidoResumoMapper {

    @Autowired
    ModelMapper modelMapper;

    public PedidoModel toModel(Pedido pedido) {

        return modelMapper.map(pedido, PedidoModel.class);
    }

    public PedidoStatusResumoModel toModelResumo(Pedido pedido) {
        return modelMapper.map(pedido, PedidoStatusResumoModel.class);
    }

    public Pedido toDomainObject(PedidoInput input) {
        return modelMapper.map(input, Pedido.class);
    }

    public void copyToDomainObject(PedidoInput input, Pedido pedido) {
        modelMapper.map(input, pedido);
    }
}
