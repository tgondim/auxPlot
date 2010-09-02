package br.com.tg.repositorio;

import java.util.List;

import br.com.tg.entidades.Pessoa;
import br.com.tg.entidades.Usuario;

public interface RepositorioPessoa {
	
	public void inserir(Pessoa pessoa);

	public void atualizar(Pessoa pessoa);

	public Pessoa getPessoa(Integer idPessoa);

	public List<Pessoa> listarPessoas();

	public List<Usuario> listarUsuarios();
	
	public void remover(Pessoa pessoa);

}
