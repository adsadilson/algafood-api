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

import br.com.apssystem.algafood.api.mapper.PermissaoMapper;
import br.com.apssystem.algafood.api.model.PermissaoModel;
import br.com.apssystem.algafood.api.model.input.PermissaoInput;
import br.com.apssystem.algafood.domain.model.Permissao;
import br.com.apssystem.algafood.domain.service.PermissaoService;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/permissoes")
@AllArgsConstructor
public class PermissaoController {

	private PermissaoService permissaoService;
	private PermissaoMapper mapper;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<PermissaoModel> adicionar(@Valid @RequestBody PermissaoInput input) {
		Permissao obj = mapper.toDomainObject(input);
		permissaoService.salvar(obj);
		return ResponseEntity.ok(mapper.toModel(obj));
	}
	
	@PutMapping
	public ResponseEntity<PermissaoModel> atualizar(@Valid @RequestBody PermissaoInput input){
		Permissao obj = permissaoService.buscarPorId(input.getId());
		mapper.copyToDomainObject(input, obj);
		permissaoService.atualizar(obj);
		return ResponseEntity.ok(mapper.toModel(obj));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> excluir(@PathVariable Long id) {
		permissaoService.excluir(id);
		return new ResponseEntity<String>(String.format("Permissão de código %d foi excluído com sucesso!", id),
				HttpStatus.NO_CONTENT);
	}

	@GetMapping
	public ResponseEntity<List<PermissaoModel>> listarTodas() {
		return ResponseEntity.ok(mapper.toCollectionModel(permissaoService.listarTodos()));
	}

	@GetMapping("/{id}")
	public ResponseEntity<PermissaoModel> buscarPorId(@PathVariable Long id) {
		Permissao obj = permissaoService.buscarPorId(id);
		return ResponseEntity.ok(mapper.toModel(obj));
	}
}
