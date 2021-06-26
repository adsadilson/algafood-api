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

import br.com.apssystem.algafood.api.mapper.CozinhaMapper;
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
	private CozinhaMapper mapper;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public CozinhaModel salvar(@Valid @RequestBody CozinhaInput cozinhaInput) {
		Cozinha cozinha = mapper.toDomainObject(cozinhaInput);
		return mapper.toModel(serivce.salvar(cozinha));
	}

	@PutMapping("/{id}")
	public CozinhaModel atualizar(@Valid @RequestBody CozinhaInput cozinhaInput, @PathVariable Long id) {
		Cozinha cozinha = serivce.buscarPorId(id);
		return mapper.toModel(serivce.atualizar(cozinha));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> excluir(@PathVariable Long id) {
		serivce.excluir(id);
		return new ResponseEntity<String>("Cozinha de código " + id + " foi excluído com sucesso!",
				HttpStatus.NO_CONTENT);
	}

	@GetMapping("/{id}")
	public CozinhaModel buscarPorId(@PathVariable Long id) {
		return mapper.toModel(serivce.buscarPorId(id));
	}

	@GetMapping("/porNome/{nome}")
	public List<CozinhaModel> buscarPorNome(@PathVariable String nome) {
		return mapper.toCollectionModel(serivce.buscarPorNome(nome));
	}

	@GetMapping
	public List<CozinhaModel> listarTodos() {
		return mapper.toCollectionModel(serivce.listarTodos());
	}

}
