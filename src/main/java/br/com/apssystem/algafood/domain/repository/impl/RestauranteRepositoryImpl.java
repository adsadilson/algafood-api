package br.com.apssystem.algafood.domain.repository.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import br.com.apssystem.algafood.domain.model.Restaurante;

@Repository
public class RestauranteRepositoryImpl implements RestauranteRepositoryQueries {
	
	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public List<Restaurante> listarTodosRestaurantes() {
		var jpql = "select distinct r from Restaurante r join fetch r.cozinha c join fetch c.restaurantes join fetch r.formasPagtos " +
				"left join fetch r.endereco.cidade c left join fetch c.estado order by r.id";
		return manager.createQuery(jpql, Restaurante.class)
				.getResultList();
	}

	
}
