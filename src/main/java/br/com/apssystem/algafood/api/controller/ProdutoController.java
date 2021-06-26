package br.com.apssystem.algafood.api.controller;

import java.util.List;

import javax.validation.Valid;

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

	private ProdutoService service;
	private ProdutoMapper mapper;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ProdutoModel salvar(@Valid @RequestBody ProdutoInput input) {
		Produto produto = mapper.toDomainObject(input);
		return mapper.toModel(service.salvar(produto));
	}

	@PutMapping("/{id}")
	public ResponseEntity<ProdutoModel> atualizar(@Valid @RequestBody ProdutoInput input, @PathVariable Long id) {
		Produto produto = service.buscarPorId(id);
		mapper.copyToDomainObject(input, produto);
		return ResponseEntity.ok(mapper.toModel(service.atualizar(produto)));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> excluir(@PathVariable Long id) {
		service.excluir(id);
		return new ResponseEntity<String>("Produto de código " + id + " foi excluído com sucesso!",
				HttpStatus.NO_CONTENT);
	}

	@GetMapping("{id}")
	public ResponseEntity<ProdutoModel> buscarPorId(@PathVariable Long id) {
		Produto produto = service.buscarPorId(id);
		return ResponseEntity.ok(mapper.toModel(produto));
	}

	@GetMapping
	public List<ProdutoModel> listarTodos() {
		return mapper.toColletionModel(service.listarTodos());
	}

}
