package br.com.apssystem.algafood.domain.service;

import br.com.apssystem.algafood.api.exception.NegocioException;
import br.com.apssystem.algafood.domain.enums.StatusPedido;
import br.com.apssystem.algafood.domain.model.Pedido;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class FluxoPedidoService {

    private static final String DESCRICAO = "O pedido de código %d não pode ser alterado de %s para %s ";
    private PedidoService pedidoService;

    @Transactional
    public void pedidoConfirmado(Long id) {
        Pedido obj = pedidoService.buscarPorId(id);
        if (!obj.getStatus().getDescricao().equals(StatusPedido.CRIADO.getDescricao())) {
            throw new NegocioException(String.format(DESCRICAO, id, obj.getStatus().getDescricao(),
                    StatusPedido.CONFIRMADO.getDescricao()));
        }
        obj.setStatus(StatusPedido.CONFIRMADO);
        obj.setDataConfirmacao(LocalDateTime.now());
    }

    @Transactional
    public void pedidoCancelado(Long id) {
        Pedido obj = pedidoService.buscarPorId(id);
        if (!obj.getStatus().getDescricao().equals(StatusPedido.CRIADO.getDescricao())) {
            throw new NegocioException(String.format(DESCRICAO, id,
                    obj.getStatus().getDescricao(), StatusPedido.CANCELADO.getDescricao()));
        }
        obj.setStatus(StatusPedido.CANCELADO);
        obj.setDataCancelamento(LocalDateTime.now());
    }

    @Transactional
    public void pedidoEntregue(Long id) {
        Pedido obj = pedidoService.buscarPorId(id);
        if (!obj.getStatus().getDescricao().equals(StatusPedido.CONFIRMADO.getDescricao())) {
            throw new NegocioException(String.format(DESCRICAO, id,
                    obj.getStatus().getDescricao(), StatusPedido.ENTREGUE.getDescricao()));
        }
        obj.setStatus(StatusPedido.ENTREGUE);
        obj.setDataEntrega(LocalDateTime.now());
    }

}
