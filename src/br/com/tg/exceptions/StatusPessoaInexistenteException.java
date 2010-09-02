package br.com.tg.exceptions;

public class StatusPessoaInexistenteException extends Exception {
	 
	private static final long serialVersionUID = 1L;

	private static final String MSG_STATUS_PESSOA_INEXISTENTE = "Status de Pessoa não cadastrado.";
	 
	public StatusPessoaInexistenteException() {
		super(MSG_STATUS_PESSOA_INEXISTENTE);
	}
	 
}
