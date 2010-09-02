package br.com.tg.exceptions;

/**
 * Exce��o caso a pessoa tratada n�o exista.
 */
public class PessoaInexistenteException extends Exception {
 
	private static final long serialVersionUID = 1L;

	private static final String MSG_PESSOA_INEXISTENTE = "Pessoa n�o cadastrada.";
	 
	public PessoaInexistenteException() {
		super(MSG_PESSOA_INEXISTENTE);
	}
	 
}
 
