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

import br.com.apssystem.algafood.api.mapper.FormaPagtoMapper;
import br.com.apssystem.algafood.api.model.FormaPagtoModel;
import br.com.apssystem.algafood.api.model.input.FormaPagtoInput;
import br.com.apssystem.algafood.domain.model.FormaPagto;
import br.com.apssystem.algafood.domain.service.FormaPagtoService;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/formaPagtos")
@AllArgsConstructor
public class FormaPagtoController {

	private FormaPagtoService service;
	private FormaPagtoMapper mapper;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public FormaPagtoModel salvar(@Valid @RequestBody FormaPagtoInput formaPagtoInput) {
		FormaPagto formaPagto = mapper.toDomainObject(formaPagtoInput);
		return mapper.toModel(service.salvar(formaPagto));
	}

	@PutMapping("/{id}")
	public ResponseEntity<FormaPagtoModel> atualizar(@Valid @RequestBody FormaPagtoInput formaPagtoInput,
			@PathVariable Long id) {
		FormaPagto formaPagto = mapper.toDomainObject(formaPagtoInput);
		return ResponseEntity.ok(mapper.toModel(service.atualizar(formaPagto, id)));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> excluir(@PathVariable Long id) {
		service.excluir(id);
		return new ResponseEntity<String>("Forma de Pagto de código " + id + " foi excluído com sucesso!",
				HttpStatus.NO_CONTENT);
	}

	@GetMapping("/{id}")
	public ResponseEntity<FormaPagtoModel> buscarPorId(@PathVariable Long id) {
		FormaPagto formaPagto = service.buscarPorId(id);
		return ResponseEntity.ok(mapper.toModel(formaPagto));
	}

	@GetMapping
	public ResponseEntity<List<FormaPagtoModel>> listarTodos() {
		List<FormaPagto> result = service.listarTodos();
		return ResponseEntity.ok(mapper.toCollectionModel(result));
	}

}
