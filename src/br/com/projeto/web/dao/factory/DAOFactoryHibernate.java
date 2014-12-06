package br.com.projeto.web.dao.factory;

import br.com.projeto.web.dao.DAOClasse;
import br.com.projeto.web.dao.hibernate.DAOHibernateClasse;

public class DAOFactoryHibernate {
	public static DAOClasse getDAOClasse(){
		return new DAOHibernateClasse();
	}
}