package br.com.tg.repositorio;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.ObjectNotFoundException;
import org.hibernate.Session;

import br.com.tg.entidades.Pessoa;
import br.com.tg.entidades.Usuario;
import br.com.tg.util.HibernateUtil;

public class RepositorioPessoaDAO implements RepositorioPessoa {

	private Session session;
	
	@Override
	public void inserir(Pessoa pessoa) {
		session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.flush();
		session.clear();
		session.save(pessoa);
		session.getTransaction().commit();
	}

	@Override
	public void atualizar(Pessoa pessoa) {
		session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.flush();
		session.clear();
		session.merge(pessoa);
		session.getTransaction().commit();
	}
	
	@Override
	public Pessoa getPessoa(Integer idPessoa) throws ObjectNotFoundException {
		session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Pessoa pessoa = (Pessoa)session.load(Pessoa.class, idPessoa);
		session.getTransaction().commit();
		return pessoa;
	}
	
	@Override
	public List<Pessoa> listarPessoas(){
		session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Criteria c = session.createCriteria(Pessoa.class);
		List<Pessoa> listaPessoas = c.list();		
		return listaPessoas;
	}

	@Override
	public List<Usuario> listarUsuarios(){
		session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Criteria c = session.createCriteria(Usuario.class);
		List<Usuario> listaUsuarios = c.list();		
		return listaUsuarios;
	}
	
	@Override
	public void remover(Pessoa pessoa){
		session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Pessoa pessoaRemover = (Pessoa)session.load(Pessoa.class, pessoa.getId());
		session.delete(pessoaRemover);
		session.getTransaction().commit();
	}
	
}
