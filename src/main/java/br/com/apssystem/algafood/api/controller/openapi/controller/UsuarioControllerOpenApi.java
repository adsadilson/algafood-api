package br.com.apssystem.algafood.api.controller.openapi.controller;

import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;

import br.com.apssystem.algafood.api.exception.Problem;
import br.com.apssystem.algafood.api.model.UsuarioModel;
import br.com.apssystem.algafood.api.model.input.SenhaInput;
import br.com.apssystem.algafood.api.model.input.UsuarioAtulizarInput;
import br.com.apssystem.algafood.api.model.input.UsuarioInput;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Usuários")
public interface UsuarioControllerOpenApi {


    @ApiOperation("Cadastrar um usuário")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Usuário cadastrada"),
    })
    ResponseEntity<UsuarioModel> salvar(@ApiParam(name = "corpo", value = "Representação de um novo usuário") UsuarioInput input);

    @ApiOperation("Atualiza um usuário")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Usuário atualizado"),
            @ApiResponse(code = 404, message = "Usuário não encontrado", response = Problem.class)
    })
    ResponseEntity<UsuarioModel> atualizar(@ApiParam(name = "corpo", value = "Representação de um novo usuário") UsuarioAtulizarInput input) ;

    @ApiOperation("Excluir um usuário por ID")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Usuário excluído"),
            @ApiResponse(code = 404, message = "Usuário não encontrado", response = Problem.class)
    })
    ResponseEntity<Void> excluir(Long id) ;

    @ApiOperation("Busca todos os usuários")
    CollectionModel<UsuarioModel> listarTodos();

    @ApiOperation("Busca um usuário por ID")
    @ApiResponses({
            @ApiResponse(code = 400, message = "ID da usuário inválido", response = Problem.class),
            @ApiResponse(code = 404, message = "Usuário não encontrado", response = Problem.class)
    })
    ResponseEntity<UsuarioModel> buscarPorId(
            @ApiParam(value = "ID de uma usuário", example = "1")
            Long id);

    @ApiOperation("Alterar a senha do usuário")
    void alterarSenha(Long id,
                      @ApiParam(value = "Senha do usuário", example = "sxkheaw451")
                      SenhaInput senha);
}
