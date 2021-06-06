package br.com.apssystem.algafood.api.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.apssystem.algafood.domain.model.Estado;
import br.com.apssystem.algafood.domain.service.EstadoService;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/estados")
@AllArgsConstructor
public class EstadoController {

	private EstadoService estadoService;

	@GetMapping
	public List<Estado> listarTodos() {
		return estadoService.listarTodos();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Estado> buscarPorId(@PathVariable Long id) {
		Estado estado = estadoService.buscarPorId(id);
		return ResponseEntity.ok(estado);
	}
}
