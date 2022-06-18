package br.com.apssystem.algafood.api.controller.openapi.controller;

import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;

import br.com.apssystem.algafood.api.exception.Problem;
import br.com.apssystem.algafood.api.model.CidadeModel;
import br.com.apssystem.algafood.api.model.input.CidadeInput;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Cidades")
public interface CidadeControllerOpenApi {

	@ApiOperation("Cadastrar uma cidade")
	@ApiResponses({
			@ApiResponse(code = 201, message = "Cidade cadastrada"),
	})
	CidadeModel salvar(@ApiParam(name = "corpo", value = "Representação de uma nova cidade")
			CidadeInput cidadeInput);

	@ApiOperation("Atualiza uma cidade por ID")
	@ApiResponses({
			@ApiResponse(code = 200, message = "Cidade atualizada"),
			@ApiResponse(code = 404, message = "Cidade não encontrada", response = Problem.class)
	})
	ResponseEntity<CidadeModel> atualizar(@ApiParam(name = "corpo", value = "Representação de uma nova cidade")
			CidadeInput cidadeInput, Long id) ;


	@ApiOperation("Excluir uma cidade por ID")
	@ApiResponses({
			@ApiResponse(code = 204, message = "Cidade excluída"),
			@ApiResponse(code = 404, message = "Cidade não encontrada", response = Problem.class)
	})
	ResponseEntity<Void> excluir(
			@ApiParam(value = "ID de uma cidade", example = "1")
			Long id);


	@ApiOperation("Busca uma cidade por ID")
	@ApiResponses({
			@ApiResponse(code = 400, message = "ID da cidade inválido", response = Problem.class),
			@ApiResponse(code = 404, message = "Cidade não encontrada", response = Problem.class)
	})
	CidadeModel buscarPorId(
			@ApiParam(value = "ID de uma cidade", example = "1")
					Long id);

	@ApiOperation("Busca todas as  cidades")
	CollectionModel<CidadeModel> listarTodos();

}
