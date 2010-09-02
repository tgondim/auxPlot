package br.com.tg.exceptions;

public class StatusPessoaInvalidoException extends Exception {
	 
	private static final long serialVersionUID = 1L;

	private static final String MSG_STATUS_PESSOA_INVALIDO = "Status de Pessoa inválido.";
	 
	public StatusPessoaInvalidoException() {
		super(MSG_STATUS_PESSOA_INVALIDO);
	}

}
