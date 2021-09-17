package br.com.apssystem.algafood.api.controller;

import br.com.apssystem.algafood.domain.model.dto.Venda;
import br.com.apssystem.algafood.domain.model.filter.VendaFilter;
import br.com.apssystem.algafood.infrastructure.report.ReportService;
import br.com.apssystem.algafood.infrastructure.repository.repository.query.VendaQuery;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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

@Api(tags = "Estatisticas")
@RestController
@RequestMapping("/estatisticas")
@AllArgsConstructor
public class EstatisticasController {

    private VendaQuery vendaQuery;

    @Autowired
    private ReportService reportService;

    @ApiOperation("Consulta as vendas diarias")
    @GetMapping("/vendas-diarias")
    public List<Venda> consultarVendasDiarias(VendaFilter filtro) {
        return vendaQuery.consultarVendasDiarias(filtro);
    }

    @GetMapping(path = "/vendas-diarias", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<byte[]> consultarVendasDiariasPdf(VendaFilter filtro) {

        var list = vendaQuery.consultarVendasDiarias(filtro);
        var parametros = new HashMap<String, Object>();
        parametros.put("par_nome_relat", "Relatório de Vendas Diarias");
        byte[] bytesPdf = reportService.gerarRelatorio("vendas-diarias", parametros, list);

        var headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=vendas-diarias.pdf");
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_PDF)
                .headers(headers)
                .body(bytesPdf);
    }

    @ApiOperation("Consulta as vendas mensais")
    @GetMapping("/vendas-mensais")
    public List<Venda> consultarVendasMensais(VendaFilter filtro) {
        return vendaQuery.consultarVendasMensais(filtro);
    }

    @ApiOperation("Consulta as vendas anuais")
    @GetMapping("/vendas-anuais")
    public List<Venda> consultarVendasAnuais(VendaFilter filtro) {
        return vendaQuery.consultarVendasAnuais(filtro);
    }

    @ApiOperation("Gerar relatório de vendas diarias")
    @GetMapping("/pdf")
    public String generateReport(VendaFilter filtro) {
        var list = vendaQuery.consultarVendasDiarias(filtro);
        var parametros = new HashMap<String, Object>();
        parametros.put("par_nome_relat", "Relatório de Vendas Diarias");
        return reportService.exportarPDF("vendas-diarias", parametros, list);
    }
}
