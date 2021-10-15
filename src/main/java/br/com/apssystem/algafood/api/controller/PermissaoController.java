package br.com.apssystem.algafood.api.controller;

import java.util.List;

import javax.validation.Valid;

import br.com.apssystem.algafood.core.utils.ResourceUriHelper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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

import br.com.apssystem.algafood.api.mapper.PermissaoMapper;
import br.com.apssystem.algafood.api.model.PermissaoModel;
import br.com.apssystem.algafood.api.model.input.PermissaoInput;
import br.com.apssystem.algafood.domain.model.Permissao;
import br.com.apssystem.algafood.domain.service.PermissaoService;
import lombok.AllArgsConstructor;

@Api(tags = "Permissões")
@RestController
@RequestMapping(path = "/permissoes", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class PermissaoController {

	private PermissaoService permissaoService;
	private PermissaoMapper mapper;

	@ApiOperation("Cadastrar permissões")
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<PermissaoModel> salvar(@Valid @RequestBody PermissaoInput input) {
		Permissao obj = mapper.toDomainObject(input);
		permissaoService.salvar(obj);
		ResourceUriHelper.addUriInResponseHeader(obj.getId());
		return ResponseEntity.ok(mapper.toModel(obj));
	}

	@ApiOperation("Atualizar permissões")
	@PutMapping
	public ResponseEntity<PermissaoModel> atualizar(@Valid @RequestBody PermissaoInput input){
		Permissao obj = permissaoService.buscarPorId(input.getId());
		mapper.copyToDomainObject(input, obj);
		permissaoService.atualizar(obj);
		return ResponseEntity.ok(mapper.toModel(obj));
	}

	@ApiOperation("Excluir permissões")
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> excluir(@PathVariable Long id) {
		permissaoService.excluir(id);
		return ResponseEntity.noContent().build();
	}

	@ApiOperation("Buscar permissões")
	@GetMapping
	public ResponseEntity<List<PermissaoModel>> listarTodas() {
		return ResponseEntity.ok(mapper.toCollectionModel(permissaoService.listarTodos()));
	}

	@ApiOperation("Busca permissões por ID")
	@GetMapping("/{id}")
	public ResponseEntity<PermissaoModel> buscarPorId(@PathVariable Long id) {
		Permissao obj = permissaoService.buscarPorId(id);
		return ResponseEntity.ok(mapper.toModel(obj));
	}
}
