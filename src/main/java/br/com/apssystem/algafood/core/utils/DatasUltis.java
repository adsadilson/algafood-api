package br.com.apssystem.algafood.core.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

public class DatasUltis {

	public static final String DATAFORMAT = "dd/MM/yyyy";

	public static final String DATATIMEFORMAT = "dd/MM/yyyy HH:mm";
	public static final String DATATIMESECONDFORMAT = "dd/MM/yyyy HH:mm:ss";
	private static DateFormat FORMATOPADRAO;
	private static final SimpleDateFormat SIMPLEDATEFORMAT;
	public static final String TIME = "HH:mm";
	public static final String TIMESECOND = "HH:mm:ss";

	static {
		FORMATOPADRAO = new SimpleDateFormat(DATAFORMAT);
		SIMPLEDATEFORMAT = new SimpleDateFormat();
		SIMPLEDATEFORMAT.setLenient(false);
	}

	@SuppressWarnings("static-access")
	public static Date primeiroDiaMes() {
		// instancia um Calendar com a data atual.
		Calendar cal = Calendar.getInstance();
		// Já que eu já tenho a data atual e quero pegar o menor dia, vou
		// manipular apenas o dia da instância gerada
		// cal.getActualMaximun(Calendar.DAY_OF_MONTH);.
		cal.set(cal.DAY_OF_MONTH, cal.getActualMinimum(Calendar.DAY_OF_MONTH));
		// Imprime a data formatada para dd/mm/aaaa
		// System.out.println(new SimpleDateFormat("dd/MM/yyyy").format(cal.getTime()));
		return cal.getTime();
	}

	@SuppressWarnings("static-access")
	public static Date ultimoDiaMes() {
		// instancia um Calendar com a data atual.
		Calendar cal = Calendar.getInstance();
		// Já que eu já tenho a data atual e quero pegar o maior dia, vou
		// manipular apenas o dia da instância gerada
		// cal.getActualMaximun(Calendar.DAY_OF_MONTH);.
		cal.set(cal.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
		// Imprime a data formatada para dd/mm/aaaa
		// System.out.println(new SimpleDateFormat("dd/MM/yyyy").format(cal.getTime()));
		return cal.getTime();
	}

	@SuppressWarnings("static-access")
	public static Date primeiroDiaMes(Date data) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(data);
		cal.set(cal.DAY_OF_MONTH, cal.getActualMinimum(Calendar.DAY_OF_MONTH));
		return cal.getTime();
	}

	@SuppressWarnings("static-access")
	public static Date ultimoDiaMes(Date data) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(data);
		cal.set(cal.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
		return cal.getTime();
	}

	public static boolean validateBeginEndDate(Date dataInicio, Date dataFim) {
		boolean result = false;
		if (!dataFim.after(dataInicio)) {
			result = true;
		}
		return result;
	}

	public static int idade(final LocalDate aniversario) {
		final LocalDate dataAtual = LocalDate.now();
		final Period periodo = Period.between(aniversario, dataAtual);
		return periodo.getYears();
	}

	// http://www.botecodigital.info/java/a-api-de-data-do-java-8/
	public static int idade(final Date aniversario) {
		final LocalDate dataAtual = LocalDate.now();
		LocalDate aniv = LocalDateTime.ofInstant(aniversario.toInstant(), ZoneOffset.UTC).toLocalDate();
		final Period periodo = Period.between(aniv, dataAtual);
		return periodo.getYears();
	}

	public static void DiferencaEntreDatasEmMesesEDias() {
		LocalDate inicio = LocalDate.of(2011, 7, 28);
		LocalDate fim = LocalDate.of(2014, 3, 18);
		Period periodo = Period.between(inicio, fim);

		int anos = periodo.getYears();
		int meses = periodo.getMonths();
		int dias = periodo.getDays();

		System.out.println("A diferença de " + anos + " anos, " + meses + " meses e " + dias + " dias.");

		long intervalo = ChronoUnit.MONTHS.between(inicio, fim);
		System.out.println("A diferença de " + intervalo + " meses.");
		long intervalo1 = ChronoUnit.DAYS.between(inicio, fim);
		System.out.println("A diferença de " + intervalo1 + " dias.");
	}

	public static boolean isNull(Date data) {
		return data == null;
	}

