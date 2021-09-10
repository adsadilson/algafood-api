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
        Pedido pedido = pedidoService.buscarPorCodigo(id);
        pedido.confirmar();

        var mensagem = EnvioEmailService.Mensagem.builder()
                .assunto(pedido.getRestaurante().getNome() + " - Pedido confirmado")
                .corpo("pedido-confirmado.html")
                .destinatario(pedido.getCliente().getEmail())
                .atributo("pedido",pedido)
               /* .destinatario("elber_gyn@hotmail.com")
                .destinatario("generoso.fernando@gmail.com")*/
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
