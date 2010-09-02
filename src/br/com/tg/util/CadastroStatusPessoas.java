package br.com.tg.util;

import java.util.List;

import br.com.tg.entidades.StatusPessoa;
import br.com.tg.exceptions.ErroAcessoRepositorioException;
import br.com.tg.exceptions.StatusPessoaInexistenteException;
import br.com.tg.repositorio.RepositorioStatusPessoa;

public class CadastroStatusPessoas {

	private RepositorioStatusPessoa statusPessoas;

	public CadastroStatusPessoas(RepositorioStatusPessoa rep) {

		this.statusPessoas = rep;
	}

	public void atualizar(StatusPessoa sp)
		throws StatusPessoaInexistenteException, ErroAcessoRepositorioException {
		statusPessoas.atualizar(sp);
	}

	public void cadastrar(StatusPessoa sp)
		throws ErroAcessoRepositorioException {
		statusPessoas.inserir(sp);
	}

	public void descadastrar(StatusPessoa sp)
		throws StatusPessoaInexistenteException, ErroAcessoRepositorioException {
		statusPessoas.remover(sp);
	}

	public StatusPessoa procurar(Integer idStatusPessoa)
		throws StatusPessoaInexistenteException, ErroAcessoRepositorioException {
		return statusPessoas.getStatusPessoa(idStatusPessoa);
	}

	public List<StatusPessoa> listar() throws ErroAcessoRepositorioException {
		return statusPessoas.listar();
	}

}
