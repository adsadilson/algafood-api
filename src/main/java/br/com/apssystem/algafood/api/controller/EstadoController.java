package br.com.apssystem.algafood.api.controller;

import java.util.List;
import java.util.stream.Collectors;

import br.com.apssystem.algafood.api.controller.openapi.controller.EstadoControllerOpenApi;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.MediaType;
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
@RequestMapping(path = "/estados", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class EstadoController implements EstadoControllerOpenApi {

	private EstadoService service;

	@GetMapping
	public List<Estado> listarTodos() {
		List<Estado> uf = service.listarTodos();
		uf.stream().map(e -> e.add(WebMvcLinkBuilder.linkTo(EstadoController.class)
				.slash(e.getId()).withSelfRel().withSelfRel())).collect(Collectors.toList());
		return uf;
	}

	@GetMapping("/{id}")
	public ResponseEntity<Estado> buscarPorId(@PathVariable Long id) {
		Estado estado = service.buscarPorId(id);
		estado.add(WebMvcLinkBuilder.linkTo(EstadoController.class).withSelfRel().withRel("Lista de estados"));
		return ResponseEntity.ok(estado);
	}
}
