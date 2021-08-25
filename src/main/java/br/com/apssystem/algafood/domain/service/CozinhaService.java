package br.com.apssystem.algafood.domain.service;

import java.util.List;


import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.apssystem.algafood.api.exception.NegocioException;
import br.com.apssystem.algafood.api.exception.RegistroEmUsoException;
import br.com.apssystem.algafood.api.exception.EntidadeNaoEncontradaException;
import br.com.apssystem.algafood.domain.model.Cozinha;
import br.com.apssystem.algafood.domain.repository.CozinhaRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CozinhaService {

	private static final String NOMECLASS = "Cozinha";
	private CozinhaRepository repository;

	@Transactional
	public Cozinha salvar(Cozinha cozinha) {
		cozinhaExistente(cozinha);
		return repository.save(cozinha);
	}

	@Transactional
	public Cozinha atualizar(Cozinha cozinha) {
		return salvar(cozinha);
	}

	@Transactional
	public void excluir(Long id) {
		try {
			repository.deleteById(id);
			repository.flush();
		} catch (EmptyResultDataAccessException e) {
			throw new EntidadeNaoEncontradaException(NOMECLASS, id);
		} catch (DataIntegrityViolationException e) {
			throw new RegistroEmUsoException(NOMECLASS, id);
		}
	}

	public List<Cozinha> listarTodos() {
		return repository.findAll();
	}

	public List<Cozinha> buscarPorNome(String nome) {
		return repository.findByNomeContaining(nome);
	}

	public Cozinha buscarPorId(Long id) {
		return repository.findById(id)
				.orElseThrow(() -> new EntidadeNaoEncontradaException(NOMECLASS, id));
	}

	public void cozinhaExistente(Cozinha cozinha) {
		boolean cozinhaPorNomeEmUso = repository.findByNome(cozinha.getNome()).stream()
				.anyMatch(cozinhaExistente -> !cozinhaExistente.equals(cozinha));
		if (cozinhaPorNomeEmUso) {
			throw new NegocioException(
					String.format("Já existe um cadastro de cozinha com nome %s!", cozinha.getNome()));
		}
	}
	
	public long count() {
		return repository.count();
	}
}
