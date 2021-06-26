package br.com.apssystem.algafood.domain.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.apssystem.algafood.api.exception.NegocioException;
import br.com.apssystem.algafood.domain.model.Estado;
import br.com.apssystem.algafood.domain.repository.EstadoRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class EstadoService {

	private EstadoRepository repository;

	public List<Estado> listarTodos() {
		return repository.findAll();
	}

	public Estado buscarPorId(Long id) {
		Estado estado = repository.findById(id).orElseThrow(() -> new NegocioException(
				String.format("Não exite nenhum cadastro de estado com esse código %d", id)));
		return estado;
	}
}
