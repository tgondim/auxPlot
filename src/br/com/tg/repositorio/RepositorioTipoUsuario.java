package br.com.tg.repositorio;

import java.util.List;

import br.com.tg.entidades.TipoUsuario;

public interface RepositorioTipoUsuario {

	public void inserir(TipoUsuario tipoUsuario);

	public void atualizar(TipoUsuario tipoUsuario);

	public TipoUsuario getTipoUsuario(Integer idTipoUsuario);

	public List<TipoUsuario> listar();
	
	public void remover(TipoUsuario tipoUsuario);

}
