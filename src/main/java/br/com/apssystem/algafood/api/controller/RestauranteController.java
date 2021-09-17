package br.com.apssystem.algafood.api.controller;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ReflectionUtils;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.SmartValidator;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.apssystem.algafood.api.exception.EntidadeNaoEncontradaException;
import br.com.apssystem.algafood.api.exception.NegocioException;
import br.com.apssystem.algafood.api.exception.ValidacaoException;
import br.com.apssystem.algafood.api.mapper.RestauranteMapper;
import br.com.apssystem.algafood.api.model.RestauranteModel;
import br.com.apssystem.algafood.api.model.input.RestauranteInput;
import br.com.apssystem.algafood.domain.model.Restaurante;
import br.com.apssystem.algafood.domain.service.CozinhaService;
import br.com.apssystem.algafood.domain.service.RestauranteService;
import lombok.AllArgsConstructor;

@Api(tags = "Restaurantes")
@RestController
@RequestMapping(path = "/restaurantes", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class RestauranteController {

	private RestauranteService service;
	private CozinhaService cozinhaService;
	private RestauranteMapper mapper;
	private SmartValidator validator;

	@ApiOperation("Cadastrar um restaurante")
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public RestauranteModel salvar(@Valid @RequestBody RestauranteInput restauranteInput) {
		Restaurante restaurante = mapper.toDomainObject(restauranteInput);
		restaurante = service.adicionar(restaurante);
		return mapper.toModel(restaurante);
	}

	@ApiOperation("Atualiza um restaurante por ID")
	@PutMapping("/{id}")
	public ResponseEntity<RestauranteModel> atualizar(@Valid @RequestBody RestauranteInput restauranteInput,
			@PathVariable Long id) {
		Restaurante restauranteAtual = service.buscarPorId(id);
		mapper.copyToDomainObject(restauranteInput, restauranteAtual);
		restauranteAtual = service.autalizar(restauranteAtual);
		return ResponseEntity.ok(mapper.toModel(restauranteAtual));
	}

	@ApiOperation("Excluir um restaurante por ID")
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> excluir(@PathVariable Long id) {
		service.buscarPorId(id);
		service.excluir(id);
		return ResponseEntity.noContent().build();
	}

	@ApiOperation("Busca todos os restaurantes")
	@GetMapping()
	public List<RestauranteModel> listarTodos() {
		List<Restaurante> list = service.listarTodos();
		return mapper.toCollectionModel(list);
	}

	@ApiOperation("Busca um restaurante por ID")
	@GetMapping("/{id}")
	public ResponseEntity<RestauranteModel> buscarPorId(@PathVariable Long id) {
		Restaurante restaurante = service.buscarPorId(id);
		return ResponseEntity.ok(mapper.toModel(restaurante));
	}

	@ApiOperation("Busca um restaurante por Nome")
	@GetMapping("/por-nome")
	public List<RestauranteModel> consultarPorNome(String nome, Long cozinhaId) {
		return mapper.toCollectionModel(service.consultarPorNome(nome, cozinhaId));
	}

	@ApiOperation("Atualização parcial de um restaurante")
	@PatchMapping("/{id}")
	public ResponseEntity<Restaurante> atualizarParcial(@Valid @RequestBody Map<String, Object> campos,
			@PathVariable Long id) {
		Restaurante restaurante = service.buscarPorId(id);
		merge(campos, restaurante);
		validate(restaurante, "restaurante");
		service.autalizar(restaurante);
		return ResponseEntity.ok(restaurante);
	}

	@ApiOperation("Ativação de restaurante em massa")
	@PutMapping("/ativacoes")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void ativarMultiplos(@RequestBody List<Long> restauranteIds) {
		try {
			service.ativar(restauranteIds);
		} catch (EntidadeNaoEncontradaException e) {
			throw new NegocioException(e.getMessage(), e);
		}
	}

	@ApiOperation("Inativação de restaurante em massa")
	@DeleteMapping("/ativacoes")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void inativarMultiplos(@RequestBody List<Long> restauranteIds) {
		try {
			service.inativar(restauranteIds);
		} catch (EntidadeNaoEncontradaException e) {
			throw new NegocioException(e.getMessage(), e);
		}
	}

	@ApiOperation("Ativa um restaurante por ID")
	@PutMapping("{id}/ativo")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void ativo(@PathVariable Long id) {
		service.ativar(id);
	}

	@ApiOperation("Inativa um restaurante por ID")
	@DeleteMapping("{id}/ativo")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void inativo(@PathVariable Long id) {
		service.inativar(id);
	}

	@ApiOperation("Abertura de um restaurante por ID")
	@PutMapping("/{id}/abertura")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void abrir(@PathVariable Long id) {
	    service.abrir(id);
	}

	@ApiOperation("Fechamento de um restaurante por ID")
	@PutMapping("/{id}/fechamento")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void fechar(@PathVariable Long id) {
		service.fechar(id);
	}        

	private void validate(Restaurante restaurante, String objectName) {
		BeanPropertyBindingResult bindingResult = new BeanPropertyBindingResult(restaurante, objectName);
		validator.validate(restaurante, bindingResult);
		if (bindingResult.hasErrors()) {
			throw new ValidacaoException(bindingResult);
		}
	}

	private void merge(Map<String, Object> dadosOrigem, Restaurante restauranteDestino) {
		/* Verificar se a cozinha existe */
		cozinhaService.buscarPorId(restauranteDestino.getCozinha().getId());

		/**
		 * Criar um ObjectMapper do jackson para converter os dados recebido conformer o
		 * tipo da variavel
		 **/
		ObjectMapper objectMapper = new ObjectMapper();
		Restaurante restOrigem = objectMapper.convertValue(dadosOrigem, Restaurante.class);

		/** Pecorre os dados para atribuir os valores **/
		dadosOrigem.forEach((nomePropriedade, vlrPropriedade) -> {
			/**
			 * O utilizar reflectionUtil para capiturar a propriedade e depois alterar o
			 * acesso da variavel para ser possivel setar o valor novo.
			 **/
			Field field = ReflectionUtils.findField(Restaurante.class, nomePropriedade);
			field.setAccessible(true);
			Object novoValor = ReflectionUtils.getField(field, restOrigem);
			ReflectionUtils.setField(field, restauranteDestino, novoValor);
		});
	}
}
