package br.com.tg.exceptions;

public class PessoaInvalidaException extends Exception {
 
	private static final long serialVersionUID = 1L;

	private static final String MSG_PESSOA_INVALIDA = "Pessoa inválida.";
	 
	public PessoaInvalidaException() {
		super(MSG_PESSOA_INVALIDA);
	}
	 
}
 
