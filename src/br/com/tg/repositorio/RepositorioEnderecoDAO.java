package br.com.tg.repositorio;

import org.hibernate.Session;

import br.com.tg.entidades.Endereco;
import br.com.tg.util.HibernateUtil;

public class RepositorioEnderecoDAO implements RepositorioEndereco {

	private Session session;
	
	@Override
	public void inserir(Endereco endereco) {
		session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.flush();
		session.clear();
		session.save(endereco);
		session.getTransaction().commit();
	}
	
	public void atualizar(Endereco endereco) {
		session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.flush();
		session.clear();
		session.merge(endereco);
		session.getTransaction().commit();
	}

	@Override
	public Endereco getEndereco(Integer idEndereco) {
		session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Endereco endereco = (Endereco)session.load(Endereco.class, idEndereco);
		session.getTransaction().commit();
		return endereco;
	}

	@Override
	public void remover(Endereco endereco) {
		session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Endereco enderecoRemover = (Endereco)session.load(Endereco.class, endereco.getId());
		session.delete(enderecoRemover);
		session.getTransaction().commit();
	}

}
