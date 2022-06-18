package br.com.apssystem.algafood.api.controller.openapi.controller;

import javax.validation.Valid;

import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.apssystem.algafood.api.exception.Problem;
import br.com.apssystem.algafood.api.model.PedidoModel;
import br.com.apssystem.algafood.api.model.input.PedidoInput;
import br.com.apssystem.algafood.domain.model.filter.PedidoFilter;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Pedidos")
public interface PedidoControllerOpenApi {


    @ApiOperation("Cadastrar um pedido")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Pedido registrado"),
    })
    PedidoModel salvar(@Valid @RequestBody PedidoInput input);

    @ApiOperation("Atualizar um pedido")
    ResponseEntity<PedidoModel> atualizar(@Valid @RequestBody PedidoInput input);

    @ApiOperation("Excluir um pedido por Codigo")
    ResponseEntity<Void> excluir(@PathVariable String codigo);

    @ApiOperation("Busca um pedido por código")
    @ApiResponses({
            @ApiResponse(code = 404, message = "Pedido não encontrado", response = Problem.class)
    })
    ResponseEntity<PedidoModel> buscarPorCodigo(@PathVariable String codigo);

    @ApiOperation("Busca um pedido por filtro")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "Nomes das propriedades para filtrar na resposta, separado por vírgula",
            name = "campos", paramType = "query", type = "string")
    })
    PagedModel<PedidoModel> pesquisar(PedidoFilter filtro, @PageableDefault(size = 2) Pageable pageable);

}
