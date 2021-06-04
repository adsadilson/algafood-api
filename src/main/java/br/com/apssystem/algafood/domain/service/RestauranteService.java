package br.com.apssystem.algafood.domain.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import br.com.apssystem.algafood.api.exception.NegocioException;
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
			restaurante.setDataCadastro(LocalDate.now());
		}
		return restauranteRepository.save(restaurante);
	}

	public Restaurante autalizar(Restaurante restaurante, Long id) {
		Restaurante restauranteSalvo = buscarPorId(id);
		BeanUtils.copyProperties(restaurante, restauranteSalvo, "id");
		return restauranteRepository.save(restauranteSalvo);
	}

	public void excluir(Long id) {
		buscarPorId(id);
		restauranteRepository.deleteById(id);
	}

	public List<Restaurante> listarTodos() {
		return restauranteRepository.findAll();
	}

	public Restaurante buscarPorId(Long id) {
		Restaurante restaurante = restauranteRepository.findById(id).orElseThrow(
				() -> new NegocioException(String.format("Não existe um cadastro de restaurante com código %d", id)));
		return restaurante;
	}

}
