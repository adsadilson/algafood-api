package br.com.apssystem.algafood.domain.repository.impl;

import br.com.apssystem.algafood.domain.model.Pedido;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class PedidoRepositoryImpl implements PedidoRespositoryQueries{

    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<Pedido> listarTodosPedidos() {
        String jpql = "from Pedido p join fetch p.cliente cli join fetch cli.grupos join fetch p.restaurante r " +
                "join fetch r.cozinha c join fetch c.restaurantes join fetch r.endereco a join fetch a.cidade u " +
                "join fetch u.estado join fetch r.formasPagtos ";
        return manager.createQuery(jpql, Pedido.class).getResultList();
    }
}
