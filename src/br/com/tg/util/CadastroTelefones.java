package br.com.tg.util;

import java.util.List;

import br.com.tg.entidades.Telefone;
import br.com.tg.exceptions.ErroAcessoRepositorioException;
import br.com.tg.exceptions.TelefoneInexistenteException;
import br.com.tg.repositorio.RepositorioTelefone;

public class CadastroTelefones {

	private RepositorioTelefone telefones;

	public CadastroTelefones(RepositorioTelefone rep) {

		this.telefones = rep;
	}

	public void atualizar(Telefone tel)
		throws TelefoneInexistenteException, ErroAcessoRepositorioException {
		telefones.atualizar(tel);
	}

	public void cadastrar(Telefone tel)
		throws ErroAcessoRepositorioException {
		telefones.inserir(tel);
	}

	public void descadastrar(Telefone tel)
		throws TelefoneInexistenteException, ErroAcessoRepositorioException {
		telefones.remover(tel);
	}

	public Telefone procurar(Integer idTelefone)
		throws TelefoneInexistenteException, ErroAcessoRepositorioException {
		return telefones.getTelefone(idTelefone);
	}

	public List<Telefone> listar() throws ErroAcessoRepositorioException {
		return telefones.listar();
	}
	
}
