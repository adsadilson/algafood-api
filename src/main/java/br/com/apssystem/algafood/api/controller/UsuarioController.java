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

import br.com.apssystem.algafood.api.mapper.UsuarioMapper;
import br.com.apssystem.algafood.api.model.UsuarioModel;
import br.com.apssystem.algafood.api.model.input.SenhaInput;
import br.com.apssystem.algafood.api.model.input.UsuarioInput;
import br.com.apssystem.algafood.domain.model.Usuario;
import br.com.apssystem.algafood.domain.service.UsuarioService;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/usuarios")
@AllArgsConstructor
public class UsuarioController {

	private UsuarioService service;
	private UsuarioMapper mapper;

	@GetMapping
	public List<UsuarioModel> listarTodos() {
		return mapper.toCollectionModel(service.listarTodos());
	}

	@GetMapping("/{id}")
	public ResponseEntity<UsuarioModel> buscarPorId(@PathVariable Long id) {
		return ResponseEntity.ok(mapper.toModel(service.buscarPorId(id)));
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<UsuarioModel> adicionar(@Valid @RequestBody UsuarioInput input) {
		Usuario usuario = mapper.toDomainObject(input);
		return ResponseEntity.ok(mapper.toModel(service.adicionar(usuario)));
	}

	@PutMapping("/{id}")
	public ResponseEntity<UsuarioModel> atualizar(@Valid @RequestBody UsuarioInput input, @PathVariable Long id) {
		Usuario usuario = service.buscarPorId(id);
		mapper.copyToDomainObject(input, usuario);
		return ResponseEntity.ok(mapper.toModel(service.atualizar(usuario)));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> excluir(@PathVariable Long id) {
		service.excluir(id);
		return new ResponseEntity<String>(String.format("Usuario de código %d foi excluído com sucesso!", id),
				HttpStatus.NO_CONTENT);
	}

	@PutMapping("/{id}/senha")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void alterarSenha(@PathVariable Long id, @RequestBody @Valid SenhaInput senha) {
		service.alterarSenha(id, senha.getSenhaAtual(), senha.getNovaSenha());
	}
}
