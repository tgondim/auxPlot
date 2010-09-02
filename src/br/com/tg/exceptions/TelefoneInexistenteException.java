package br.com.tg.exceptions;

public class TelefoneInexistenteException extends Exception {
	
	private static final long serialVersionUID = 1L;

	private static final String MSG_TELEFONE_INEXISTENTE = "Telefone n�o cadastrado.";
	 
	public TelefoneInexistenteException() {
		super(MSG_TELEFONE_INEXISTENTE);
	}
	
}
