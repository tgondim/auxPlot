package br.com.tg.exceptions;

/**
 * Exceção lançada quando ocorre um erro de acesso ao mecanismo de armazenamento de
 * dados nas implementações dos repositórios. 
 *
 * @author Tiago Gondim <a href="mailto:tgondim@gmail.com">tgondim@gmail.com</a>
 *
 * @version 1.0 
 */
public class ErroAcessoRepositorioException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * A exceção interna ocorrida dentro da implementação do repositório.
	 */
	private Exception excecaoInterna;

	/**
	 * O código numérico de erro associado à exceção ocorrida dentro da implementação 
	 * do repositório. 
	 */
	private int codigoErro;

	/**
	 * O construtor da classe. Passa a mensagem de erro à super-classe, inicializa a 
	 * exceção interna associada e o código interno da exceção associada. 
	 * 
	 * @param pMensagem a mensagem de erro associada à exceção.
	 * @param pExcecaoInterna a exceção interna ocorrida na implementação do repositório.
	 * @param pCodigoErro o código de erro da exceção interna ocorrida na implementação 
	 *        do repositório.
	 */
	public ErroAcessoRepositorioException(
		String pMensagem,
		Exception pExcecaoInterna,
		int pCodigoErro) {
		super(pMensagem);
		excecaoInterna = pExcecaoInterna;
		codigoErro = pCodigoErro;
	}
	/**
	 * Retorna o código numérico de erro associado à exceção ocorrida dentro da implementação 
	 * do repositório.
	 *
	 * @return int o código numérico de erro associado à exceção ocorrida dentro da 
	 *         implementação do repositório.
	 */
	public int getCodigoErro() {
		return codigoErro;
	}
	/**
	 * Retorna a exceção interna ocorrida dentro da implementação do repositório.
	 *
	 * @return Exception a exceção interna ocorrida dentro da implementação do repositório.
	 */
	public Exception getExcecaoInterna() {
		return excecaoInterna;
	}
}