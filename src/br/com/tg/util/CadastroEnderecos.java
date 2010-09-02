package br.com.tg.util;

import br.com.tg.entidades.Endereco;
import br.com.tg.exceptions.EnderecoInexistenteException;
import br.com.tg.exceptions.ErroAcessoRepositorioException;
import br.com.tg.exceptions.PessoaInexistenteException;
import br.com.tg.repositorio.RepositorioEndereco;

public class CadastroEnderecos {
	/**
	 * Referência para a implementação do repositório de pessoas.
	 */
	private RepositorioEndereco enderecos;

	/**
	 * O construtor da classe. Inicializa a referência para o repositório
	 * de pessoas com o valor passado como parâmetro.
	 *
	 * @param Pessoa a referência para o repositório de pessoas.
	 */
	public CadastroEnderecos(RepositorioEndereco rep) {

		this.enderecos = rep;
	}

	/**
	 * Atualiza os dados de uma pessoa no repositório de pessoas.
	 *
	 * @param p a pessoa com os dados a serem atualizados.
	 *
	 * @exception PessoaInexistenteException lançada quando a pessoa a ter seus dados
	 *            atualizados não existe no repositório de pessoas.Esta exceção vem da
	 *            chamada ao repositório de pessoas e é repassada diretamente por este
	 *            método ao seu método chamador.
	 */
	public void atualizar(Endereco e)
		throws EnderecoInexistenteException, ErroAcessoRepositorioException {
		enderecos.atualizar(e);
	}

	/**
	 * Cadastra os dados de uma pessoa no repositório de pessoas. Antes disso,
	 * checa se o CPF da pessoa a ser cadastrada já existe no repositório de pessoas.
	 *
	 * @param p a pessoa com os dados a serem cadastrados.
	 *
	 * @exception PessoaExistenteException se o CPF da pessoa a ser cadastrada já
	 *            existir no repositório de pessoas. Esta exceção é instanciada e
	 *            lançada por este método, caso a consulta ao CPF feita no repositório
	 *            retorne true.
	 */
	public void cadastrar(Endereco e)
		throws ErroAcessoRepositorioException {
		enderecos.inserir(e);
	}

	/**
	 * Exclui uma pessoa armazenado no repositório de pessoas.
	 *
	 * @param cpf o CPF da pessoa que será excluída do repositório de pessoas.
	 *
	 * @exception PessoaInexistenteException lançada quando a pessoa a ser excluída
	 *            não existe no repositório de pessoas. Esta exceção vem da chamada ao
	 *            repositório de pessoas e é repassada diretamente por este método
	 *            ao seu método chamador.
	 */
	public void descadastrar(Endereco e)
		throws EnderecoInexistenteException, ErroAcessoRepositorioException {
		enderecos.remover(e);
	}

	/**
	 * Retorna uma pessoa armazenada no repositório de pessoas.
	 *
	 * @param cpf o CPF da pessoa que será procurado no repositório de pessoas.
	 *
	 * @return Pessoa a pessoa com seus dados lidos a partir do repositório
	 *         de pessoas.
	 *
	 * @exception PessoaInexistenteException lançada quando a pessoa a ter seus dados
	 *            lidos não existe no repositório de pessoas. Esta exceção vem da chamada
	 *            ao repositório de pessoas e é repassada diretamente por este método ao
	 *            seu método chamador.
	 */
	public Endereco procurar(Integer idEndereco)
		throws EnderecoInexistenteException, ErroAcessoRepositorioException {
		return enderecos.getEndereco(idEndereco);
	}

}
