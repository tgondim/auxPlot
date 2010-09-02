package br.com.tg.repositorio;

import java.util.List;

import br.com.tg.entidades.TipoPessoa;

public interface RepositorioTipoPessoa {
	
	public void inserir(TipoPessoa tipoPessoa);

	public void atualizar(TipoPessoa tipoPessoa);

	public TipoPessoa getTipoPessoa(Integer idTipoPessoa);

	public List<TipoPessoa> listar();
	
	public void remover(TipoPessoa tipoPessoa);

}
