package br.com.apssystem.algafood.domain.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.apssystem.algafood.api.exception.EntidadeNaoEncontradaException;
import br.com.apssystem.algafood.api.exception.NegocioException;
import br.com.apssystem.algafood.api.exception.RegistroEmUsoException;
import br.com.apssystem.algafood.domain.model.Permissao;
import br.com.apssystem.algafood.domain.repository.PermissaoRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class PermissaoService {

	private PermissaoRepository repository;

	@Transactional
	public Permissao salvar(Permissao permissao) {
		permissaoExistente(permissao);
		return repository.save(permissao);
	}

	public Permissao atualizar(Permissao permissao) {
		return salvar(permissao);
	}
	
	@Transactional
	public void excluir(Long id) {
		try {
			repository.findById(id);
			repository.flush();
		} catch (EmptyResultDataAccessException e) {
			throw new EntidadeNaoEncontradaException("permissão", id);
		} catch (DataIntegrityViolationException e) {
			throw new RegistroEmUsoException("permissão", id);
		}
	}

	public List<Permissao> listarTodos() {
		return repository.findAll();
	}

	public Permissao buscarPorId(Long id) {
		Permissao permissao = repository.findById(id).orElseThrow(() -> new NegocioException(
				String.format("Não existe nenhum cadastro de permissão com esse código %d", id)));
		return permissao;
	}
	
	public void permissaoExistente(Permissao permissao) {
		boolean result = repository.findByNome(permissao.getNome()).stream().anyMatch(obj -> !obj.equals(permissao));
		if (result) {
			throw new NegocioException(
					String.format("Já existe um cadastro de permissão com nome %s!", permissao.getNome()));
		}
	}
}
