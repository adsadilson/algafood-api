package br.com.apssystem.algafood.domain.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.apssystem.algafood.domain.model.Cidade;

@Repository
public interface CidadeRepository extends JpaRepository<Cidade, Long> {

	Cidade consultarCidadeEstado(@Param("nome") String cidade, @Param("sigla") String sigla);
}
