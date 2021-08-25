package br.com.apssystem.algafood.domain.service;

import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.apssystem.algafood.api.exception.NegocioException;
import br.com.apssystem.algafood.api.exception.RegistroEmUsoException;
import br.com.apssystem.algafood.api.exception.EntidadeNaoEncontradaException;
import br.com.apssystem.algafood.domain.model.Produto;
import br.com.apssystem.algafood.domain.repository.ProdutoRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ProdutoService {

    private static final String NOMECLASSE = "Produto";
    private ProdutoRepository repository;

    public Produto salvar(Produto produto) {
        produtoExistente(produto);
        return repository.save(produto);
    }

    public Produto atualizar(Produto produto) {
        return repository.save(produto);
    }

    public void excluir(Long id) {
        try {
            repository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new EntidadeNaoEncontradaException(NOMECLASSE, id);
        } catch (DataIntegrityViolationException e) {
            throw new RegistroEmUsoException(NOMECLASSE, id);
        }
    }

    public Produto buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException(NOMECLASSE, id));
    }

    public List<Produto> listarTodos() {
        return repository.findAll();
    }

    public void produtoExistente(Produto produto) {
        boolean result = repository.findByNome(produto.getNome()).stream()
                .anyMatch(produtoExistente -> !produtoExistente.equals(produto));
        if (result) {
            throw new NegocioException("Já existe um produto cadastrado com esse nome " + produto.getNome());
        }
    }

    public Produto buscarPorIdAndRestaurante(Long restauranteId, Long produtoId) {
        return repository.findById(restauranteId, produtoId)
                .orElseThrow(() -> new EntidadeNaoEncontradaException(NOMECLASSE, produtoId));
    }
}
