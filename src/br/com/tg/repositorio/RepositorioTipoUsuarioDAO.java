package br.com.tg.repositorio;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;

import br.com.tg.entidades.TipoUsuario;
import br.com.tg.util.HibernateUtil;

public class RepositorioTipoUsuarioDAO implements RepositorioTipoUsuario {

	private Session session;
	
	@Override
	public void atualizar(TipoUsuario tipoUsuario) {
		session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.flush();
		session.clear();
		session.merge(tipoUsuario);
		session.getTransaction().commit();
	}

	@Override
	public TipoUsuario getTipoUsuario(Integer idTipoUsuario) {
		session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		TipoUsuario tipoUsuario = (TipoUsuario)session.load(TipoUsuario.class, idTipoUsuario);
		session.getTransaction().commit();
		return tipoUsuario;	
	}

	@Override
	public void inserir(TipoUsuario tipoUsuario) {
		session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.flush();
		session.clear();
		session.save(tipoUsuario);
		session.getTransaction().commit();
	}

	@Override
	public List<TipoUsuario> listar() {
		session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Criteria c = session.createCriteria(TipoUsuario.class);
		List<TipoUsuario> listaTipoUsuario = c.list();		
		return listaTipoUsuario;
	}

	@Override
	public void remover(TipoUsuario tipoUsuario) {
		session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		TipoUsuario tipoUsuarioRemover = (TipoUsuario)session.load(TipoUsuario.class, tipoUsuario.getId());
		session.delete(tipoUsuarioRemover);
		session.getTransaction().commit();
	}

}
