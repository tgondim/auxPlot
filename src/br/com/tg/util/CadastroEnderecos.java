package br.com.tg.util;

import br.com.tg.entidades.Endereco;
import br.com.tg.exceptions.EnderecoInexistenteException;
import br.com.tg.exceptions.ErroAcessoRepositorioException;
import br.com.tg.exceptions.PessoaInexistenteException;
import br.com.tg.repositorio.RepositorioEndereco;

public class CadastroEnderecos {
	/**
	 * Refer�ncia para a implementa��o do reposit�rio de pessoas.
	 */
	private RepositorioEndereco enderecos;

	/**
	 * O construtor da classe. Inicializa a refer�ncia para o reposit�rio
	 * de pessoas com o valor passado como par�metro.
	 *
	 * @param Pessoa a refer�ncia para o reposit�rio de pessoas.
	 */
	public CadastroEnderecos(RepositorioEndereco rep) {

		this.enderecos = rep;
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
	public void atualizar(Endereco e)
		throws EnderecoInexistenteException, ErroAcessoRepositorioException {
		enderecos.atualizar(e);
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
	public void cadastrar(Endereco e)
		throws ErroAcessoRepositorioException {
		enderecos.inserir(e);
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
	public void descadastrar(Endereco e)
		throws EnderecoInexistenteException, ErroAcessoRepositorioException {
		enderecos.remover(e);
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
	public Endereco procurar(Integer idEndereco)
		throws EnderecoInexistenteException, ErroAcessoRepositorioException {
		return enderecos.getEndereco(idEndereco);
	}

}
