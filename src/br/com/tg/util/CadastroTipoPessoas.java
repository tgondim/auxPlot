package br.com.tg.util;

import java.util.List;

import br.com.tg.entidades.TipoPessoa;
import br.com.tg.exceptions.ErroAcessoRepositorioException;
import br.com.tg.exceptions.TipoPessoaInexistenteException;
import br.com.tg.repositorio.RepositorioTipoPessoa;

public class CadastroTipoPessoas {

	private RepositorioTipoPessoa tipoPessoas;

	public CadastroTipoPessoas(RepositorioTipoPessoa rep) {

		this.tipoPessoas = rep;
	}

	public void atualizar(TipoPessoa tp)
		throws TipoPessoaInexistenteException, ErroAcessoRepositorioException {
		tipoPessoas.atualizar(tp);
	}

	public void cadastrar(TipoPessoa tp)
		throws ErroAcessoRepositorioException {
		tipoPessoas.inserir(tp);
	}

	public void descadastrar(TipoPessoa tp)
		throws TipoPessoaInexistenteException, ErroAcessoRepositorioException {
		tipoPessoas.remover(tp);
	}

	public TipoPessoa procurar(Integer idTipoPessoa)
		throws TipoPessoaInexistenteException, ErroAcessoRepositorioException {
		return tipoPessoas.getTipoPessoa(idTipoPessoa);
	}

	public List<TipoPessoa> listar() throws ErroAcessoRepositorioException {
		return tipoPessoas.listar();
	}
	
}
