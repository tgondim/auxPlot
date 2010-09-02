package br.com.tg.util;

import java.util.List;

import br.com.tg.entidades.TipoTelefone;
import br.com.tg.exceptions.ErroAcessoRepositorioException;
import br.com.tg.exceptions.TipoTelefoneInexistenteException;
import br.com.tg.repositorio.RepositorioTipoTelefone;

public class CadastroTipoTelefones {
	
	private RepositorioTipoTelefone tipoTelefones;

	public CadastroTipoTelefones(RepositorioTipoTelefone rep) {

		this.tipoTelefones = rep;
	}

	public void atualizar(TipoTelefone tt)
		throws TipoTelefoneInexistenteException, ErroAcessoRepositorioException {
		tipoTelefones.atualizar(tt);
	}

	public void cadastrar(TipoTelefone tt)
		throws ErroAcessoRepositorioException {
		tipoTelefones.inserir(tt);
	}

	public void descadastrar(TipoTelefone tt)
		throws TipoTelefoneInexistenteException, ErroAcessoRepositorioException {
		tipoTelefones.remover(tt);
	}

	public TipoTelefone procurar(Integer idTipoTelefone)
		throws TipoTelefoneInexistenteException, ErroAcessoRepositorioException {
		return tipoTelefones.getTipoTelefone(idTipoTelefone);
	}

	public List<TipoTelefone> listar() throws ErroAcessoRepositorioException {
		return tipoTelefones.listar();
	}

}
