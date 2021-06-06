package br.com.apssystem.algafood.domain.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import br.com.apssystem.algafood.api.exception.NegocioException;
import br.com.apssystem.algafood.domain.model.Cidade;
import br.com.apssystem.algafood.domain.model.Estado;
import br.com.apssystem.algafood.domain.repository.CidadeRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CidadeService {

	private CidadeRepository cidadeRepository;

	private EstadoService estadoService;

	public Cidade salvar(Cidade cidade) {
		Estado estado = estadoService.buscarPorId(cidade.getEstado().getId());
		cidade.setEstado(estado);
		cidadeExiste(cidade.getNome(), estado.getSigla());
		return cidadeRepository.save(cidade);
	}

	public Cidade atualizar(Cidade cidade, Long id) {
		Cidade cidadeSalva = buscarPorId(id);
		BeanUtils.copyProperties(cidade, cidadeSalva, "id");
		return salvar(cidadeSalva);
	}

	public void excluir(Long id) {
		buscarPorId(id);
		cidadeRepository.deleteById(id);
	}

	public Cidade buscarPorId(Long id) {
		Cidade cidade = cidadeRepository.findById(id).orElseThrow(() -> new NegocioException(
				String.format("Não existe nenhum cadastro de cidade com esse código %d", id)));
		return cidade;
	}

	public List<Cidade> listarTodos() {
		return cidadeRepository.findAll();
	}

	public boolean cidadeExiste(String cid, String uf) {
		Cidade cidade = cidadeRepository.consultarCidadeEstado(cid, uf);
		if (null != cidade) {
			throw new NegocioException(
					String.format("Já existe um cadastro de cidade com nome %s para o estado %s ", cid, uf));
		}
		return false;
	}
}
