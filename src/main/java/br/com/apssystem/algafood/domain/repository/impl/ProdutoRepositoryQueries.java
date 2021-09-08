package br.com.apssystem.algafood.domain.repository.impl;

import br.com.apssystem.algafood.domain.model.FotoProduto;
import br.com.apssystem.algafood.domain.model.Produto;

import java.util.Optional;

public interface ProdutoRepositoryQueries {

    FotoProduto save(FotoProduto foto);

    void excluir(FotoProduto foto);


}
