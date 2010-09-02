package br.com.tg.repositorio;

import java.util.List;

import br.com.tg.entidades.StatusPessoa;

public interface RepositorioStatusPessoa {
	
	public void inserir(StatusPessoa statusPessoa);

	public void atualizar(StatusPessoa statusPessoa);

	public StatusPessoa getStatusPessoa(Integer idStatusPessoa);

	public List<StatusPessoa> listar();
	
	public void remover(StatusPessoa statusPessoa);

}
