package br.com.tg.repositorio;

import br.com.tg.entidades.Endereco;

public interface RepositorioEndereco {
	
	public void inserir(Endereco endereco);

	public void atualizar(Endereco endereco);

	public Endereco getEndereco(Integer idEndereco);

	public void remover(Endereco endereco);
	
}
