package br.com.apssystem.algafood.domain.listener;

import br.com.apssystem.algafood.domain.event.PedidoCanceladoEvent;
import br.com.apssystem.algafood.domain.model.Pedido;
import br.com.apssystem.algafood.infrastructure.email.EnvioEmailService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
@AllArgsConstructor
public class NotificacaoClientePedidoCanceladoListener {

    private EnvioEmailService envioEmail;

    @TransactionalEventListener(phase = TransactionPhase.BEFORE_COMMIT)
    public void aoCancelarPedido(PedidoCanceladoEvent event) {
        Pedido pedido = event.getPedido();

        var mensagem = EnvioEmailService.Mensagem.builder()
                .assunto(pedido.getRestaurante().getNome() + " - Pedido cancelado")
                .corpo("pedido-cancelado.html")
                .atributo("pedido", pedido)
                .destinatario(pedido.getCliente().getEmail())
                .build();

        envioEmail.enviar(mensagem);
    }
}
