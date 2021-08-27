package br.com.apssystem.algafood.domain.service;

import br.com.apssystem.algafood.domain.model.Pedido;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class FluxoPedidoService {

    private PedidoService pedidoService;

    @Transactional
    public void pedidoConfirmado(String id) {
        Pedido obj = pedidoService.buscarPorCodigo(id);
        obj.confirmar();
    }

    @Transactional
    public void pedidoCancelado(String codigo) {
        Pedido obj = pedidoService.buscarPorCodigo(codigo);
        obj.cancelar();
    }

    @Transactional
    public void pedidoEntregar(String codigo) {
        Pedido obj = pedidoService.buscarPorCodigo(codigo);
        obj.entregar();
    }

    public Pedido buscarPorCodigo(String codigo) {
        return pedidoService.buscarPorCodigo(codigo);
    }
}
