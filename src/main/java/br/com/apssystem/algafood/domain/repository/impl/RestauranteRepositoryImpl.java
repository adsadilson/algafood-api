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
		var jpql = "from Restaurante r join fetch r.cozinha join fetch r.formasPagtos join fetch r.endereco e " +
				"join fetch e.cidade a join fetch a.estado";
		return manager.createQuery(jpql, Restaurante.class)
				.getResultList();
	}

	
}
