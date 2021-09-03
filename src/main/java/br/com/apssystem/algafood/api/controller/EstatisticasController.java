package br.com.apssystem.algafood.api.controller;

import br.com.apssystem.algafood.domain.model.dto.Venda;
import br.com.apssystem.algafood.domain.model.filter.VendaFilter;
import br.com.apssystem.algafood.infrastructure.repository.repository.query.VendaQuery;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/estatisticas")
@AllArgsConstructor
public class EstatisticasController {

    @Autowired
    private VendaQuery vendaQuery;

    @GetMapping("/vendas-diarias")
    public List<Venda> consultarVendasDiarias(VendaFilter filtro){
        return vendaQuery.consultarVendasDiarias(filtro);
    }

    @GetMapping("/vendas-mensais")
    public List<Venda> consultarVendasMensais(VendaFilter filtro){
        return vendaQuery.consultarVendasMensais(filtro);
    }

    @GetMapping("/vendas-anuais")
    public List<Venda> consultarVendasAnuais(VendaFilter filtro){
        return vendaQuery.consultarVendasAnuais(filtro);
    }
}
