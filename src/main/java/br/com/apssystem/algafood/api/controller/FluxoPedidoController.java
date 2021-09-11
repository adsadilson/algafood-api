package br.com.apssystem.algafood.api.controller;

import br.com.apssystem.algafood.api.mapper.PedidoResumoMapper;
import br.com.apssystem.algafood.api.model.PedidoStatusResumoModel;
import br.com.apssystem.algafood.domain.service.FluxoPedidoService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pedidos/{codigo}")
@AllArgsConstructor
public class FluxoPedidoController {

    private FluxoPedidoService fluxoPedidoService;
    private PedidoResumoMapper mapper;


    @PutMapping("/confirmacao")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void confirmar(@PathVariable String codigo) {
        fluxoPedidoService.pedidoConfirmado(codigo);
    }

    @PutMapping("/entrega")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void entregue(@PathVariable String codigo) {
        fluxoPedidoService.pedidoEntregar(codigo);
    }

    @PutMapping("/cancelar")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void cancelado(@PathVariable String codigo) {
        fluxoPedidoService.pedidoCancelado(codigo);
    }

    @GetMapping("/status")
    public PedidoStatusResumoModel buscar(@PathVariable String codigo) {
        return mapper.toModelResumo(fluxoPedidoService.buscarPorCodigo(codigo));
    }
}
