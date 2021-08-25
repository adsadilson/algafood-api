package br.com.apssystem.algafood.api.controller;

import java.util.List;

import javax.validation.Valid;

import br.com.apssystem.algafood.domain.model.Restaurante;
import br.com.apssystem.algafood.domain.service.RestauranteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.apssystem.algafood.api.mapper.ProdutoMapper;
import br.com.apssystem.algafood.api.model.ProdutoModel;
import br.com.apssystem.algafood.api.model.input.ProdutoInput;
import br.com.apssystem.algafood.domain.model.Produto;
import br.com.apssystem.algafood.domain.service.ProdutoService;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("produtos")
@AllArgsConstructor
public class ProdutoController {

	private ProdutoService produtoService;
	private ProdutoMapper mapper;
	private RestauranteService restauranteService;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ProdutoModel salvar(@Valid @RequestBody ProdutoInput input) {
		Restaurante restaurante = restauranteService.buscarPorId(input.getRestaurante().getId());
		Produto produto = mapper.toDomainObject(input);
		produto.setRestaurante(restaurante);
		return mapper.toModel(produtoService.salvar(produto));
	}

	@PutMapping
	public ResponseEntity<ProdutoModel> atualizar(@Valid @RequestBody ProdutoInput input) {
		Produto produto = produtoService.buscarPorId(input.getId());
		mapper.copyToDomainObject(input, produto);
		return ResponseEntity.ok(mapper.toModel(produtoService.atualizar(produto)));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> excluir(@PathVariable Long id) {
		produtoService.excluir(id);
		return ResponseEntity.noContent().build();
	}


	@GetMapping("/{id}")
	public ProdutoModel buscarPorId(@PathVariable Long restauranteId, @PathVariable Long produtoId) {
		Produto produto = produtoService.buscarPorIdAndRestaurante(restauranteId, produtoId);
		return mapper.toModel(produto);
	}

	@GetMapping
	public List<ProdutoModel> listarTodos() {
		return mapper.toColletionModel(produtoService.listarTodos());
	}

}
