package br.com.apssystem.algafood.api.exception;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.TypeMismatchException;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.fasterxml.jackson.databind.JsonMappingException.Reference;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.fasterxml.jackson.databind.exc.PropertyBindingException;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@ControllerAdvice
public class ExcepitonHandler extends ResponseEntityExceptionHandler {

	private MessageSource messageSource;

	private String joinPath(List<Reference> references) {
		return references.stream().map(Reference::getFieldName).collect(Collectors.joining("."));
	}

	@Override
	protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		if (body == null) {
			body = Problem.builder().status(status.value()).detail(ex.getMessage()).timestamp(LocalDateTime.now())
					.build();
		} else if (body instanceof String) {
			body = Problem.builder().status(status.value()).detail(ex.getMessage()).timestamp(LocalDateTime.now())
					.build();
		}
		return super.handleExceptionInternal(ex, body, headers, status, request);
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		return handleValidationInternal(ex, ex.getBindingResult(), headers, status, request);
	}

	@Override
	protected ResponseEntity<Object> handleBindException(BindException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		return handleValidationInternal(ex, ex.getBindingResult(), headers, status, request);
	}

	private ResponseEntity<Object> handleValidationInternal(Exception ex, BindingResult bindingResult,
															HttpHeaders headers, HttpStatus status, WebRequest request) {

		ProblemType problemType = ProblemType.DADOS_INVALIDOS;

		List<Problem.Field> listFields = new ArrayList<>();

		for (ObjectError objErro : bindingResult.getAllErrors()) {
			String nome = ((FieldError) objErro).getField();
			listFields.add(new Problem.Field(nome, messageSource.getMessage(objErro, LocaleContextHolder.getLocale())));
		}

		Problem problem = Problem.builder().status(status.value()).title(problemType.getTitle())
				.type(problemType.getUri())
				.detail("Um ou mais campos estão inválidos. Faça o preenchimento correto e tente novamente.")
				.timestamp(LocalDateTime.now()).fields(listFields).build();

		return handleExceptionInternal(ex, problem, headers, status, request);
	}

	@ExceptionHandler({ ValidacaoException.class })
	public ResponseEntity<Object> handleValidacaoException(ValidacaoException ex, WebRequest request) {
		return handleValidationInternal(ex, ex.getBindingResult(), new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
	}

	@ExceptionHandler(NegocioException.class)
	public ResponseEntity<Object> NegocioExcepitonHandler(NegocioException ex, WebRequest request) {

		HttpStatus status = HttpStatus.BAD_REQUEST;

		ProblemType problemType = ProblemType.ERRO_NEGOCIO;

		Problem problem = Problem.builder().title(problemType.getTitle()).type(problemType.getUri())
				.status(status.value()).detail(ex.getMessage()).timestamp(LocalDateTime.now()).build();

		return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
	}

	@ExceptionHandler(EntidadeNaoEncontradaException.class)
	public ResponseEntity<Object> handlerNegocioExcepitonHandler(EntidadeNaoEncontradaException ex,
			WebRequest request) {

		HttpStatus status = HttpStatus.BAD_REQUEST;

		ProblemType problemType = ProblemType.ERRO_NEGOCIO;

		Problem problem = Problem.builder().title(problemType.getTitle()).type(problemType.getUri())
				.status(status.value()).detail(ex.getMessage()).timestamp(LocalDateTime.now()).build();

		return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
	}

	@ExceptionHandler(RegistroEmUsoException.class)
	public ResponseEntity<Object> handlerEntidadeEmUso(RegistroEmUsoException ex, WebRequest request) {

		HttpStatus status = HttpStatus.CONFLICT;

		ProblemType problemType = ProblemType.ENTIDADE_EM_USO;

		Problem problem = Problem.builder().title(problemType.getTitle()).type(problemType.getUri())
				.status(status.value()).detail(ex.getMessage()).timestamp(LocalDateTime.now()).build();

		return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
	}

	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		Throwable cause = ex.getCause();

		if (cause instanceof InvalidFormatException) {
			return handleInvalidFormatException((InvalidFormatException) cause, headers, status, request);
		} else if (cause instanceof PropertyBindingException) {
			return handlePropertyBindingException((PropertyBindingException) cause, headers, status, request);
		}

		String msn = "O corpo da requisição está inválido. Verifique erro de sintaxe.";

		ProblemType problemType = ProblemType.DADOS_INVALIDOS;

		Problem problem = Problem.builder().title(problemType.getTitle()).type(problemType.getUri())
				.status(status.value()).detail(msn).timestamp(LocalDateTime.now()).build();

		return handleExceptionInternal(ex, problem, headers, HttpStatus.BAD_REQUEST, request);
	}

	private ResponseEntity<Object> handlePropertyBindingException(PropertyBindingException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {

		ProblemType problemType = ProblemType.MENSAGEM_INCOMPREENSIVEL;

		String path = joinPath(ex.getPath());

		String msn = String
				.format("A propriedade '%s' não existe. Corrija ou remova essa propriedade e tente novamente.", path);

		Problem problem = Problem.builder().title(problemType.getTitle()).type(problemType.getUri())
				.status(status.value()).detail(msn).timestamp(LocalDateTime.now()).build();

		return handleExceptionInternal(ex, problem, headers, status, request);
	}

	private ResponseEntity<Object> handleInvalidFormatException(InvalidFormatException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {

		ProblemType problemType = ProblemType.MENSAGEM_INCOMPREENSIVEL;

		String path = joinPath(ex.getPath());

		String msn = String.format(
				"A propriedade '%S' recebeu o valor '%s', "
						+ "que é de um tipo inválido. Corrija e informe um valor compatível com o tipo %s.",
				path, ex.getValue(), ex.getTargetType().getSimpleName());

		Problem problem = Problem.builder().title(problemType.getTitle()).type(problemType.getUri())
				.status(status.value()).detail(msn).timestamp(LocalDateTime.now()).build();

		return handleExceptionInternal(ex, problem, headers, status, request);
	}

	@Override
	protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {

		if (ex instanceof MethodArgumentTypeMismatchException) {
			return handleMethodArgumentTypeMismatch((MethodArgumentTypeMismatchException) ex, headers, status, request);
		}

		return super.handleTypeMismatch(ex, headers, status, request);
	}

	private ResponseEntity<Object> handleMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		ProblemType problemType = ProblemType.PARAMETRO_INVALIDO;

		String msn = String.format(
				"O parâmetro de URL '%s' recebeu o valor '%s', "
						+ "que é de um tipo inválido. Corrija e informe um valor compatível com o tipo %s.",
				ex.getName(), ex.getValue(), ex.getRequiredType().getSimpleName());

		Problem problem = Problem.builder().status(status.value()).title(problemType.getTitle())
				.type(problemType.getUri()).detail(msn).build();

		return handleExceptionInternal(ex, problem, headers, status, request);
	}

	@Override
	protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {

		ProblemType problemType = ProblemType.RECURSO_NAO_ENCONTRADO;

		String msn = String.format("O recurso %s, que você tentou acessar, é inexistente.", ex.getRequestURL());

		Problem problem = Problem.builder().status(status.value()).title(problemType.getTitle())
				.type(problemType.getUri()).detail(msn).timestamp(LocalDateTime.now()).build();

		return handleExceptionInternal(ex, problem, headers, status, request);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<Object> handleUncaught(Exception ex, WebRequest request) {
		HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
		// ProblemType problemType = ProblemType.ERRO_DE_SISTEMA;
		String msn = "Ocorreu um erro interno inesperado no sistema. "
				+ "Tente novamente e se o problema persistir, entre em contato " + "com o administrador do sistema.";

		// Importante colocar o printStackTrace (pelo menos por enquanto, que não
		// estamos
		// fazendo logging) para mostrar a stacktrace no console
		// Se não fizer isso, você não vai ver a stacktrace de exceptions que seriam
		// importantes
		// para você durante, especialmente na fase de desenvolvimento
		ex.printStackTrace();

		ProblemType problemType = ProblemType.ERRO_DE_SISTEMA;

		Problem problem = Problem.builder().status(status.value()).title(problemType.getTitle())
				.type(problemType.getUri()).detail(msn).timestamp(LocalDateTime.now()).build();

		return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
	}

}
