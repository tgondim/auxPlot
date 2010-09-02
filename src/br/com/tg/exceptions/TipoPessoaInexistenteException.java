package br.com.tg.exceptions;

public class TipoPessoaInexistenteException extends Exception {
	 
	private static final long serialVersionUID = 1L;

	private static final String MSG_TIPO_PESSOA_INEXISTENTE = "Status de Pessoa não cadastrado.";
	 
	public TipoPessoaInexistenteException() {
		super(MSG_TIPO_PESSOA_INEXISTENTE);
	}
	 
}
