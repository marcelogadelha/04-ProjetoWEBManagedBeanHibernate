package br.com.projeto.web.dao.hibernate.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.hibernate.tool.hbm2ddl.SchemaExport;

@SuppressWarnings("deprecation")
public class DAOHibernateUtil {
	//DECLARAÇÃO DO OBJETO static PARA QUE SE POSSA CHAMAR O MÉTODO SEM UMA INSTANCIA DE HibernateUtil
	private static SessionFactory fabrica = null;
	private static ServiceRegistry registro;
	static {
		try {
			fabrica = getSessionFactory();
		} catch (Throwable e) {
			System.err.println("A criação do SessionFactory inicial falhou. " + e);
			throw new ExceptionInInitializerError(e);
		}
	}
	//MÉTODO static, POIS SEU RETORNO (SessionFactory) É STATIC 
	public static SessionFactory getSessionFactory() {
		if (fabrica == null) {
			Configuration configuracao = new Configuration();
			//PARA hibernate.cfg.xml
			configuracao.configure();
			registro = new ServiceRegistryBuilder().applySettings(configuracao.getProperties()).buildServiceRegistry();
			//PARA hibernate.properties
			//configuracao.addAnnotatedClass(Classe.class);//CLASSE COM A ANOTATION
			
			//INSTANCIANDO UM OBJETO DA CLASSE Configuration 
			fabrica = configuracao.buildSessionFactory(registro);
			fabrica.openSession();
			/*
			OU sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
			PARA ISSO É NECESSÁRIO throws MappingException
			*/
			return fabrica;
		}
		return fabrica;
	}
	public static Session getSession() {
		return fabrica.openSession();
	}
	public static void gerarBanco() {
		//CARREGA CONFIGURAÇÕES DE hibernate.cfg.xml
		Configuration configura = new Configuration();
		configura.configure();
		SchemaExport esquema = new SchemaExport(configura);
		//CRIA BANCO
		esquema.create(true, true);
	}
}