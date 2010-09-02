package br.com.tg.exceptions;

public class TipoUsuarioInexistenteException extends Exception {
	
	private static final long serialVersionUID = 1L;

	private static final String MSG_TIPO_USUARIO_INEXISTENTE = "Tipo de Usu�rio n�o cadastrado.";
	 
	public TipoUsuarioInexistenteException() {
		super(MSG_TIPO_USUARIO_INEXISTENTE);
	}
	
}
