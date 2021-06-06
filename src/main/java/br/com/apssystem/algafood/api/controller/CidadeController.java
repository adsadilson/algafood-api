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

import br.com.apssystem.algafood.domain.model.Cidade;
import br.com.apssystem.algafood.domain.service.CidadeService;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/cidades")
@AllArgsConstructor
public class CidadeController {

	private CidadeService cidadeService;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Cidade salvar(@Valid @RequestBody Cidade cidade) {
		return cidadeService.salvar(cidade);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Cidade> atualizar(@Valid @RequestBody Cidade cidade, @PathVariable Long id) {
		cidade = cidadeService.atualizar(cidade, id);
		return ResponseEntity.ok(cidade);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> excluir(@PathVariable Long id) {
		cidadeService.excluir(id);
		return ResponseEntity.noContent().build();
	}

	@GetMapping("/{id}")
	public Cidade buscarPorId(@PathVariable Long id) {
		return cidadeService.buscarPorId(id);
	}

	@GetMapping
	public List<Cidade> listarTodos() {
		return cidadeService.listarTodos();
	}

}
