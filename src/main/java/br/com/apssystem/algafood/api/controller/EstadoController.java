package br.com.apssystem.algafood.api.controller;

import java.util.List;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.apssystem.algafood.domain.model.Estado;
import br.com.apssystem.algafood.domain.service.EstadoService;
import lombok.AllArgsConstructor;

@Api(tags = "Estados")
@RestController
@RequestMapping("/estados")
@AllArgsConstructor
public class EstadoController {

	private EstadoService service;

	@ApiOperation("Busca todas os estados")
	@GetMapping
	public List<Estado> listarTodos() {
		return service.listarTodos();
	}

	@ApiOperation("Busca um estado por ID")
	@GetMapping("/{id}")
	public ResponseEntity<Estado> buscarPorId(@PathVariable Long id) {
		Estado estado = service.buscarPorId(id);
		return ResponseEntity.ok(estado);
	}
}
