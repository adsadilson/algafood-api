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

import br.com.apssystem.algafood.api.converter.CozinhaConverter;
import br.com.apssystem.algafood.api.model.CozinhaModel;
import br.com.apssystem.algafood.api.model.input.CozinhaInput;
import br.com.apssystem.algafood.domain.model.Cozinha;
import br.com.apssystem.algafood.domain.service.CozinhaService;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/cozinhas")
@AllArgsConstructor
public class CozinhaController {

	private CozinhaService serivce;
	private CozinhaConverter cozinhaConverter;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public CozinhaModel salvar(@Valid @RequestBody CozinhaInput cozinhaInput) {
		Cozinha cozinha = cozinhaConverter.toDomainObject(cozinhaInput);
		return cozinhaConverter.toModel(serivce.salvar(cozinha));
	}

	@PutMapping("/{id}")
	public CozinhaModel atualizar(@Valid @RequestBody CozinhaInput cozinhaInput, @PathVariable Long id) {
		Cozinha cozinha = serivce.buscarPorId(id);
		return cozinhaConverter.toModel(serivce.atualizar(cozinha));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> excluir(@PathVariable Long id) {
		serivce.excluir(id);
		return ResponseEntity.noContent().build();
	}

	@GetMapping("/{id}")
	public CozinhaModel buscarPorId(@PathVariable Long id) {
		return cozinhaConverter.toModel(serivce.buscarPorId(id));
	}

	@GetMapping("/porNome/{nome}")
	public List<CozinhaModel> buscarPorNome(@PathVariable String nome) {
		return cozinhaConverter.toCollectionModel(serivce.buscarPorNome(nome));
	}

	@GetMapping
	public List<CozinhaModel> listarTodos() {
		return cozinhaConverter.toCollectionModel(serivce.listarTodos());
	}

}
