package br.com.apssystem.algafood.domain.repository;

import java.util.List;
import java.util.Optional;

import br.com.apssystem.algafood.domain.model.Restaurante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.apssystem.algafood.domain.model.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {

	List<Produto> findByNome(String nome);

	@Query("from Produto where restaurante.id = :restaurante and id = :produto")
	Optional<Produto> findById(@Param("restaurante") Long restauranteId,
							   @Param("produto") Long produtoId);

	List<Produto> findByRestaurante(Restaurante restaurante);
}
