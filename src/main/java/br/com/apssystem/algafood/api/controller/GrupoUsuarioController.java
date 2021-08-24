package br.com.apssystem.algafood.api.controller;

import java.util.List;
import java.util.stream.Collectors;

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

import br.com.apssystem.algafood.api.mapper.GrupoUsuarioMapper;
import br.com.apssystem.algafood.api.model.GrupoUsuarioModel;
import br.com.apssystem.algafood.api.model.input.GrupoUsuarioInput;
import br.com.apssystem.algafood.api.model.input.PermissaoIdInput;
import br.com.apssystem.algafood.domain.model.GrupoUsuario;
import br.com.apssystem.algafood.domain.model.Permissao;
import br.com.apssystem.algafood.domain.repository.PermissaoRepository;
import br.com.apssystem.algafood.domain.service.GrupoUsuarioService;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/grupo-usuarios")
@AllArgsConstructor
public class GrupoUsuarioController {

	private GrupoUsuarioService service;
	private PermissaoRepository permissaoRepository;
	private GrupoUsuarioMapper mapper;

	@GetMapping
	public ResponseEntity<List<GrupoUsuarioModel>> listarTodos() {
		return ResponseEntity.ok(mapper.toCollectionModel(service.listarTodos()));
	}

	@GetMapping("/{id}")
	public ResponseEntity<GrupoUsuarioModel> buscarPorId(@PathVariable Long id) {
		GrupoUsuario grupo = service.buscarPorId(id);
		return ResponseEntity.ok(mapper.toModel(grupo));
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public GrupoUsuarioModel adicionar(@Valid @RequestBody GrupoUsuarioInput input) {
		GrupoUsuario grupo = mapper.toDomainObject(input);
		
		List<Long> idsPermissoes = input.getPermissaoIdInputs()
				.stream()
				.map(PermissaoIdInput::getId)
				.collect(Collectors.toList());
		
		List<Permissao> permissoes = permissaoRepository.findAllById(idsPermissoes);
		grupo.getPermissoes().addAll(permissoes);
		return mapper.toModel(service.adicionar(grupo));
	}

	@PutMapping
	public ResponseEntity<GrupoUsuarioModel> atualizar(@Valid @RequestBody GrupoUsuarioInput input) {
		GrupoUsuario grupo = service.buscarPorId(input.getId());
		List<Long> idsPermissoes = input.getPermissaoIdInputs()
				.stream()
				.map(PermissaoIdInput::getId)
				.collect(Collectors.toList());
		
		List<Permissao> permissoes = permissaoRepository.findAllById(idsPermissoes);
		grupo.getPermissoes().clear();
		grupo.getPermissoes().addAll(permissoes);
		mapper.copyToDomainObject(input, grupo);
		return ResponseEntity.ok(mapper.toModel(service.atualizar(grupo)));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> excluir(@PathVariable Long id) {
		service.excluir(id);
		return new ResponseEntity<String>("Grupo de Usuário de código " + id + " foi excluído com sucesso!",
				HttpStatus.NO_CONTENT);
	}

}
