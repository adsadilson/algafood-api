package br.com.apssystem.algafood.api.controller.openapi.controller;

import br.com.apssystem.algafood.api.exception.Problem;
import br.com.apssystem.algafood.api.model.CozinhaModel;
import br.com.apssystem.algafood.api.model.input.CozinhaInput;
import io.swagger.annotations.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

@Api(tags = "Cozinhas")
public interface CozinhaControllerOpenApi {

    @ApiOperation("Cadastrar uma cozinha")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Cozinha cadastrada"),
    })
    CozinhaModel salvar(
            @ApiParam(name = "corpo", value = "Representação de uma nova cozinha")
                    CozinhaInput cozinhaInput);

    @ApiOperation("Atualiza uma cozinha por ID")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Cozinha atualizada"),
            @ApiResponse(code = 404, message = "Cozinha não encontrada", response = Problem.class)
    })
     CozinhaModel atualizar(
            @ApiParam(name = "corpo", value = "Representação de uma nova cozinha")
            CozinhaInput cozinhaInput, Long id);

    @ApiOperation("Excluir uma cozinha por ID")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Cozinha excluída"),
            @ApiResponse(code = 404, message = "Cozinha não encontrada", response = Problem.class)
    })
    ResponseEntity<Void> excluir(
            @ApiParam(value = "ID de uma Cozinha", example = "1")
            Long id) ;

    @ApiOperation("Busca uma cozinha por ID")
    @ApiResponses({
            @ApiResponse(code = 400, message = "ID da cozinha inválido", response = Problem.class),
            @ApiResponse(code = 404, message = "Cozinha não encontrada", response = Problem.class)
    })
    CozinhaModel buscarPorId(
            @ApiParam(value = "ID de uma cozinha", example = "1")
            Long id);

    @ApiOperation("Busca uma cozinha por Nome")
    Page<CozinhaModel> buscarPorNome(Pageable pageable,String nome);

    @ApiOperation("Busca todas as  cozinhas")
    Page<CozinhaModel> listarTodos(Pageable pageable);

}
