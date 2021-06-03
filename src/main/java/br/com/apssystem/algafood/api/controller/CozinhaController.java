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

import br.com.apssystem.algafood.domain.model.Cozinha;
import br.com.apssystem.algafood.domain.service.CozinhaService;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/cozinhas")
@AllArgsConstructor
public class CozinhaController {

	private CozinhaService cozinhaService;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Cozinha salvar(@Valid @RequestBody Cozinha cozinha) {
		return cozinhaService.salvar(cozinha);
	}

	@PutMapping("/{id}")
	public Cozinha atualizar(@Valid @RequestBody Cozinha cozinha, @PathVariable Long id) {
		return cozinhaService.atualizar(cozinha, id);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> excluir(@PathVariable Long id) {
		cozinhaService.excluir(id);
		return ResponseEntity.noContent().build();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Cozinha> buscarPorId(@PathVariable Long id) {
		Cozinha cozinha = cozinhaService.buscarPorId(id);
		return ResponseEntity.ok(cozinha);
	}

	@GetMapping("/porNome/{nome}")
	public List<Cozinha> buscarPorNome(@PathVariable String nome) {
		return cozinhaService.buscarPorNome(nome);
	}

	@GetMapping
	public List<Cozinha> listarTodos() {
		return cozinhaService.listarTodos();
	}

}
