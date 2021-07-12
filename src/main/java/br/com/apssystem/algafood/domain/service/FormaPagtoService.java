package br.com.apssystem.algafood.domain.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.apssystem.algafood.api.exception.NegocioException;
import br.com.apssystem.algafood.api.exception.RegistroEmUsoException;
import br.com.apssystem.algafood.api.exception.EntidadeNaoEncontradaException;
import br.com.apssystem.algafood.domain.model.FormaPagto;
import br.com.apssystem.algafood.domain.repository.FormaPagtoRespository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class FormaPagtoService {

	private FormaPagtoRespository repository;

	public FormaPagto salvar(FormaPagto formaPagto) {
		return repository.save(formaPagto);
	}

	public FormaPagto atualizar(FormaPagto formaPagto, Long id) {
		FormaPagto formaPagtoSalvo = buscarPorId(id);
		BeanUtils.copyProperties(formaPagto, formaPagtoSalvo, "id");
		return salvar(formaPagtoSalvo);
	}

	public void excluir(Long id) {
		try {
			repository.deleteById(id);
			repository.flush();
		} catch (EmptyResultDataAccessException e) {
			throw new EntidadeNaoEncontradaException("Forma de Pagto", id);
		} catch (DataIntegrityViolationException e) {
			throw new RegistroEmUsoException("Forma de Pagto", id);
		}
	}

	public List<FormaPagto> listarTodos() {
		return repository.findAll();
	}

	public FormaPagto buscarPorId(Long id) {
		FormaPagto formaPagto = repository.findById(id).orElseThrow(() -> new NegocioException(
				String.format("Não existe nenhum cadastro de Forma de Pagto com esse código %d", id)));
		return formaPagto;
	}

	public void formaPagtoExistente(FormaPagto formaPagto) {
		boolean result = repository.findByDescricao(formaPagto.getDescricao()).stream()
				.anyMatch(fPagtoExistente -> !fPagtoExistente.equals(formaPagto));
		if (result) {
			throw new NegocioException(
					String.format("Já existe um cadastro de Forma Pagto com nome %s!", formaPagto.getDescricao()));
		}
	}
}
