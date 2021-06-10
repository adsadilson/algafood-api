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

import br.com.apssystem.algafood.domain.model.Produto;
import br.com.apssystem.algafood.domain.service.ProdutoService;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("produtos")
@AllArgsConstructor
public class ProdutoController {

	private ProdutoService produtoService;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Produto salvar(@Valid @RequestBody Produto produto) {
		Produto produtoSalva = produtoService.salvar(produto);
		return produtoSalva;
	}

	@PutMapping("/{id}")
	public ResponseEntity<Produto> atualizar(@Valid @RequestBody Produto produto, @PathVariable Long id) {
		Produto produtoSalvor = produtoService.atualizar(produto, id);
		return ResponseEntity.ok(produtoSalvor);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> excluir(@PathVariable Long id) {
		produtoService.excluir(id);
		return ResponseEntity.noContent().build();
	}

	@GetMapping("{id}")
	public ResponseEntity<Produto> buscarPorId(@PathVariable Long id) {
		Produto produto = produtoService.buscarPorId(id);
		return ResponseEntity.ok(produto);
	}

	@GetMapping
	public List<Produto> listarTodos() {
		return produtoService.listarTodos();
	}

}
