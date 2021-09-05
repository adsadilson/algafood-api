package br.com.apssystem.algafood.api.controller;

import br.com.apssystem.algafood.domain.model.dto.Venda;
import br.com.apssystem.algafood.domain.model.filter.VendaFilter;
import br.com.apssystem.algafood.infrastructure.report.Relatorio;
import br.com.apssystem.algafood.infrastructure.repository.repository.query.VendaQuery;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/estatisticas")
@AllArgsConstructor
public class EstatisticasController {

    private VendaQuery vendaQuery;
    private Relatorio relatorio;


    @GetMapping("/vendas-diarias")
    public List<Venda> consultarVendasDiarias(VendaFilter filtro) {
        return vendaQuery.consultarVendasDiarias(filtro);
    }

    @GetMapping(path = "/vendas-diarias", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<byte[]> consultarVendasDiariasPdf(VendaFilter filtro) {

        var list = vendaQuery.consultarVendasDiarias(filtro);
        var parametros = new HashMap<String,Object>();
        byte[] bytesPdf = relatorio.gerarRelatorio("vendas-diarias",parametros, list);

        var headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=vendas-diarias.pdf");
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_PDF)
                .headers(headers)
                .body(bytesPdf);
    }

    @GetMapping("/vendas-mensais")
    public List<Venda> consultarVendasMensais(VendaFilter filtro) {
        return vendaQuery.consultarVendasMensais(filtro);
    }

    @GetMapping("/vendas-anuais")
    public List<Venda> consultarVendasAnuais(VendaFilter filtro) {
        return vendaQuery.consultarVendasAnuais(filtro);
    }
}
