package br.com.tg.util;

import java.util.List;

import br.com.tg.entidades.Endereco;
import br.com.tg.entidades.Pessoa;
import br.com.tg.entidades.StatusPessoa;
import br.com.tg.entidades.Telefone;
import br.com.tg.entidades.TipoPessoa;
import br.com.tg.entidades.TipoTelefone;
import br.com.tg.entidades.TipoUsuario;
import br.com.tg.entidades.Usuario;
import br.com.tg.exceptions.EnderecoInexistenteException;
import br.com.tg.exceptions.ErroAcessoRepositorioException;
import br.com.tg.exceptions.PessoaInexistenteException;
import br.com.tg.exceptions.StatusPessoaInexistenteException;
import br.com.tg.exceptions.TelefoneInexistenteException;
import br.com.tg.exceptions.TipoPessoaInexistenteException;
import br.com.tg.exceptions.TipoTelefoneInexistenteException;
import br.com.tg.exceptions.TipoUsuarioInexistenteException;
import br.com.tg.repositorio.RepositorioPessoa;
import br.com.tg.repositorio.RepositorioPessoaDAO;
import br.com.tg.repositorio.RepositorioStatusPessoa;
import br.com.tg.repositorio.RepositorioStatusPessoaDAO;
import br.com.tg.repositorio.RepositorioTelefone;
import br.com.tg.repositorio.RepositorioTelefoneDAO;
import br.com.tg.repositorio.RepositorioTipoPessoa;
import br.com.tg.repositorio.RepositorioTipoPessoaDAO;
import br.com.tg.repositorio.RepositorioTipoTelefone;
import br.com.tg.repositorio.RepositorioTipoTelefoneDAO;
import br.com.tg.repositorio.RepositorioTipoUsuario;
import br.com.tg.repositorio.RepositorioTipoUsuarioDAO;

/**
 * Classe que representa a fachada do sistema. Interage com o meio externo para
 * atender ou encaminhar solicitações de processamento. Esta classe é um
 * Singleton, padrão de projeto que garante a existência de uma única instância
 * desta classe em um programa JAVA.
 * 
 * @author Tiago Gondim <a href="mailto:tgondim@gmail.com">tgondim@gmail.com</a>
 * 
 * @version 1.0
 * 
 * @see br.com.tg.pessoas.CadastroPessoas
 */
public class Fachada {

	/**
	 * Referência estática do Singleton. Guarda o endereço do objeto que
	 * representa a única instância desta classe em um programa JAVA.
	 */
	private static Fachada instancia;
	/**
	 * Referência para o cadastro de pessoas.
	 */
	private CadastroPessoas pessoas;
	private CadastroEnderecos enderecos;
	private CadastroStatusPessoas statusPessoas;
	private CadastroTipoPessoas tipoPessoas;
	private CadastroTipoUsuarios tipoUsuarios;
	private CadastroTipoTelefones tipoTelefones;
	private CadastroTelefones telefones;

	/**
	 * Construtor privado da classe. Ele é assim definido para que o padrão de
	 * implementação do Singleton possa garantir que uma única instância desta
	 * classe exista em um programa JAVA. Para que isto ocorra, uma das
	 * premissas é restringir a responsabilidade de criar objetos do tipo desta
	 * classe a ela própria. Isto se faz colocando o construtor com acesso
	 * privado. Este construtor chama o método que inicializa os cadastros de
	 * Pessoas.
	 */
	private Fachada() {
		initCadastros();
	}

	/**
	 * Inicializa os cadastros de pessoas, passando para estes as implementações
	 * dos repositórios de pessoas a serem usadas pelos cadastros inicializados.
	 * É interessante notar que os repositórios inicializados são interfaces,
	 * que recebem referências de implementações usando arrays em memória. Uma
	 * sofisticação maior do sistema permitirá a implementação de repositórios
	 * que utilizam acesso a bancos de dados SQL. Para mudar a forma de
	 * implementação dos repositórios, basta alterar a 1a e a 3a linhas deste
	 * método, inicializando os repositórios de pessoas com as implementações
	 * que usam acesso a bancos de dados SQL.
	 */
	private void initCadastros() {
		RepositorioPessoa repPessoa = new RepositorioPessoaDAO();
		pessoas = new CadastroPessoas(repPessoa);
		RepositorioStatusPessoa repStatusPessoa= new RepositorioStatusPessoaDAO();
		statusPessoas = new CadastroStatusPessoas(repStatusPessoa);
		RepositorioTipoPessoa repTipoPessoa = new RepositorioTipoPessoaDAO();
		tipoPessoas = new CadastroTipoPessoas(repTipoPessoa);
		RepositorioTipoUsuario repTipoUsuario = new RepositorioTipoUsuarioDAO();
		tipoUsuarios = new CadastroTipoUsuarios(repTipoUsuario);
		RepositorioTipoTelefone repTipoTelefone = new RepositorioTipoTelefoneDAO();
		tipoTelefones = new CadastroTipoTelefones(repTipoTelefone);
		RepositorioTelefone repTelefone = new RepositorioTelefoneDAO();
		telefones = new CadastroTelefones(repTelefone);
	}

