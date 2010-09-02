package br.com.tg.exceptions;

/**
 * Exceção caso a pessoa tratada não exista.
 */
public class PessoaInexistenteException extends Exception {
 
	private static final long serialVersionUID = 1L;

	private static final String MSG_PESSOA_INEXISTENTE = "Pessoa não cadastrada.";
	 
	public PessoaInexistenteException() {
		super(MSG_PESSOA_INEXISTENTE);
	}
	 
}
 
