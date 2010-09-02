package br.com.tg.repositorio;

import java.util.List;

import br.com.tg.entidades.Telefone;

public interface RepositorioTelefone {

	public void inserir(Telefone telefone);

	public void atualizar(Telefone telefone);

	public Telefone getTelefone(Integer idTelefone);

	public List<Telefone> listar();
	
	public void remover(Telefone telefone);


}
