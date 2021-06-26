package br.com.apssystem.algafood.domain.service;

import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.apssystem.algafood.api.exception.NegocioException;
import br.com.apssystem.algafood.api.exception.RegistroEmUsoException;
import br.com.apssystem.algafood.api.exception.RegistroNaoEncontradoException;
import br.com.apssystem.algafood.domain.model.Cidade;
import br.com.apssystem.algafood.domain.model.Estado;
import br.com.apssystem.algafood.domain.repository.CidadeRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CidadeService {

	private CidadeRepository repository;

	private EstadoService estadoService;

	@Transactional
	public Cidade salvar(Cidade cidade) {
		Estado estado = estadoService.buscarPorId(cidade.getEstado().getId());
		cidade.setEstado(estado);
		cidadeExistente(cidade.getNome(), estado.getSigla());
		return repository.save(cidade);
	}

	@Transactional
	public Cidade atualizar(Cidade cidade) {
		return salvar(cidade);
	}

	@Transactional
	public void excluir(Long id) {
		try {
			repository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new RegistroNaoEncontradoException("Cidade", id);
		} catch (DataIntegrityViolationException e) {
			throw new RegistroEmUsoException("Cidade", id);
		}
	}

	public Cidade buscarPorId(Long id) {
		Cidade cidade = repository.findById(id)
				.orElseThrow(() -> new RegistroNaoEncontradoException("Cidade", id));
		return cidade;
	}

	public List<Cidade> listarTodos() {
		return repository.findAll();
	}

	public boolean cidadeExistente(String cid, String uf) {
		Cidade cidade = repository.consultarCidadeEstado(cid, uf);
		if (null != cidade) {
			throw new NegocioException(
					String.format("Já existe um cadastro de cidade com nome %s para o estado %s ", cid, uf));
		}
		return false;
	}
}
