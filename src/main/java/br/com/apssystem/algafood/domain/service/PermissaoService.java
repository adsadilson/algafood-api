package br.com.apssystem.algafood.domain.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import br.com.apssystem.algafood.api.exception.NegocioException;
import br.com.apssystem.algafood.domain.model.Permissao;
import br.com.apssystem.algafood.domain.repository.PermissaoRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class PermissaoService {

	private PermissaoRepository permissaoRepository;

	public Permissao salvar(Permissao permissao) {
		return permissaoRepository.save(permissao);
	}

	public Permissao atualizar(Permissao permissao, Long id) {
		Permissao permissaoSalvo = buscarPorId(id);
		BeanUtils.copyProperties(permissao, permissaoSalvo, "id");
		return permissaoRepository.save(permissaoSalvo);
	}
	
	public void excluir(Long id) {
		buscarPorId(id);
		permissaoRepository.deleteById(id);
	}

	public List<Permissao> listarTodos() {
		return permissaoRepository.findAll();
	}

	public Permissao buscarPorId(Long id) {
		Permissao permissao = permissaoRepository.findById(id).orElseThrow(() -> new NegocioException(
				String.format("Não existe nenhum cadastro de permissão com esse código %d", id)));
		return permissao;
	}
}
