package br.com.tg.repositorio;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;

import br.com.tg.entidades.Telefone;
import br.com.tg.entidades.TipoUsuario;
import br.com.tg.util.HibernateUtil;

public class RepositorioTelefoneDAO implements RepositorioTelefone {
	
	private Session session;

	@Override
	public void atualizar(Telefone telefone) {
		session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.flush();
		session.clear();
		session.merge(telefone);
		session.getTransaction().commit();
	}

	@Override
	public Telefone getTelefone(Integer idTelefone) {
		session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Telefone telefone = (Telefone)session.load(Telefone.class, idTelefone);
		session.getTransaction().commit();
		return telefone;	
	}

	@Override
	public void inserir(Telefone telefone) {
		session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.flush();
		session.clear();
		session.save(telefone);
		session.getTransaction().commit();
	}

	@Override
	public List<Telefone> listar() {
		session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Criteria c = session.createCriteria(Telefone.class);
		List<Telefone> listaTelefone = c.list();		
		return listaTelefone;
	}

	@Override
	public void remover(Telefone telefone) {
		session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Telefone telefoneRemover = (Telefone)session.load(Telefone.class, telefone.getId());
		session.delete(telefoneRemover);
		session.getTransaction().commit();
	}

}
