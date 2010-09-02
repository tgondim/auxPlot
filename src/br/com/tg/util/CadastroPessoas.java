package br.com.tg.util;

import java.util.List;

import br.com.tg.entidades.Pessoa;
import br.com.tg.entidades.Usuario;
import br.com.tg.exceptions.ErroAcessoRepositorioException;
import br.com.tg.exceptions.PessoaInexistenteException;
import br.com.tg.repositorio.RepositorioPessoa;

/**
 * Classe que realiza valida��es referentes �s opera��es de atualiza��o de dados
 * no mecanismo de armazenamento de dados de pessoas e usa o reposit�rio de pessoas
 * para aualizar e buscar os dados das mesmas.
 *
 * @author Tiago Gondim <a href="mailto:tgondim@gmail.com">tgondim@gmail.com</a>
 *
 * @version 1.0
 *
 * @see br.com.tg.entidades.Pessoa
 * @see br.com.tg.pessoas.ReposiorioPessoas
 */
public class CadastroPessoas {

	/**
	 * Refer�ncia para a implementa��o do reposit�rio de pessoas.
	 */
	private RepositorioPessoa pessoas;

	/**
	 * O construtor da classe. Inicializa a refer�ncia para o reposit�rio
	 * de pessoas com o valor passado como par�metro.
	 *
	 * @param Pessoa a refer�ncia para o reposit�rio de pessoas.
	 */
	public CadastroPessoas(RepositorioPessoa rep) {

		this.pessoas = rep;
	}

	/**
	 * Atualiza os dados de uma pessoa no reposit�rio de pessoas.
	 *
	 * @param p a pessoa com os dados a serem atualizados.
	 *
	 * @exception PessoaInexistenteException lan�ada quando a pessoa a ter seus dados
	 *            atualizados n�o existe no reposit�rio de pessoas.Esta exce��o vem da
	 *            chamada ao reposit�rio de pessoas e � repassada diretamente por este
	 *            m�todo ao seu m�todo chamador.
	 */
	public void atualizar(Pessoa p)
		throws PessoaInexistenteException, ErroAcessoRepositorioException {
		pessoas.atualizar(p);
	}

	/**
	 * Cadastra os dados de uma pessoa no reposit�rio de pessoas. Antes disso,
	 * checa se o CPF da pessoa a ser cadastrada j� existe no reposit�rio de pessoas.
	 *
	 * @param p a pessoa com os dados a serem cadastrados.
	 *
	 * @exception PessoaExistenteException se o CPF da pessoa a ser cadastrada j�
	 *            existir no reposit�rio de pessoas. Esta exce��o � instanciada e
	 *            lan�ada por este m�todo, caso a consulta ao CPF feita no reposit�rio
	 *            retorne true.
	 */
	public void cadastrar(Pessoa p)
		throws ErroAcessoRepositorioException {
		pessoas.inserir(p);
	}

	/**
	 * Exclui uma pessoa armazenado no reposit�rio de pessoas.
	 *
	 * @param cpf o CPF da pessoa que ser� exclu�da do reposit�rio de pessoas.
	 *
	 * @exception PessoaInexistenteException lan�ada quando a pessoa a ser exclu�da
	 *            n�o existe no reposit�rio de pessoas. Esta exce��o vem da chamada ao
	 *            reposit�rio de pessoas e � repassada diretamente por este m�todo
	 *            ao seu m�todo chamador.
	 */
	public void descadastrar(Pessoa p)
		throws PessoaInexistenteException, ErroAcessoRepositorioException {
		pessoas.remover(p);
	}

	/**
	 * Retorna uma pessoa armazenada no reposit�rio de pessoas.
	 *
	 * @param cpf o CPF da pessoa que ser� procurado no reposit�rio de pessoas.
	 *
	 * @return Pessoa a pessoa com seus dados lidos a partir do reposit�rio
	 *         de pessoas.
	 *
	 * @exception PessoaInexistenteException lan�ada quando a pessoa a ter seus dados
	 *            lidos n�o existe no reposit�rio de pessoas. Esta exce��o vem da chamada
	 *            ao reposit�rio de pessoas e � repassada diretamente por este m�todo ao
	 *            seu m�todo chamador.
	 */
	public Pessoa procurar(Integer idPessoa)
		throws PessoaInexistenteException, ErroAcessoRepositorioException {
		return pessoas.getPessoa(idPessoa);
	}

	public List<Pessoa> listarPessoas() throws ErroAcessoRepositorioException {
		return pessoas.listarPessoas();
	}

	public List<Usuario> listarUsuarios() throws ErroAcessoRepositorioException {
		return pessoas.listarUsuarios();
	}
	
}
