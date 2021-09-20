package br.com.apssystem.algafood.api.controller.openapi.controller;

import br.com.apssystem.algafood.api.exception.Problem;
import br.com.apssystem.algafood.domain.model.Estado;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Api(tags = "Estados")
public interface EstadoControllerOpenApi {

	@ApiOperation("Busca todas os estados")
	List<Estado> listarTodos();

	@ApiOperation("Busca um estado por ID")
	@ApiResponses({
			@ApiResponse(code = 400, message = "ID da estado inválido", response = Problem.class),
			@ApiResponse(code = 404, message = "Estado não encontrada", response = Problem.class)
	})
	ResponseEntity<Estado> buscarPorId(
			@ApiParam(value = "ID de uma estado", example = "1")
			@PathVariable Long id) ;
}
