package br.com.apssystem.algafood.api.controller;

import br.com.apssystem.algafood.api.mapper.PedidoResumoMapper;
import br.com.apssystem.algafood.api.model.PedidoStatusResumoModel;
import br.com.apssystem.algafood.domain.service.FluxoPedidoService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pedidos/{id}")
@AllArgsConstructor
public class FluxoPedidoController {

    private FluxoPedidoService fluxoPedidoService;
    private PedidoResumoMapper mapper;


    @PutMapping("/confirmacao")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void confirmar(@PathVariable Long id) {
        fluxoPedidoService.pedidoConfirmado(id);
    }

    @PutMapping("/entregar")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void entregue(@PathVariable Long id) {

        fluxoPedidoService.pedidoEntregar(id);
    }

    @PutMapping("/cancelar")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void cancelado(@PathVariable Long id) {
        fluxoPedidoService.pedidoCancelado(id);
    }

    @GetMapping("/status")
    public PedidoStatusResumoModel buscar(@PathVariable Long id) {
        return mapper.toModelResumo(fluxoPedidoService.buscarPorId(id));
    }
}
