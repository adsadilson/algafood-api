package br.com.apssystem.algafood.domain.listener;

import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

import br.com.apssystem.algafood.domain.event.PedidoConfirmadoEvent;
import br.com.apssystem.algafood.infrastructure.email.EnvioEmailService;
import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class NotificacaoClientePedidoConfirmadoListener {

    private EnvioEmailService envioEmailService;

    @TransactionalEventListener(phase = TransactionPhase.BEFORE_COMMIT)
    public void aoConfirmarPedido(PedidoConfirmadoEvent event) {
        var pedido = event.getPedido();
        var mensagem = EnvioEmailService.Mensagem.builder()
                .assunto(pedido.getRestaurante().getNome() + " - Pedido confirmado")
                .corpo("pedido-confirmado.html")
                .destinatario(pedido.getCliente().getEmail())
                .atributo("pedido", pedido)
                /* .destinatario("elber_gyn@hotmail.com")
                 .destinatario("generoso.fernando@gmail.com")*/
                .destinatario("adilson.curso@yahoo.com.br")
                .build();

        envioEmailService.enviar(mensagem);
    }
}
