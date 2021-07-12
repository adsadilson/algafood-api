package br.com.apssystem.algafood.api.exception;

public class EntidadeNaoEncontradaException extends NegocioException {

	private static final long serialVersionUID = 1L;

	public EntidadeNaoEncontradaException(String mensagem) {
		super(mensagem);
	}

	public EntidadeNaoEncontradaException(String classe, Long id) {
		super(String.format("Não existe nenhum cadastro de %s com código %d", classe, id));
	}

}
