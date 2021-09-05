package br.com.apssystem.algafood.infrastructure.report;

import br.com.apssystem.algafood.api.exception.NegocioException;
import br.com.apssystem.algafood.domain.model.Restaurante;
import br.com.apssystem.algafood.domain.service.RestauranteService;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Locale;
import java.util.Map;

@Service
public class ReportService {

    @Autowired
    RestauranteService restauranteService;

    public String exportarPDF(String nomeArquivo, Map<String, Object> parametros,
                              Collection<?> list) {
        String path = "C:\\Users\\supor\\Desktop";
        try {
            var empresa = restauranteService.buscarPorId(1L);

            infoEmpresa(parametros, empresa);

            var inputStream = this.getClass().getResourceAsStream("/relatorios/" + nomeArquivo + ".jasper");
            var dataSource = new JRBeanCollectionDataSource(list);
            var jasperPrint = JasperFillManager.fillReport(inputStream, parametros, dataSource);
            JasperExportManager.exportReportToPdfFile(jasperPrint, path + "/" + nomeArquivo + ".pdf");
        } catch (JRException e) {
            e.printStackTrace();
        }
        return path;
    }


    public byte[] gerarRelatorio(String nomeArquivo, Map<String, Object> parametros,
                                 Collection<?> objects) {
        try {
            var empresa = restauranteService.buscarPorId(1L);
            infoEmpresa(parametros, empresa);

            var inputStream = this.getClass().getResourceAsStream(
                    "/relatorios/" + nomeArquivo + ".jasper");

            if (null == inputStream) {
                throw new NegocioException("Não foi possivel carregar o arquivo pdf");
            }

            parametros.put(JRParameter.REPORT_LOCALE, new Locale("pt", "BR"));
            var dataSource = new JRBeanCollectionDataSource(objects);
            var jasperPrint = JasperFillManager.fillReport(inputStream, parametros, dataSource);
            return JasperExportManager.exportReportToPdf(jasperPrint);
        } catch (Exception e) {
            throw new ReportException("Não foi possível emitir relatório de vendas diárias", e);
        }
    }

    private void infoEmpresa(Map<String, Object> parametros, Restaurante empresa) {
        parametros.put("emp_nome", empresa.getNome());
        parametros.put("emp_end1", empresa.getEndereco().getLogradouro() + ", " + empresa.getEndereco().getNumero());
        parametros.put("emp_end2",
                empresa.getEndereco().getBairro() + " - " + empresa.getEndereco().getCidade().getNome() + " -" +
                        " " + empresa.getEndereco().getCidade().getEstado().getSigla()
                        + "    CEP: " + empresa.getEndereco().getCep());
        parametros.put("emp_end3", "CNPJ: " + empresa.getCnpj() + "     Fone: " + empresa.getTelefone());
    }
}
