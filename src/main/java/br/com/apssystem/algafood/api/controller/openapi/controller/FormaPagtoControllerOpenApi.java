package br.com.apssystem.algafood.api.controller.openapi.controller;

import br.com.apssystem.algafood.api.exception.Problem;
import br.com.apssystem.algafood.api.model.FormaPagtoModel;
import br.com.apssystem.algafood.api.model.input.FormaPagtoInput;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.ServletWebRequest;

import java.util.List;

@Api(tags = "Formas de pagamento")
public interface FormaPagtoControllerOpenApi {

    @ApiOperation("Busca todas as formas de pagamento")
    ResponseEntity<List<FormaPagtoModel>> listarTodos(ServletWebRequest request);

    @ApiOperation("Busca uma forma de pagamento por ID")
    @ApiResponses({
            @ApiResponse(code = 400, message = "ID da forma de pagamento inválido", response = Problem.class),
            @ApiResponse(code = 404, message = "Forma de pagamento não encontrada", response = Problem.class)
    })
    ResponseEntity<FormaPagtoModel> buscarPorId(
            @ApiParam(value = "ID de uma forma de pagamento", example = "1")
                    Long formaPagamentoId);

    @ApiOperation("Cadastra uma forma de pagamento")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Forma de pagamento cadastrada"),
    })
    FormaPagtoModel salvar(
            @ApiParam(name = "corpo", value = "Representação de uma nova forma de pagamento")
                    FormaPagtoInput formaPagamentoInput);

    @ApiOperation("Atualiza uma cidade por ID")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Forma de pagamento atualizada"),
            @ApiResponse(code = 404, message = "Forma de pagamento não encontrada", response = Problem.class)
    })
    ResponseEntity<FormaPagtoModel> atualizar(
            @ApiParam(name = "corpo", value = "Representação de uma forma de pagamento com os novos dados")
            FormaPagtoInput formaPagtoInput,
            @ApiParam(value = "ID de uma forma de pagamento", example = "1")
                    Long id);


    @ApiOperation("Exclui uma forma de pagamento por ID")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Forma de pagamento excluída"),
            @ApiResponse(code = 404, message = "Forma de pagamento não encontrada", response = Problem.class)
    })
    ResponseEntity<Void> excluir(Long id);
}
