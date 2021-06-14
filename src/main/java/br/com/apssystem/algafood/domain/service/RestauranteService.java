package br.com.apssystem.algafood.domain.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.apssystem.algafood.api.exception.RegistroEmUsoException;
import br.com.apssystem.algafood.api.exception.RegistroNaoEncontradoException;
import br.com.apssystem.algafood.domain.model.Restaurante;
import br.com.apssystem.algafood.domain.repository.RestauranteRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class RestauranteService {

	private RestauranteRepository restauranteRepository;

	public Restaurante salvar(Restaurante restaurante) {
		if (restaurante.getId() == null) {
			restaurante.setAtivo(true);
		}
		return restauranteRepository.save(restaurante);
	}

	public Restaurante autalizar(Restaurante restaurante, Long id) {
		Restaurante restauranteSalvo = buscarPorId(id);
		BeanUtils.copyProperties(restaurante, restauranteSalvo, "id", "formasPagtos", "endereco", "dataCadastro",
				"produtos");
		return restauranteRepository.save(restauranteSalvo);
	}

	public void excluir(Long id) {
		try {
			restauranteRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new RegistroNaoEncontradoException("Restaurante", id);
		} catch (DataIntegrityViolationException e) {
			throw new RegistroEmUsoException("Restaurante", id);
		}
	}

	public List<Restaurante> listarTodos() {
		return restauranteRepository.findAll();
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
