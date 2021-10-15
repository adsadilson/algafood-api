package br.com.apssystem.algafood.api.controller;

import br.com.apssystem.algafood.api.controller.openapi.controller.CidadeControllerOpenApi;
import br.com.apssystem.algafood.api.mapper.CidadeMapper;
import br.com.apssystem.algafood.api.model.CidadeModel;
import br.com.apssystem.algafood.api.model.input.CidadeInput;
import br.com.apssystem.algafood.core.utils.ResourceUriHelper;
import br.com.apssystem.algafood.domain.service.CidadeService;
import lombok.AllArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/cidades", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class CidadeController implements CidadeControllerOpenApi {

	private CidadeService service;
	private CidadeMapper mapper;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public CidadeModel salvar(@Valid @RequestBody CidadeInput cidadeInput) {
		var cidade = mapper.toDomainObject(cidadeInput);
		var cidadeModel = mapper.toModel(service.salvar(cidade));
		ResourceUriHelper.addUriInResponseHeader(cidadeModel.getId());
		return cidadeModel;
	}

	@PutMapping("/{id}")
	public ResponseEntity<CidadeModel> atualizar(@Valid @RequestBody CidadeInput cidadeInput, @PathVariable Long id) {
		var cidade = service.buscarPorId(id);
		mapper.copyToDomainObject(cidadeInput, cidade);
		return ResponseEntity.ok(mapper.toModel(service.atualizar(cidade)));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> excluir(@PathVariable Long id) {
		service.excluir(id);
		return ResponseEntity.noContent().build();
	}

	@GetMapping("/{id}")
	public CidadeModel buscarPorId(@PathVariable Long id) {
		return mapper.toModel(service.buscarPorId(id));
	}

	@GetMapping
	public CollectionModel<CidadeModel> listarTodos() {
		return mapper.toCollectionModel(service.listarTodos());
	}
}
