package br.com.tg.exceptions;

public class EnderecoInexistenteException extends Exception {
	
	private static final long serialVersionUID = 1L;

	private static final String MSG_ENDERECO_INEXISTENTE = "Endereco n�o cadastrado.";
	 
	public EnderecoInexistenteException() {
		super(MSG_ENDERECO_INEXISTENTE);
	}
	
}
