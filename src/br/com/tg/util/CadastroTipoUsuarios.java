package br.com.tg.util;

import java.util.List;

import br.com.tg.entidades.TipoUsuario;
import br.com.tg.exceptions.ErroAcessoRepositorioException;
import br.com.tg.exceptions.TipoUsuarioInexistenteException;
import br.com.tg.repositorio.RepositorioTipoUsuario;

public class CadastroTipoUsuarios {

	private RepositorioTipoUsuario tipoUsuarios;

	public CadastroTipoUsuarios(RepositorioTipoUsuario rep) {

		this.tipoUsuarios = rep;
	}

	public void atualizar(TipoUsuario tu)
		throws TipoUsuarioInexistenteException, ErroAcessoRepositorioException {
		tipoUsuarios.atualizar(tu);
	}

	public void cadastrar(TipoUsuario tu)
		throws ErroAcessoRepositorioException {
		tipoUsuarios.inserir(tu);
	}

	public void descadastrar(TipoUsuario tu)
		throws TipoUsuarioInexistenteException, ErroAcessoRepositorioException {
		tipoUsuarios.remover(tu);
	}

	public TipoUsuario procurar(Integer idTipoUsuario)
		throws TipoUsuarioInexistenteException, ErroAcessoRepositorioException {
		return tipoUsuarios.getTipoUsuario(idTipoUsuario);
	}

	public List<TipoUsuario> listar() throws ErroAcessoRepositorioException {
		return tipoUsuarios.listar();
	}
	
}
