package br.com.apssystem.algafood.api.controller;

import br.com.apssystem.algafood.api.mapper.PedidoResumoMapper;
import br.com.apssystem.algafood.api.model.PedidoStatusResumoModel;
import br.com.apssystem.algafood.domain.service.FluxoPedidoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@Api(tags = "Fluxo de Pedidos")
@RestController
@RequestMapping(path = "/pedidos/{codigo}", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class FluxoPedidoController {

    private FluxoPedidoService fluxoPedidoService;
    private PedidoResumoMapper mapper;


    @ApiOperation("Confirmação do pedido")
    @PutMapping("/confirmacao")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void confirmar(@PathVariable String codigo) {
        fluxoPedidoService.pedidoConfirmado(codigo);
    }

    @ApiOperation("Confirmação da entrega do pedido")
    @PutMapping("/entrega")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void entregue(@PathVariable String codigo) {
        fluxoPedidoService.pedidoEntregar(codigo);
    }

    @ApiOperation("Confirmação do cancelamento do pedido")
    @PutMapping("/cancelar")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void cancelado(@PathVariable String codigo) {
        fluxoPedidoService.pedidoCancelado(codigo);
    }

    @ApiOperation("Consulta o status do pedido")
    @GetMapping("/status")
    public PedidoStatusResumoModel buscar(@PathVariable String codigo) {
        return mapper.toModelResumo(fluxoPedidoService.buscarPorCodigo(codigo));
    }
}
