package br.com.tg.repositorio;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;

import br.com.tg.entidades.StatusPessoa;
import br.com.tg.util.HibernateUtil;

public class RepositorioStatusPessoaDAO implements RepositorioStatusPessoa {

	private Session session;
	
	@Override
	public void atualizar(StatusPessoa statusPessoa) {
		session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.flush();
		session.clear();
		session.merge(statusPessoa);
		session.getTransaction().commit();
	}

	@Override
	public StatusPessoa getStatusPessoa(Integer idStatusPessoa) {
		session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		StatusPessoa statusPessoa = (StatusPessoa)session.load(StatusPessoa.class, idStatusPessoa);
		session.getTransaction().commit();
		return statusPessoa;	
	}

	@Override
	public void inserir(StatusPessoa statusPessoa) {
		session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.flush();
		session.clear();
		session.save(statusPessoa);
		session.getTransaction().commit();
	}

	@Override
	public List<StatusPessoa> listar() {
		session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Criteria c = session.createCriteria(StatusPessoa.class);
		List<StatusPessoa> listaStatusPessoas = c.list();		
		return listaStatusPessoas;
	}

	@Override
	public void remover(StatusPessoa statusPessoa) {
		session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		StatusPessoa statusPessoaRemover = (StatusPessoa)session.load(StatusPessoa.class, statusPessoa.getId());
		session.delete(statusPessoaRemover);
		session.getTransaction().commit();
	}

}
