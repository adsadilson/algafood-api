package br.com.apssystem.algafood.api.exception;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@ControllerAdvice
public class ExcepitonHandler extends ResponseEntityExceptionHandler {

	private MessageSource messageSource;

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		List<Campo> listCampos = new ArrayList<>();
		for (ObjectError objErro : ex.getBindingResult().getAllErrors()) {
			String nome = ((FieldError) objErro).getField();
			listCampos.add(new Campo(nome, messageSource.getMessage(objErro, LocaleContextHolder.getLocale())));
		}

		Erro error = new Erro();
		error.setStatus(status.value());
		error.setTitulo("Um ou mais campos estão inválidos. Faça o preenchimento correto e tente novamente.");
		error.setDataHora(LocalDateTime.now());
		error.setCampos(listCampos);
		return handleExceptionInternal(ex, error, headers, status, request);
	}

	@Getter
	@Setter
	@NoArgsConstructor
	public static class Erro {
		private Integer status;
		private String titulo;
		private LocalDateTime dataHora;
		private List<Campo> campos;

		public Erro(Integer status, String titulo, LocalDateTime dataHora) {
			this.status = status;
			this.titulo = titulo;
			this.dataHora = dataHora;
		}

	}

	@Getter
	@AllArgsConstructor
	public static class Campo {
		private String nome;
		private String mensagem;
	}

	@ExceptionHandler(NegocioException.class)
	public ResponseEntity<Object> NegocioExcepitonHandler(NegocioException ex, WebRequest request) {
		HttpStatus status = HttpStatus.BAD_REQUEST;
		Erro error = new Erro(status.value(), ex.getMessage(), LocalDateTime.now());
		return handleExceptionInternal(ex, error, new HttpHeaders(), status, request);
	}

	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		Erro error = new Erro(status.value(), ex.getMessage(), LocalDateTime.now());
		return handleExceptionInternal(ex, error, headers, HttpStatus.BAD_REQUEST, request);
	}

}
