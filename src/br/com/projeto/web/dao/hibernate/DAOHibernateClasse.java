package br.com.projeto.web.dao.hibernate;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

import br.com.projeto.web.dao.DAOClasse;
import br.com.projeto.web.dao.hibernate.util.DAOHibernateUtil;
import br.com.projeto.web.model.Classe;

public class DAOHibernateClasse implements DAOClasse{
	private Session session;
	@Override
	public void alterar(Classe classe) {
		try{
			session = DAOHibernateUtil.getSession();
			session.beginTransaction();
			session.update(classe);//session.merge(classe)
			session.getTransaction().commit();
		}catch(Exception e){
			session.getTransaction().rollback();
			e.printStackTrace();
		}finally{
			session.close();  
		}
	}
	@Override
	public Classe consultar(Classe classe) {
		Classe retorno=new Classe();
		try {
			session = DAOHibernateUtil.getSession();
			session.beginTransaction();
			//PODE-SE USAR OUTROS ATRIBUTOS NA PESQUISA
			retorno = (Classe) session.get(Classe.class, classe.getNumero());
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return retorno;
	}
	@Override
	public boolean existe(Classe classe){
		Classe retorno=consultar(classe);
		return (retorno!=null);
	}
	/*
	public Classe consultar(Long numero) {
		session = DAOHibernateUtil.getSession();
		session.beginTransaction();
		Criteria criterio = session.createCriteria(Classe.class).add(Restrictions.like("numero", numero));
		return (Classe)criterio.uniqueResult();
	}
	*/
	@Override
	public void excluir(Classe classe) {
		try{
			session = DAOHibernateUtil.getSession();
			session.beginTransaction();
			session.delete(classe);
			session.getTransaction().commit();
		}catch(Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		}finally{
			session.close();
		}
	}
	@Override
	public void inserir(Classe classe) {
		try {
			session = DAOHibernateUtil.getSession();
			session.beginTransaction();
			session.save(classe);
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}
	@SuppressWarnings ("unchecked")
	@Override
	public List<Classe> listar() {
		List<Classe> lista = new ArrayList<Classe>();
		try{
			session = DAOHibernateUtil.getSession();
			session.beginTransaction();
			lista = (List<Classe>)session.createCriteria(Classe.class).list();
			session.getTransaction().commit();
		}catch(Exception e){
			session.getTransaction().rollback();
			e.printStackTrace();
		}finally{
			session.close();
		}
		return lista;
	}
	/*
	@SuppressWarnings ("unchecked" )
	public List<Classe> listar(Classe classe) {
		Criteria c = session.createCriteria(Classe.class);
		if(classe.getNome().length() > 0){
			c.add(Restrictions.like("nome", classe.getNome()+"%"));
		}
		c.addOrder(Order.asc("nome"));
		return (List<classe>)c.list();
	}
	*/
}