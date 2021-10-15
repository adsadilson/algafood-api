package br.com.apssystem.algafood.api.controller;

import java.util.List;

import javax.validation.Valid;

import br.com.apssystem.algafood.core.utils.ResourceUriHelper;
import br.com.apssystem.algafood.domain.model.Restaurante;
import br.com.apssystem.algafood.domain.service.RestauranteService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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

@Api(tags = "Produtos")
@RestController
@RequestMapping(path = "produtos", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class ProdutoController {

	private ProdutoService produtoService;
	private ProdutoMapper mapper;
	private RestauranteService restauranteService;

	@ApiOperation("Cadastrar um produto")
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ProdutoModel salvar(@Valid @RequestBody ProdutoInput input) {
		Restaurante restaurante = restauranteService.buscarPorId(input.getRestaurante().getId());
		Produto produto = mapper.toDomainObject(input);
		produto.setRestaurante(restaurante);
		ResourceUriHelper.addUriInResponseHeader(produto.getId());
		return mapper.toModel(produtoService.salvar(produto));
	}

	@ApiOperation("Atualizar um produto")
	@PutMapping
	public ResponseEntity<ProdutoModel> atualizar(@Valid @RequestBody ProdutoInput input) {
		Produto produto = produtoService.buscarPorId(input.getId());
		mapper.copyToDomainObject(input, produto);
		return ResponseEntity.ok(mapper.toModel(produtoService.atualizar(produto)));
	}

	@ApiOperation("Excluir um produto por ID")
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> excluir(@PathVariable Long id) {
		produtoService.excluir(id);
		return ResponseEntity.noContent().build();
	}


	@ApiOperation("Busca um produto por ID")
	@GetMapping("/{id}")
	public ProdutoModel buscarPorId(@PathVariable Long restauranteId, @PathVariable Long produtoId) {
		Produto produto = produtoService.buscarPorIdAndRestaurante(restauranteId, produtoId);
		return mapper.toModel(produto);
	}

	@ApiOperation("Busca todos os produtos")
	@GetMapping
	public List<ProdutoModel> listarTodos() {
		return mapper.toColletionModel(produtoService.listarTodos());
	}

}
