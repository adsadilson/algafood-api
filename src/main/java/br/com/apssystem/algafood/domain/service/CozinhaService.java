package br.com.apssystem.algafood.domain.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.apssystem.algafood.api.exception.RegistroEmUsoException;
import br.com.apssystem.algafood.api.exception.RegistroNaoEncontradoException;
import br.com.apssystem.algafood.api.exception.NegocioException;
import br.com.apssystem.algafood.domain.model.Cozinha;
import br.com.apssystem.algafood.domain.repository.CozinhaRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CozinhaService {

	private CozinhaRepository cozinhaRepository;

	public Cozinha salvar(Cozinha cozinha) {
		cozinhaExistente(cozinha);
		return cozinhaRepository.save(cozinha);
	}

	public Cozinha atualizar(Cozinha cozinha, Long id) {
		Cozinha cozinhaSalva = buscarPorId(id);
		BeanUtils.copyProperties(cozinha, cozinhaSalva, "id");
		return salvar(cozinhaSalva);
	}

	public void excluir(Long id) {
		try {
			cozinhaRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new RegistroNaoEncontradoException("Cozinha", id);
		} catch (DataIntegrityViolationException e) {
			throw new RegistroEmUsoException("Cozinha", id);
		}
	}

	public List<Cozinha> listarTodos() {
		return cozinhaRepository.findAll();
	}

	public List<Cozinha> buscarPorNome(String nome) {
		return cozinhaRepository.findByNomeContaining(nome);
	}

	public Cozinha buscarPorId(Long id) {
		Cozinha cozinha = cozinhaRepository.findById(id)
				.orElseThrow(() -> new RegistroNaoEncontradoException("Cozinha", id));
		return cozinha;
	}

	public void cozinhaExistente(Cozinha cozinha) {
		boolean cozinhaPorNomeEmUso = cozinhaRepository.findByNome(cozinha.getNome()).stream()
				.anyMatch(cozinhaExistente -> !cozinhaExistente.equals(cozinha));
		if (cozinhaPorNomeEmUso) {
			throw new NegocioException(
					String.format("Já existe um cadastro de cozinha com nome %s!", cozinha.getNome()));
		}
	}
}
