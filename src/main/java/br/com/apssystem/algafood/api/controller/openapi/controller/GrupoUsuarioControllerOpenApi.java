package br.com.apssystem.algafood.api.controller.openapi.controller;

import br.com.apssystem.algafood.api.exception.Problem;
import br.com.apssystem.algafood.api.model.GrupoUsuarioModel;
import br.com.apssystem.algafood.api.model.input.GrupoUsuarioInput;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Api(tags = "Grupos de Usuários")
public interface GrupoUsuarioControllerOpenApi {

	@ApiOperation("Cadastrar um grupo de usuário")
	@ApiResponses({
			@ApiResponse(code = 201, message = "Grupo cadastrado"),
	})
	GrupoUsuarioModel salvar(
			@ApiParam(name = "corpo", value = "Representação de um novo grupo")
			GrupoUsuarioInput input);

	@ApiOperation("Atualizar um grupo de usuário")
	@ApiResponses({
			@ApiResponse(code = 200, message = "Grupo atualizado"),
			@ApiResponse(code = 404, message = "Grupo não encontrado", response = Problem.class)
	})
	ResponseEntity<GrupoUsuarioModel> atualizar(
			@ApiParam(value = "ID de um grupo", example = "1")
			GrupoUsuarioInput input);

	@ApiOperation("Excluir um grupo de usuário")
	@ApiResponses({
			@ApiResponse(code = 204, message = "Grupo excluído"),
			@ApiResponse(code = 404, message = "Grupo não encontrado", response = Problem.class)
	})
	ResponseEntity<Void> excluir(
			@ApiParam(value = "ID de um grupo", example = "1")
			Long id);

	@ApiOperation("Busca todos os grupos de usuários")
	ResponseEntity<List<GrupoUsuarioModel>> listarTodos();

	@ApiOperation("Busca grupo de usuário por ID")
	@ApiResponses({
			@ApiResponse(code = 400, message = "ID da grupo inválido", response = Problem.class),
			@ApiResponse(code = 404, message = "Grupo não encontrado", response = Problem.class)
	})
	ResponseEntity<GrupoUsuarioModel> buscarPorId(
			@ApiParam(value = "ID de um grupo", example = "1")
			Long id);

}
