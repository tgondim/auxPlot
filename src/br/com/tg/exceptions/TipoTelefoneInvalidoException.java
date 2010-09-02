package br.com.tg.exceptions;

public class TipoTelefoneInvalidoException extends Exception {
	 
	private static final long serialVersionUID = 1L;

	private static final String MSG_TIPO_TELEFONE_INVALIDO = "Tipo de Telefone inválido.";
	 
	public TipoTelefoneInvalidoException() {
		super(MSG_TIPO_TELEFONE_INVALIDO);
	}

}