	/**
	 * Método responsável por retornar a referência da única instância desta
	 * classe no programa JAVA e por criar esta única instância, caso ela não
	 * exista.
	 * 
	 * @return Fachada referência para a única instância desta classe.
	 */
	public static Fachada obterInstancia() {

		// Na primeira vez que este método for chamado, instancia == null
		// Assim, o teste retorna true, a instância é criada e sua referência
		// é atribuída ao atributo instancia. Nas próximas chamadas, o teste
		// retorna false e a referência criada na primeira chamada sempre é
		// retornada.
		if (instancia == null) {
			instancia = new Fachada();
		}
		return instancia;
	}

	/**
	 * Atualiza os dados de uma pessoa. A fachada, neste caso, delega esta
	 * responsabilidade ao cadastro de pessoas.
	 * 
	 * @param p
	 *            a pessoa com os dados a serem atualizados.
	 * 
	 * @exception PessoaInexistenteException
	 *                lançada quando a pessoa a ter seus dados atualizados não
	 *                existe no cadastro. Esta exceção vem da chamada ao
	 *                cadastro de pessoas e é repassada diretamente por este
	 *                método ao seu método chamador.
	 * @throws ErroAcessoRepositorioException
	 */
	public void atualizarPessoa(Pessoa p) throws PessoaInexistenteException, 
	ErroAcessoRepositorioException {
		pessoas.atualizar(p);
	}

	/**
	 * Busca uma pessoa do cadastro de pessoas. A fachada, neste caso, delega
	 * esta responsabilidade ao cadastro de pessoas.
	 * 
	 * @param cpf
	 *            o CPF da pessoa a ser buscada.
	 * 
	 * @return Pessoa a pessoa com os dados reucperados do cadastro.
	 * 
	 * @exception PessoaInexistenteException
	 *                lançada quando a pessoa a ser buscado não existe no
	 *                cadastro. Esta exceção vem da chamada ao cadastro de
	 *                pessoas e é repassada diretamente por este método ao seu
	 *                método chamador.
	 * @throws ErroAcessoRepositorioException
	 */
	public Pessoa procurarPessoa(Integer idPessoa) throws PessoaInexistenteException,
			ErroAcessoRepositorioException {
		return pessoas.procurar(idPessoa);
	}

	/**
	 * Cadastra os dados de uma pessoa. A fachada, neste caso, delega esta
	 * responsabilidade ao cadastro de pessoas.
	 * 
	 * @param p
	 *            a pessoa com os dados a serem cadastrados.
	 * 
	 * @exception PessoaExistenteException
	 *                lançada quando a pessoa a ter seus dados cadastrados já
	 *                existe no cadastro. Esta exceção vem da chamada ao
	 *                cadastro de pessoas e é repassada diretamente por este
	 *                método ao seu método chamador.
	 * @throws ErroAcessoRepositorioException
	 */
	public void cadastrarPessoa(Pessoa p) throws ErroAcessoRepositorioException {
		pessoas.cadastrar(p);
	}

	/**
	 * Exclui uma pessoa do cadastro de pessoas. A fachada, neste caso, delega
	 * esta responsabilidade ao cadastro de pessoas.
	 * 
	 * @param cpf
	 *            o CPF da pessoa a ser excluído.
	 * 
	 * @exception PessoaInexistenteException
	 *                lançada quando a pessoa a ser excluída não existe no
	 *                cadastro. Esta exceção vem da chamada ao cadastro de
	 *                pessoas e é repassada diretamente por este método ao seu
	 *                método chamador.
	 * @throws ErroAcessoRepositorioException
	 */
	public void descadastrarPessoa(Pessoa p)
			throws PessoaInexistenteException, ErroAcessoRepositorioException {
		pessoas.descadastrar(p);
	}

	public List<Pessoa> listarPessoas()
			throws ErroAcessoRepositorioException {
		return pessoas.listarPessoas();
	}

	public List<Usuario> listarUsuarios()
	throws ErroAcessoRepositorioException {
		return pessoas.listarUsuarios();
	}
	
	public void atualizarEndereco(Endereco e) throws EnderecoInexistenteException,
	ErroAcessoRepositorioException {
		enderecos.atualizar(e);
	}
	
	public Endereco procurarEndereco(Integer idEndereco) throws EnderecoInexistenteException,
	ErroAcessoRepositorioException {
		return enderecos.procurar(idEndereco);
	}

	public void cadastrarEndereco(Endereco e) throws ErroAcessoRepositorioException {
		enderecos.cadastrar(e);
	}
	
