package br.com.projeto.web.mb;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@ManagedBean(name = "ManagedBeanControladorDeEntidade")
@ViewScoped
public class ManagedBeanControleDeEntidade implements Serializable{
	private static final long serialVersionUID = 1L;
	@PersistenceContext
	EntityManager em;
	
	//CONSTRUTOR
    public ManagedBeanControleDeEntidade() {
        super();
        // TODO Auto-generated constructor stub
    }
}