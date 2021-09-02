package br.com.apssystem.algafood.domain.repository;

import br.com.apssystem.algafood.domain.repository.impl.PedidoRespositoryQueries;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.apssystem.algafood.domain.model.Pedido;

import java.util.Optional;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long>, PedidoRespositoryQueries,
        JpaSpecificationExecutor<Pedido> {

    //@Query("from Pedido p join fetch p.cliente join fetch p.restaurante r join fetch r.cozinha")
    Page<Pedido> findAll(Pageable pageable);

    Optional<Object> findByCodigo(String codigo);
}
