package br.com.apssystem.algafood.infrastructure.report;

import br.com.apssystem.algafood.api.exception.NegocioException;
import br.com.apssystem.algafood.domain.model.Empresa;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.Collection;
import java.util.Locale;
import java.util.Map;

@Service
public class Relatorio implements Serializable {

    private static final long serialVersionUID = 1L;

    private final Empresa empresa;

    public Relatorio() {
        empresa = Empresa.builder()
                .nome("Apssystem Solucoes Customizadas")
                .build();
    }

    public byte[] gerarRelatorio(String nomeArquivo, Map<String, Object> parametros,
                                 Collection<?> objects) {
        try {
            /*parametros.put("emp_nome", empresa.getNome());
            parametros.put("emp_end1", empresa.getEndereco().getLogradouro() + ", " + empresa.getEndereco().getNumero());
            parametros.put("emp_end2", empresa.getEndereco().getBairro() + " - " + empresa.getEndereco().getCidade() + " -" +
                    " " + empresa.getEndereco().getCidade().getEstado()
                    + "    CEP: " + empresa.getEndereco().getCep());
            parametros.put("emp_end3", "CNPJ: " + empresa.getCpfCnpj() + "     Fone: " + empresa.getCelular());*/

           // System.out.println(this.getClass().getResource("/relatorios/vendas-diarias.jasper"));

            var inputStream = this.getClass().getResourceAsStream(
                    "/relatorios/"+nomeArquivo+".jasper");

            if(null == inputStream){
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


}
