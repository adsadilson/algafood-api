package br.com.apssystem.algafood.domain.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import br.com.apssystem.algafood.api.exception.NegocioException;
import br.com.apssystem.algafood.domain.model.Produto;
import br.com.apssystem.algafood.domain.repository.ProdutoRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ProdutoService {

	private ProdutoRepository produtoRepository;

	public Produto salvar(Produto produto) {
		produtoExistente(produto);
		return produtoRepository.save(produto);
	}

	public Produto atualizar(Produto produto, Long id) {
		Produto produtoSalvo = buscarPorId(id);
		BeanUtils.copyProperties(produto, produtoSalvo, "id");
		return produtoRepository.save(produtoSalvo);
	}

	public void excluir(Long id) {
		buscarPorId(id);
		produtoRepository.deleteById(id);
	}

	public Produto buscarPorId(Long id) {
		Produto produto = produtoRepository.findById(id).orElseThrow(() -> new NegocioException(
				String.format("Não existe nenhum cadastro de Produto com esse nome %d", id)));
		return produto;
	}

	public List<Produto> listarTodos() {
		return produtoRepository.findAll();
	}

	public void produtoExistente(Produto produto) {
		boolean result = produtoRepository.findByNome(produto.getNome()).stream()
				.anyMatch(produtoExistente -> !produtoExistente.equals(produto));
		if (result) {
			throw new NegocioException("Já existe um produto cadastrado com esse nome " + produto.getNome());
		}
	}
}
