package br.com.apssystem.algafood.domain.repository;

import br.com.apssystem.algafood.domain.repository.impl.PedidoRespositoryQueries;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.apssystem.algafood.domain.model.Pedido;

import java.util.List;
import java.util.Optional;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long>, PedidoRespositoryQueries,
        JpaSpecificationExecutor<Pedido> {

    List<Pedido> findAll();

    Optional<Object> findByCodigo(String codigo);
}
