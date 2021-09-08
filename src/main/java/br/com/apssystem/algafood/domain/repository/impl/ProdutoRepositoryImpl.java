package br.com.apssystem.algafood.domain.repository.impl;

import br.com.apssystem.algafood.domain.model.FotoProduto;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Optional;

@Repository
public class ProdutoRepositoryImpl implements  ProdutoRepositoryQueries{

    @PersistenceContext
    EntityManager manager;

    @Transactional
    @Override
    public FotoProduto save(FotoProduto foto) {
        return manager.merge(foto);
    }

    @Override
    public void excluir(FotoProduto foto) {
        manager.remove(foto);
    }

}