	public void descadastrarEndereco(Endereco e)
	throws EnderecoInexistenteException, ErroAcessoRepositorioException {
		enderecos.descadastrar(e);
	}
	
	public void atualizarStatusPessoa(StatusPessoa sp) throws StatusPessoaInexistenteException, 
	ErroAcessoRepositorioException {
		statusPessoas.atualizar(sp);
	}

	public StatusPessoa procurarStatusPessoa(Integer idStatusPessoa) throws StatusPessoaInexistenteException,
			ErroAcessoRepositorioException {
		return statusPessoas.procurar(idStatusPessoa);
	}

	public void cadastrarStatusPessoa(StatusPessoa sp) throws ErroAcessoRepositorioException {
		statusPessoas.cadastrar(sp);
	}

	public void descadastrarStatusPessoa(StatusPessoa sp)
			throws StatusPessoaInexistenteException, ErroAcessoRepositorioException {
		statusPessoas.descadastrar(sp);
	}

	public List<StatusPessoa> listarStatusPessoas()
			throws ErroAcessoRepositorioException {
		return statusPessoas.listar();
	}
	
	public void atualizarTipoPessoa(TipoPessoa tp) throws TipoPessoaInexistenteException, 
	ErroAcessoRepositorioException {
		tipoPessoas.atualizar(tp);
	}

	public TipoPessoa procurarTipoPessoa(Integer idTipoPessoa) throws TipoPessoaInexistenteException,
			ErroAcessoRepositorioException {
		return tipoPessoas.procurar(idTipoPessoa);
	}

	public void cadastrarTipoPessoa(TipoPessoa tp) throws ErroAcessoRepositorioException {
		tipoPessoas.cadastrar(tp);
	}

	public void descadastrarTipoPessoa(TipoPessoa tp)
			throws TipoPessoaInexistenteException, ErroAcessoRepositorioException {
		tipoPessoas.descadastrar(tp);
	}

	public List<TipoPessoa> listarTipoPessoas()
			throws ErroAcessoRepositorioException {
		return tipoPessoas.listar();
	}
	
	public void atualizarTipoUsuario(TipoUsuario tu) throws TipoUsuarioInexistenteException, 
	ErroAcessoRepositorioException {
		tipoUsuarios.atualizar(tu);
	}
	
	public TipoUsuario procurarTipoUsuario(Integer idTipoUsuario) throws TipoUsuarioInexistenteException,
	ErroAcessoRepositorioException {
		return tipoUsuarios.procurar(idTipoUsuario);
	}
	
	public void cadastrarTipoUsuario(TipoUsuario tu) throws ErroAcessoRepositorioException {
		tipoUsuarios.cadastrar(tu);
	}
	
	public void descadastrarTipoUsuario(TipoUsuario tu)
	throws TipoUsuarioInexistenteException, ErroAcessoRepositorioException {
		tipoUsuarios.descadastrar(tu);
	}
	
	public List<TipoUsuario> listarTiposUsuarios()
	throws ErroAcessoRepositorioException {
		return tipoUsuarios.listar();
	}
	
	public void atualizarTipoTelefone(TipoTelefone tt) throws TipoTelefoneInexistenteException, 
	ErroAcessoRepositorioException {
		tipoTelefones.atualizar(tt);
	}
	
	public TipoTelefone procurarTipoTelefone(Integer idTipoTelefone) throws TipoTelefoneInexistenteException,
	ErroAcessoRepositorioException {
		return tipoTelefones.procurar(idTipoTelefone);
	}
	
	public void cadastrarTipoTelefone(TipoTelefone tt) throws ErroAcessoRepositorioException {
		tipoTelefones.cadastrar(tt);
	}
	
	public void descadastrarTipoTelefone(TipoTelefone tt)
	throws TipoTelefoneInexistenteException, ErroAcessoRepositorioException {
		tipoTelefones.descadastrar(tt);
	}
	
	public List<TipoTelefone> listarTiposTelefones()
	throws ErroAcessoRepositorioException {
		return tipoTelefones.listar();
	}

	public void atualizarTelefone(Telefone tel) throws TelefoneInexistenteException, 
	ErroAcessoRepositorioException {
		telefones.atualizar(tel);
	}
	
	public Telefone procurarTelefone(Integer idTelefone) throws TelefoneInexistenteException,
	ErroAcessoRepositorioException {
		return telefones.procurar(idTelefone);
	}
	
	public void cadastrarTelefone(Telefone tel) throws ErroAcessoRepositorioException {
		telefones.cadastrar(tel);
	}
	
	public void descadastrarTelefone(Telefone tel)
	throws TelefoneInexistenteException, ErroAcessoRepositorioException {
		telefones.descadastrar(tel);
	}
	
	public List<Telefone> listarTelefone()
	throws ErroAcessoRepositorioException {
		return telefones.listar();
	}
	
}
