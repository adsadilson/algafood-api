package br.com.apssystem.algafood.api.controller;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ReflectionUtils;
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

import br.com.apssystem.algafood.domain.model.Cozinha;
import br.com.apssystem.algafood.domain.model.Restaurante;
import br.com.apssystem.algafood.domain.service.CozinhaService;
import br.com.apssystem.algafood.domain.service.RestauranteService;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/restaurantes")
@AllArgsConstructor
public class RestauranteController {

	private RestauranteService restauranteService;

	private CozinhaService cozinhaService;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Restaurante salvar(@Valid @RequestBody Restaurante restaurante) {
		Cozinha cozinha = cozinhaService.buscarPorId(restaurante.getCozinha().getId());
		restaurante.setCozinha(cozinha);
		return restauranteService.salvar(restaurante);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Restaurante> atualizar(@Valid @RequestBody Restaurante restaurante, @PathVariable Long id) {
		cozinhaService.buscarPorId(restaurante.getCozinha().getId());
		Restaurante restauranteSalvo = restauranteService.autalizar(restaurante, id);
		return ResponseEntity.ok(restauranteSalvo);
	}

	@PatchMapping("/{id}")
	public ResponseEntity<Restaurante> atualizarParcial(@Valid @RequestBody Map<String, Object> campos,
			@PathVariable Long id) {
		Restaurante restaurante = restauranteService.buscarPorId(id);
		merge(campos, restaurante);
		restauranteService.autalizar(restaurante, id);
		return ResponseEntity.ok(restaurante);
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
			// System.out.println(nomePropriedade + " - " + vlrPropriedade);
			ReflectionUtils.setField(field, restauranteDestino, novoValor);
		});
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> excluir(@PathVariable Long id) {
		restauranteService.buscarPorId(id);
		restauranteService.excluir(id);
		return ResponseEntity.noContent().build();
	}

	@GetMapping()
	public List<Restaurante> listarTodos() {
		return restauranteService.listarTodos();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Restaurante> buscarPorId(@PathVariable Long id) {
		Restaurante restaurante = restauranteService.buscarPorId(id);
		return ResponseEntity.ok(restaurante);
	}
}
