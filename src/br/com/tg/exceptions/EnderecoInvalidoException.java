package br.com.tg.exceptions;

public class EnderecoInvalidoException extends Exception {
	
	private static final long serialVersionUID = 1L;

	private static final String MSG_ENDERECO_INVALIDO = "Endere�o inv�lido.";
	 
	public EnderecoInvalidoException() {
		super(MSG_ENDERECO_INVALIDO);
	}
	
}
