package br.com.tg.util;

import java.util.List;

import br.com.tg.entidades.Pessoa;
import br.com.tg.entidades.Usuario;
import br.com.tg.exceptions.ErroAcessoRepositorioException;
import br.com.tg.exceptions.PessoaInexistenteException;
import br.com.tg.repositorio.RepositorioPessoa;

/**
 * Classe que realiza validações referentes às operações de atualização de dados
 * no mecanismo de armazenamento de dados de pessoas e usa o repositório de pessoas
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
	 * Referência para a implementação do repositório de pessoas.
	 */
	private RepositorioPessoa pessoas;

	/**
	 * O construtor da classe. Inicializa a referência para o repositório
	 * de pessoas com o valor passado como parâmetro.
	 *
	 * @param Pessoa a referência para o repositório de pessoas.
	 */
	public CadastroPessoas(RepositorioPessoa rep) {

		this.pessoas = rep;
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
	public void atualizar(Pessoa p)
		throws PessoaInexistenteException, ErroAcessoRepositorioException {
		pessoas.atualizar(p);
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
	public void cadastrar(Pessoa p)
		throws ErroAcessoRepositorioException {
		pessoas.inserir(p);
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
	public void descadastrar(Pessoa p)
		throws PessoaInexistenteException, ErroAcessoRepositorioException {
		pessoas.remover(p);
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
