package br.com.tg.exceptions;

public class TelefoneInvalidoException extends Exception {
	 
	private static final long serialVersionUID = 1L;

	private static final String MSG_TELEFONE_INVALIDO = "Telefone inválido.";
	 
	public TelefoneInvalidoException() {
		super(MSG_TELEFONE_INVALIDO);
	}


}
