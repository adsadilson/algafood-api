package br.com.apssystem.algafood.domain.service;

import br.com.apssystem.algafood.domain.model.Pedido;
import br.com.apssystem.algafood.infrastructure.email.EnvioEmailService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class FluxoPedidoService {

    private PedidoService pedidoService;

    @Transactional
    public void pedidoConfirmado(String id) {
        Pedido pedido = pedidoService.buscarPorCodigo(id);
        pedido.confirmar();
        pedidoService.salvar(pedido);
    }

    @Transactional
    public void pedidoCancelado(String codigo) {
        Pedido pedido = pedidoService.buscarPorCodigo(codigo);
        pedido.cancelar();
        pedidoService.salvar(pedido);
    }

    @Transactional
    public void pedidoEntregar(String codigo) {
        Pedido pedido = pedidoService.buscarPorCodigo(codigo);
        pedido.entregar();
    }

    public Pedido buscarPorCodigo(String codigo) {
        return pedidoService.buscarPorCodigo(codigo);
    }
}
