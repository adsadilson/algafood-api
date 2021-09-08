package br.com.apssystem.algafood.domain.repository;

import java.util.List;
import java.util.Optional;

import br.com.apssystem.algafood.domain.model.FotoProduto;
import br.com.apssystem.algafood.domain.model.Restaurante;
import br.com.apssystem.algafood.domain.repository.impl.ProdutoRepositoryQueries;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.apssystem.algafood.domain.model.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long>, ProdutoRepositoryQueries {

	List<Produto> findByNome(String nome);

	@Query("from Produto where restaurante.id = :restaurante and id = :produto")
	Optional<Produto> findById(@Param("restaurante") Long restauranteId,
							   @Param("produto") Long produtoId);

	@Query("select f from FotoProduto f join f.produto p "
			+ "where p.restaurante.id = :restauranteId and f.produto.id = :produtoId")
	Optional<FotoProduto> buscarFotoProdutoPorRestaurante(Long restauranteId, Long produtoId);


	List<Produto> findByRestaurante(Restaurante restaurante);
}
