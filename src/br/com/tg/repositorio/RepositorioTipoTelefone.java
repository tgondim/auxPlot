package br.com.tg.repositorio;

import java.util.List;

import br.com.tg.entidades.TipoTelefone;

public interface RepositorioTipoTelefone {

	public void inserir(TipoTelefone tipoTelefone);

	public void atualizar(TipoTelefone tipoTelefone);

	public TipoTelefone getTipoTelefone(Integer idTipoTelefone);

	public List<TipoTelefone> listar();
	
	public void remover(TipoTelefone tipoTelefone);


}
