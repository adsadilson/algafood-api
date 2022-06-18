package br.com.apssystem.algafood.api.controller.openapi.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;

import br.com.apssystem.algafood.api.exception.Problem;
import br.com.apssystem.algafood.api.model.RestauranteModel;
import br.com.apssystem.algafood.api.model.input.RestauranteInput;
import br.com.apssystem.algafood.domain.model.Restaurante;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Restaurantes")
public interface RestauranteControllerOpenApi {


	@ApiOperation("Cadastrar um restaurante")
	@ApiResponses({
			@ApiResponse(code = 201, message = "Restaurante cadastrado"),
	})
	RestauranteModel salvar(
			@ApiParam(name = "corpo", value = "Representação de um novo restaurante")
			RestauranteInput restauranteInput) ;

	@ApiOperation("Atualiza um restaurante por ID")
	@ApiResponses({
			@ApiResponse(code = 200, message = "Restaurante atualizado"),
			@ApiResponse(code = 404, message = "Restaurante não encontrado", response = Problem.class)
	})
	ResponseEntity<RestauranteModel> atualizar(@ApiParam(name = "corpo", value = "Representação de um novo restaurante") RestauranteInput restauranteInput, Long id);

	@ApiOperation("Excluir um restaurante por ID")
	@ApiResponses({
			@ApiResponse(code = 204, message = "Restaurante excluído"),
			@ApiResponse(code = 404, message = "Restaurante não encontrado", response = Problem.class)
	})
	ResponseEntity<Void> excluir(Long id) ;

	@ApiOperation("Busca todos os restaurantes")
	List<RestauranteModel> listarTodos();

	@ApiOperation("Busca um restaurante por ID")
	@ApiResponses({
			@ApiResponse(code = 400, message = "ID da restaurante inválido", response = Problem.class),
			@ApiResponse(code = 404, message = "Restaurante não encontrado", response = Problem.class)
	})
	ResponseEntity<RestauranteModel> buscarPorId( @ApiParam(value = "ID de uma usuário", example = "1") Long id);

	@ApiOperation("Busca um restaurante por Nome")
	List<RestauranteModel> consultarPorNome(String nome, Long cozinhaId) ;

	@ApiOperation("Atualização parcial de um restaurante")
	ResponseEntity<Restaurante> atualizarParcial(Map<String, Object> campos,
			Long id);

	@ApiOperation("Ativação de restaurante em massa")
	void ativarMultiplos(List<Long> restauranteIds);

	@ApiOperation("Inativação de restaurante em massa")
	void inativarMultiplos(List<Long> restauranteIds);

	@ApiOperation("Ativa um restaurante por ID")
	void ativo(Long id);

	@ApiOperation("Inativa um restaurante por ID")
	void inativo(Long id);

	@ApiOperation("Abertura de um restaurante por ID")
	void abrir(Long id);

	@ApiOperation("Fechamento de um restaurante por ID")
	void fechar(Long id);



}
