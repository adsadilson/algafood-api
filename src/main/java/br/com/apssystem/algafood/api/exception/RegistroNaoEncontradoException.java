package br.com.apssystem.algafood.api.exception;

public class RegistroNaoEncontradoException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public RegistroNaoEncontradoException(String mensagem) {
		super(mensagem);
	}

	public RegistroNaoEncontradoException(String classe, Long id) {
		super(String.format("Não existe nenhum cadastro de %s com código %d", classe, id));
	}

}