	public static boolean isNotNull(Date data) {
		return data != null;
	}

	public static Date adicionarMes(Date date, Integer mes) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, mes);
		return calendar.getTime();
	}

	public static Date subtrairMes(Date date, Integer mes) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, -mes);
		return calendar.getTime();
	}

	public static Date alterarDiaData(Calendar calendar, Integer dia) {
		calendar.set(Calendar.DAY_OF_MONTH, dia);
		return calendar.getTime();
	}

	public static String converterDataString(Date data, String pattern) {
		if (data == null) {
			return null;
		}
		SIMPLEDATEFORMAT.applyPattern(pattern);
		try {
			return SIMPLEDATEFORMAT.format(data);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static Date converterStringData(String dataStr, String pattern) {
		if (dataStr == null || dataStr.length() == 0) {
			return null;
		}
		SIMPLEDATEFORMAT.applyPattern(pattern);
		try {
			return SIMPLEDATEFORMAT.parse(dataStr);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static boolean dataAnterior(Date primeira, Date segunda) {
		if (dataIgual(primeira, segunda)) {
			return false;
		}
		return primeira.before(segunda);
	}

	public static boolean dataPosterior(Date primeira, Date segunda) {
		if (dataIgual(primeira, segunda)) {
			return false;
		}
		return primeira.after(segunda);
	}

	public static Date getData(String date) {
		Date dt = null;
		try {
			dt = (Date) FORMATOPADRAO.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return dt;
	}

	@SuppressWarnings("deprecation")
	public static Date getDataAtual() {
		Date dt = new Date();
		dt.setHours(0);
		dt.setMinutes(0);
		dt.setSeconds(0);
		return dt;
	}

	public static String getDataAtualString() {
		SIMPLEDATEFORMAT.applyPattern(DATAFORMAT);
		try {
			return SIMPLEDATEFORMAT.format(new Date());
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static String getDataHoraAtualString() {
		SIMPLEDATEFORMAT.applyPattern(DATATIMEFORMAT);
		try {
			return SIMPLEDATEFORMAT.format(new Date());
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static String getDataAtualCompletaString() {
		SIMPLEDATEFORMAT.applyPattern(DATATIMESECONDFORMAT);
		try {
			return SIMPLEDATEFORMAT.format(new Date());
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static String getDataPadraoString(Date data) {
		return FORMATOPADRAO.format(data);
	}

	public static Long getTimeMillis() {
		return System.currentTimeMillis();
	}

	public static Date subtrairDias(Date date, Integer dias) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_MONTH, -dias);
		return calendar.getTime();
	}

	public static Date subtrairDatas(Date dateInicial, Date dateFinal) {

		Calendar result = Calendar.getInstance();

		Long dif = dateFinal.getTime() - dateInicial.getTime();

		result.set(0, 0, 0, 0, 0, 0);
		result.set(Calendar.HOUR_OF_DAY, (int) (dif / (60 * 60 * 1000)));
		result.set(Calendar.MINUTE, (int) (dif / (60 * 1000) % 60));
		result.set(Calendar.SECOND, (int) (dif / 1000 % 60));

		return result.getTime();
	}

	public static Integer getAno() {
		Calendar calendar = Calendar.getInstance();
		return calendar.get(Calendar.YEAR);
	}

	public static Integer getMes() {
		Calendar calendar = Calendar.getInstance();
		return calendar.get(Calendar.MONTH);
	}

	public static Integer getDia() {
		Calendar calendar = Calendar.getInstance();
		return calendar.get(Calendar.DAY_OF_MONTH);
	}

	public static Integer getDiaDaSemana(Calendar calendar) {
		return calendar.get(Calendar.DAY_OF_WEEK);
	}

	public static Integer getDiaDaSemana(Date data) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(data);
		return calendar.get(Calendar.DAY_OF_WEEK);
	}

	public static Integer getAno(Date data) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(data);
		return calendar.get(Calendar.YEAR);
	}

	public static Integer getMes(Date data) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(data);
		return calendar.get(Calendar.MONTH);
	}

	public static Date adicionarHora(Date data, Integer hora, Integer minuto, Integer segundo) {
		data.setHours(hora);
		data.setMinutes(minuto);
		data.setSeconds(segundo);
		return data;
	}

	public static Date criarData(Integer dia, Integer mes, Integer ano) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(ano, mes - 1, dia);
		calendar.set(Calendar.HOUR, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		return calendar.getTime();
	}

	public static Date adicionarHora(Date data) {
		Calendar date = Calendar.getInstance();
		date.setTime(data);

		return date.getTime();
	}

	public static Date adicionarPrimeiraHoraDia(Date data) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(data);
		calendar.set(Calendar.HOUR, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		return calendar.getTime();
	}

	public static Date adicionarUltimaHoraDia(Date data) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(data);
		calendar.set(Calendar.HOUR, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		return calendar.getTime();
	}

	public static boolean dataIgual(Date primeira, Date segunda) {
		if (getDataPadraoString(primeira).equals(getDataPadraoString(segunda))) {
			return true;
		}
		return false;
	}

	public static int getHora() {
		return Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
	}

	public static Date adicionarDias(Date date, int dias) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_MONTH, dias);
		return calendar.getTime();
	}

	public static long diferencaEntreDias(Date dt1, Date dt2) {
		Instant instant = Instant.ofEpochMilli(dt1.getTime());
		LocalDate localDate = LocalDateTime.ofInstant(instant, ZoneId.systemDefault()).toLocalDate();

		Instant instant2 = Instant.ofEpochMilli(dt2.getTime());
		LocalDate localDate2 = LocalDateTime.ofInstant(instant2, ZoneId.systemDefault()).toLocalDate();

		long dias = ChronoUnit.DAYS.between(localDate, localDate2);
		return dias;
	}

	public static String getDataHoraString(Date data) {

		if (data == null) {
			return null;
		}
		SIMPLEDATEFORMAT.applyPattern(DATATIMEFORMAT);
		try {
			return SIMPLEDATEFORMAT.format(data);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static String NomeDoMes(int i, int tipo) {
		String mes[] = { "JANEIRO", "FEVEREIRO", "MARÇO", "ABRIL", "MAIO", "JUNHO", "JULHO", "AGOSTO", "SETEMBRO",
				"OUTUBRO", "NOVEMBRO", "DEZEMBRO" };
		// Java é uma linguagem com vetores zero-based: as posições do vetor
		// iniciam a numeração a partir do valor 0 (0-janeiro, 1-fevereiro, ...)
		if (tipo == 0)
			return (mes[i - 1]); // extenso
		// o método "substring" retorna os 3 primeiros caracteres de "mes[i-1]"
		else
			return (mes[i - 1].substring(0, 3)); // abreviado
	}

	public static String nomeMes(Date dt) {
		Locale local = new Locale("pt", "BR");
		DateFormat dateFormat = new SimpleDateFormat(" MMMM 'de' yyyy", local);
		return dateFormat.format(dt);
	}

	// Retorna o dia da semana.
	// Parâmetros: "i" = índice para o vetor "diasem"
//	             "tipo" = 0 para retornar o nome completo e
//	                      1 para o nome abreviado do dia da semana.
	public static String DiaDaSemana(int i, int tipo) {
		String diasem[] = { "domingo", "segunda-feira", "terça-feira", "quarta-feira", "quinta-feira", "sexta-feira",
				"sábado" };
		if (tipo == 0)
			return (diasem[i - 1]); // extenso
		// o método "substring" retorna os 3 primeiros caracteres de "diasem[i]"
		else
			return (diasem[i - 1].substring(0, 3));
	}

	// Retorna a data por extenso.
	// Parâmetros: "cidade" = nome da cidade; e, "dt" = data.
	public static String DataPorExtenso(String cidade, Date dt) {
		// retorna os valores ano, mês e dia da variável "dt"
		int d = dt.getDate();
		int m = dt.getMonth() + 1;
		int a = dt.getYear() + 1900;

		// retorna o dia da semana: 1=domingo, 2=segunda-feira, ..., 7=sábado
		Calendar data = new GregorianCalendar(a, m - 1, d);
		int ds = data.get(Calendar.DAY_OF_WEEK);

		return (cidade + ", " + d + " de " + NomeDoMes(m, 0) + " de " + a + " (" + DiaDaSemana(ds, 1) + ").");
	}

	public static String DiaDaSemana(Date data) {
		DateFormat dd = new SimpleDateFormat("EEEE");
		return dd.format(data);
	}

}
