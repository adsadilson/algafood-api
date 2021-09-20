package br.com.apssystem.algafood.infrastructure.repository.specification;

import br.com.apssystem.algafood.domain.model.Pedido;
import br.com.apssystem.algafood.domain.model.filter.PedidoFilter;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;

public class PedidoSpecification {

    private static final String RESTAURANTE = "restaurante";

    public static Specification<Pedido> filter(PedidoFilter filtro) {
        return (root, query, builder) -> {
            if (Pedido.class.equals(query.getResultType())) {
                root.fetch("cliente").fetch("grupos");
                root.fetch(RESTAURANTE).fetch("cozinha").fetch("restaurantes");
                root.fetch(RESTAURANTE).fetch("formasPagtos");
                root.fetch(RESTAURANTE).fetch("endereco").fetch("cidade").fetch("estado");
            }
            var predicates = new ArrayList<Predicate>();

            if(filtro.getStatus() != null){
                predicates.add(builder.equal(root.get("status"),filtro.getStatus()));
            }

            if (filtro.getClienteId() != null) {
                predicates.add(builder.equal(root.get("cliente"), filtro.getClienteId()));
            }

            if (filtro.getRestauranteId() != null) {
                predicates.add(builder.equal(root.get("restaurante"), filtro.getRestauranteId()));
            }

            if (filtro.getDataCriacaoInicio() != null) {
                predicates.add(builder.greaterThanOrEqualTo(root.get("dataCriacao"),
                        filtro.getDataCriacaoInicio()));
            }

            if (filtro.getDataCriacaoFim() != null) {
                predicates.add(builder.lessThanOrEqualTo(root.get("dataCriacao"),
                        filtro.getDataCriacaoFim()));
            }

            return builder.and(predicates.toArray(new Predicate[0]));
        };
    }
}

