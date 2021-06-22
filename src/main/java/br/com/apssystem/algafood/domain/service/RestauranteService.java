package br.com.apssystem.algafood.domain.service;

import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.apssystem.algafood.api.exception.RegistroEmUsoException;
import br.com.apssystem.algafood.api.exception.RegistroNaoEncontradoException;
import br.com.apssystem.algafood.domain.model.Cozinha;
import br.com.apssystem.algafood.domain.model.Restaurante;
import br.com.apssystem.algafood.domain.repository.RestauranteRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class RestauranteService {

	private RestauranteRepository restauranteRepository;
	private CozinhaService cozinhaService;

	@Transactional
	public Restaurante salvar(Restaurante restaurante) {
		if (restaurante.getId() == null) {
			restaurante.setAtivo(true);
		}
		Cozinha cozinha = cozinhaService.buscarPorId(restaurante.getCozinha().getId());
		restaurante.setCozinha(cozinha);
		return restauranteRepository.save(restaurante);
	}

	@Transactional
	public Restaurante autalizar(Restaurante restaurante) {
		return salvar(restaurante);
	}

	@Transactional
	public void excluir(Long id) {
		try {
			restauranteRepository.deleteById(id);
			restauranteRepository.flush();
		} catch (EmptyResultDataAccessException e) {
			throw new RegistroNaoEncontradoException("Restaurante", id);
		} catch (DataIntegrityViolationException e) {
			throw new RegistroEmUsoException("Restaurante", id);
		}
	}

	public List<Restaurante> listarTodos() {
		return restauranteRepository.teste();
	}

	public List<Restaurante> consultarPorNome(String nome, Long id) {
		return restauranteRepository.consultarPorNome(nome, id);
	}

	public Restaurante buscarPorId(Long id) {
		Restaurante restaurante = restauranteRepository.findById(id)
				.orElseThrow(() -> new RegistroNaoEncontradoException("Restaurante", id));
		return restaurante;
	}

}
