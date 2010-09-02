package br.com.tg.repositorio;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;

import br.com.tg.entidades.TipoTelefone;
import br.com.tg.entidades.TipoUsuario;
import br.com.tg.util.HibernateUtil;

public class RepositorioTipoTelefoneDAO implements RepositorioTipoTelefone {

	private Session session;
	
	@Override
	public void atualizar(TipoTelefone tipoTelefone) {
		session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.flush();
		session.clear();
		session.merge(tipoTelefone);
		session.getTransaction().commit();
	}

	@Override
	public TipoTelefone getTipoTelefone(Integer idTipoTelefone) {
		session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		TipoTelefone tipoTelefone = (TipoTelefone)session.load(TipoTelefone.class, idTipoTelefone);
		session.getTransaction().commit();
		return tipoTelefone;	
	}

	@Override
	public void inserir(TipoTelefone tipoTelefone) {
		session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.flush();
		session.clear();
		session.save(tipoTelefone);
		session.getTransaction().commit();
	}

	@Override
	public List<TipoTelefone> listar() {
		session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Criteria c = session.createCriteria(TipoTelefone.class);
		List<TipoTelefone> listaTipoTelefone = c.list();		
		return listaTipoTelefone;
	}

	@Override
	public void remover(TipoTelefone tipoTelefone) {
		session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		TipoTelefone tipoTelefoneRemover = (TipoTelefone)session.load(TipoTelefone.class, tipoTelefone.getId());
		session.delete(tipoTelefoneRemover);
		session.getTransaction().commit();
	}

}
