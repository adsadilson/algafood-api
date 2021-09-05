package br.com.apssystem.algafood.infrastructure.report;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.Collection;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.apssystem.algafood.domain.service.RestauranteService;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Service
public class RelatorioTest implements Serializable {

	private static final long serialVersionUID = 1L;

	@Autowired
	private HttpServletResponse response;

	@Autowired
	private RestauranteService restauranteService;

	public void gerarRelatorio(String nomeArquivo, Map<String, Object> parametros,
			Collection<?> objects) {

		try {

			var empresa = restauranteService.buscarPorId(1L);

			// seta a configuraçao padrao no formato brasil
			parametros.put(JRParameter.REPORT_LOCALE, new Locale("pt", "BR"));

			parametros.put("emp_nome", empresa.getNome());
			parametros.put("emp_end1",
					empresa.getEndereco().getLogradouro() + ", " + empresa.getEndereco().getNumero());
			parametros.put("emp_end2",
					empresa.getEndereco().getBairro() + " - " + empresa.getEndereco().getCidade().getNome() + " -" + " "
							+ empresa.getEndereco().getCidade().getEstado().getSigla() + "    CEP: "
							+ empresa.getEndereco().getCep());
			parametros.put("emp_end3", "CNPJ: " + empresa.getCnpj() + "     Fone: " + empresa.getTelefone());

			// copilar o arquivo recebido
			var inputStream = this.getClass().getResourceAsStream(
					"/relatorios/"+nomeArquivo+".jasper");
			JasperReport report = JasperCompileManager.compileReport(inputStream);

			// preencher o relatorio com os dados
			JasperPrint print = JasperFillManager.fillReport(report, parametros,
					new JRBeanCollectionDataSource(objects));

			// exporta o relatorio em pdf para ByteArrayOutputStream()
			JasperExportManager.exportReportToPdf(print);

			response.reset();
			// seta o conteudo que vai no response
			response.setContentType("application/pdf");
			// seta o tamanho do arquivo
			// Código abaixo gerar o relatório e disponibiliza diretamente na página ou
			// "attachment para fazer o download
			response.setHeader("Content-Disposition", "inline; filename=" + nomeArquivo + ".pdf");
			// escrever os dados do baos
			response.getOutputStream().flush();
			response.getOutputStream().close();

		} catch (JRException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
