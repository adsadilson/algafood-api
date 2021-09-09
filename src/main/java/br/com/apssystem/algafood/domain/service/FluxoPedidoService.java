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
    private EnvioEmailService envioEmailService;

    @Transactional
    public void pedidoConfirmado(String id) {
        Pedido obj = pedidoService.buscarPorCodigo(id);
        obj.confirmar();

        var mensagem = EnvioEmailService.Mensagem.builder()
                .assunto(obj.getRestaurante().getNome() + " - Pedido confirmado")
                .corpo("O pedido de código <strong>"
                        + obj.getCodigo() + "</strong> foi confirmado!")
                .destinatario(obj.getCliente().getEmail())
                .destinatario("adilson.curso@yahoo.com.br")
                .build();

        envioEmailService.enviar(mensagem);
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
