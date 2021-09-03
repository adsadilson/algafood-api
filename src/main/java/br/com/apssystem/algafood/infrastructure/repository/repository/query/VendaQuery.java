package br.com.apssystem.algafood.infrastructure.repository.repository.query;

import br.com.apssystem.algafood.domain.model.dto.Venda;
import br.com.apssystem.algafood.domain.model.filter.VendaFilter;

import java.util.List;

public interface VendaQuery {

    List<Venda> consultarVendasDiarias(VendaFilter filtro);

    List<Venda> consultarVendasMensais(VendaFilter filtro);

    List<Venda> consultarVendasAnuais(VendaFilter filtro);


}
