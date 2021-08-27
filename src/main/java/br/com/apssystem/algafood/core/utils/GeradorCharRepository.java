package br.com.apssystem.algafood.core.utils;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

@Repository
public class GeradorCharRepository  {

	@PersistenceContext
	EntityManager entityManager;

	public Boolean gerarChar(Class clazz, String value) {
		try {
			long valor = (long) entityManager
					.createQuery("select count(*) from " + clazz.getName() + " where codigo=:value")
					.setParameter("value", value).getSingleResult();
			return valor > 0 ? true : false;
		} catch (NoResultException e) {
			return null;
		}
	}

}
