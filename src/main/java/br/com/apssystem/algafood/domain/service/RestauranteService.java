package br.com.apssystem.algafood.domain.service;

import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.apssystem.algafood.api.exception.RegistroEmUsoException;
import br.com.apssystem.algafood.api.exception.EntidadeNaoEncontradaException;
import br.com.apssystem.algafood.domain.model.Cidade;
import br.com.apssystem.algafood.domain.model.Cozinha;
import br.com.apssystem.algafood.domain.model.Restaurante;
import br.com.apssystem.algafood.domain.repository.RestauranteRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class RestauranteService {

	private RestauranteRepository repository;
	private CozinhaService cozinhaService;
	private CidadeService cidadeService;

	@Transactional
	public Restaurante adicionar(Restaurante restaurante) {
		if (restaurante.getId() == null) {
			restaurante.setAtivo(true);
		}
		
		Cozinha cozinha = cozinhaService.buscarPorId(restaurante.getCozinha().getId());
		
		restaurante.setCozinha(cozinha);
		
		if (restaurante.getEndereco().getCidade().getId() != null) {
			Cidade cidade = cidadeService.buscarPorId(restaurante.getEndereco().getCidade().getId());
			restaurante.getEndereco().setCidade(cidade);
		}
		return repository.save(restaurante);
	}

	@Transactional
	public Restaurante autalizar(Restaurante restaurante) {
		return adicionar(restaurante);
	}

	@Transactional
	public void excluir(Long id) {
		try {
			repository.deleteById(id);
			repository.flush();
		} catch (EmptyResultDataAccessException e) {
			throw new EntidadeNaoEncontradaException("Restaurante", id);
		} catch (DataIntegrityViolationException e) {
			throw new RegistroEmUsoException("Restaurante", id);
		}
	}

	public List<Restaurante> listarTodos() {
		return repository.teste();
	}

	public List<Restaurante> consultarPorNome(String nome, Long id) {
		return repository.consultarPorNome(nome, id);
	}

	public Restaurante buscarPorId(Long id) {
		Restaurante restaurante = repository.findById(id)
				.orElseThrow(() -> new EntidadeNaoEncontradaException("Restaurante", id));
		return restaurante;
	}

	@Transactional
	public void ativar(Long id) {
		Restaurante restauranteAtual = buscarPorId(id);
		restauranteAtual.ativar();
	}

	@Transactional
	public void inativar(Long id) {
		Restaurante restauranteAtual = buscarPorId(id);
		restauranteAtual.inativar();
	}

}
