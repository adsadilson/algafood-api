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
    public void pedidoConfirmado(Long id) {
        Pedido obj = pedidoService.buscarPorId(id);
        obj.confirmar();
    }

    @Transactional
    public void pedidoCancelado(Long id) {
        Pedido obj = pedidoService.buscarPorId(id);
        obj.cancelar();
    }

    @Transactional
    public void pedidoEntregar(Long id) {
        Pedido obj = pedidoService.buscarPorId(id);
        obj.entregar();
    }

    public Pedido buscarPorId(Long id) {
        return pedidoService.buscarPorId(id);
    }
}
