package br.com.tg.repositorio;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;

import br.com.tg.entidades.TipoPessoa;
import br.com.tg.util.HibernateUtil;

public class RepositorioTipoPessoaDAO implements RepositorioTipoPessoa {

	private Session session;
	
	@Override
	public void atualizar(TipoPessoa tipoPessoa) {
		session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.flush();
		session.clear();
		session.merge(tipoPessoa);
		session.getTransaction().commit();
	}

	@Override
	public TipoPessoa getTipoPessoa(Integer idTipoPessoa) {
		session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		TipoPessoa tipoPessoa = (TipoPessoa)session.load(TipoPessoa.class, idTipoPessoa);
		session.getTransaction().commit();
		return tipoPessoa;	
	}

	@Override
	public void inserir(TipoPessoa tipoPessoa) {
		session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.flush();
		session.clear();
		session.save(tipoPessoa);
		session.getTransaction().commit();
	}

	@Override
	public List<TipoPessoa> listar() {
		session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Criteria c = session.createCriteria(TipoPessoa.class);
		List<TipoPessoa> listaTipoPessoas = c.list();		
		return listaTipoPessoas;
	}

	@Override
	public void remover(TipoPessoa tipoPessoa) {
		session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		TipoPessoa tipoPessoaRemover = (TipoPessoa)session.load(TipoPessoa.class, tipoPessoa.getId());
		session.delete(tipoPessoaRemover);
		session.getTransaction().commit();
	}

}
