package br.com.apssystem.algafood.domain.repository;

import java.time.OffsetDateTime;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.apssystem.algafood.domain.model.FormaPagto;

@Repository
public interface FormaPagtoRespository extends JpaRepository<FormaPagto, Long> {

	Optional<FormaPagto> findByDescricao(String nome);

    @Query("select max(dataAtualizacao) from FormaPagto")
    OffsetDateTime getDataUltimaAtualizacao();
}
