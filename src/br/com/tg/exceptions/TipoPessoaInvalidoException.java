package br.com.tg.exceptions;

public class TipoPessoaInvalidoException extends Exception {
	 
	private static final long serialVersionUID = 1L;

	private static final String MSG_TIPO_PESSOA_INVALIDO = "Tipo de Pessoa inválido.";
	 
	public TipoPessoaInvalidoException() {
		super(MSG_TIPO_PESSOA_INVALIDO);
	}

}
