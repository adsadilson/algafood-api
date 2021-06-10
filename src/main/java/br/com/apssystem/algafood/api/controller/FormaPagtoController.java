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

import br.com.apssystem.algafood.domain.model.FormaPagto;
import br.com.apssystem.algafood.domain.service.FormaPagtoService;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/formaPagtos")
@AllArgsConstructor
public class FormaPagtoController {

	private FormaPagtoService formaPagtoService;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public FormaPagto salvar(@Valid @RequestBody FormaPagto formaPagto) {
		FormaPagto formaPagtoSalvo = formaPagtoService.salvar(formaPagto);
		return formaPagtoSalvo;
	}

	@PutMapping("/{id}")
	public ResponseEntity<FormaPagto> atualizar(@Valid @RequestBody FormaPagto formaPagto, @PathVariable Long id) {
		FormaPagto formaPagtoSalvo = formaPagtoService.atualizar(formaPagto, id);
		return ResponseEntity.ok(formaPagtoSalvo);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> excluir(@PathVariable Long id) {
		formaPagtoService.excluir(id);
		return ResponseEntity.noContent().build();
	}

	@GetMapping("/{id}")
	public ResponseEntity<FormaPagto> buscarPorId(@PathVariable Long id) {
		FormaPagto formaPagto = formaPagtoService.buscarPorId(id);
		return ResponseEntity.ok(formaPagto);
	}

	@GetMapping
	public ResponseEntity<List<FormaPagto>> listarTodos() {
		List<FormaPagto> result = formaPagtoService.listarTodos();
		return ResponseEntity.ok(result);
	}

}
