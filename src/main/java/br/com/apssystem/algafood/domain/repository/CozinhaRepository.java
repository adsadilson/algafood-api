package br.com.apssystem.algafood.domain.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.apssystem.algafood.domain.model.Cozinha;

@Repository
public interface CozinhaRepository extends JpaRepository<Cozinha, Long> {

    Optional<Cozinha> findByNome(String nome);

    @Query(value = "from Cozinha c join fetch c.restaurantes r where c.nome like :nome",
            countQuery = "select count(c.id) from Cozinha c where c.nome like :nome")
    Page<Cozinha> findByNomeContaining(Pageable pageable, @Param("nome") String nome);

    long count();

}
