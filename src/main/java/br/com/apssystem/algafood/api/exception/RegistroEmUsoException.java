package br.com.apssystem.algafood.api.exception;

public class RegistroEmUsoException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public RegistroEmUsoException(String mensagem) {
		super(mensagem);
	}

	public RegistroEmUsoException(String classe, Long id) {
		super(String.format("%s de código %d não pode ser excluido pois possui vinculos com outras tabelas", classe, id));
	}
}
