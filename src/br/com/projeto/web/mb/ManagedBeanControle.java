package br.com.projeto.web.mb;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.com.projeto.web.dao.DAOClasse;
import br.com.projeto.web.dao.factory.DAOFactoryHibernate;
import br.com.projeto.web.model.Classe;

@ManagedBean(name = "ManagedBeanControle")
@ViewScoped
public class ManagedBeanControle implements Serializable{
	private static final long serialVersionUID = 1L;
	private Classe classe;
	private List<Classe> lista=new ArrayList<Classe>();
	private DAOClasse dao = DAOFactoryHibernate.getDAOClasse();
	
	//CONSTRUTOR
    public ManagedBeanControle() {
        super();
        this.classe = new Classe();
        // TODO Auto-generated constructor stub
    }
  	//VALIDANDO SESSÃO
    public String LogIn() {
    	if(!(dao.existe(classe))){
			//if(dao.consultar(classe.getNumero()).getNumero()!=classe.getNumero()){
				dao.inserir(classe);
				
				//COLOCAR OBJETO NA SESSÃO
				FacesContext contexto = FacesContext.getCurrentInstance();
				contexto.getExternalContext().getSessionMap().put("objeto", classe);
				contexto.getExternalContext().getSessionMap().put("nomear", classe.getNome());
				contexto.getExternalContext().getSessionMap().put("lista", dao.listar());
				//PEGAR OBJETO DE SESSÃO
				//FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("atributo");
				return "/paginas/sucesso/sucessoJSTLEL.jsp";
			//}
			//FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Longin inválido!"));
		}
    	return "/index.jsp";
    }
    //INVALIDANDO A SESSÃO
  	public String LogOut() throws IOException{
  		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
  		//FacesContext.getCurrentInstance().getExternalContext().redirect("/index.xhtml");
  		return "/index";
  	}
  	
    //GET
  	public Classe getClasse() {
  		return classe;
  	}
  	public List<Classe> getLista() {
  		return lista;
  	}
  	//SET
  	public void setClasse(Classe classe) {
  		this.classe = classe;
  	}
  	public void setLista(List<Classe> lista) {
  		this.lista = lista;
  	}
  	
    //CRUD
  	public void alterar(){
  		if(classe != null){
  			dao.alterar(classe);
  	  		listar();
  	  		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Manutenção: ", "Atualização bem sucedida!"));
  		}
  		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Manutenção: ", "Atualização NÃO sucedida!"));
  	}
  	public void consultar(){
  		classe=dao.consultar(classe);
  		if(classe != null){
  			if(classe.getNome().equalsIgnoreCase("")) {
  				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Manutenção: ", "Nome Encontrado!"));
  			}else{
  				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Manutenção: ", "Nome NÃO Encontrado!"));
  			}
  		}
  		listar();
  	}
  	public void excluir(){
  		if(classe!=null){
  			dao.excluir(classe);
  	  		listar();
  	  		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Manutenção: ", "Exclusão bem sucedida!"));
  		}else{
  			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Manutenção: ", "Exclusão NÃO sucedida!"));
  		}
  	}
  	public void inserir(){
  		if(classe!=null){
  			if(tratarValor()&&tratarRepeticao()){
  				dao.inserir(classe);
  		  		listar();
  		  		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Manutenção: ", "Inserção bem sucedida!"));
  			}else{
  				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Manutenção: ", "Inserção NÃO sucedida!"));
  			}
  		}
  		limpar();
  	}
  	public void listar(){
  		this.lista = dao.listar();
  	}
  	public void limpar(){
  		this.classe=new Classe();
  	}
  	
	//BUSCA SE HÁ REPETIÇÃO DO VALOR 
	public boolean tratarRepeticao(){
		try{
			return (!(dao.existe(classe)))? true:false;
		}catch(NumberFormatException e){
			e.printStackTrace();
			return false;
		}
	}
	//VERIFICA SE O VALOR DE ENTRADA É VÁLIDO
	public boolean tratarValor(){
		return (String.valueOf(classe.getNumero()).matches("([0-9]+)"))? true:false;
	}
	
    //CONTROLE DE ACESSO
	//https://dhn3.dhn.mb/grnhid/aplica/sigdem20/grnhid.nsf/0/51F04E71E6DD531283257D480044E6B2?OpenDocument
	//https://dhn3.dhn.mb/grnhid/aplica/sigdem20/psigdem.nsf/FormFichaDocumento?OpenAgent&304&MR-2009/04-38362&admingnho/grnhid/Mar&&&&&&&
    //"[a-z]{2,20}@[a-z]{2,20}.com(.|)[a-z]{0,2}"
	//"[a-zA-Z0-9]{2,20}"
  	
	//EDIÇÃO DE LINHAS
  	/*
	public void onRowEdit(RowEditEvent event) {
		if(event.getObject() == null) {
        	FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Manutenção: ", "Atualização NÃO realizada!"));
        }else{
			dao.alterar((Classe)event.getObject());
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Manutenção: ", "Atualização bem sucedida!"));
        }
		limpar();
		listar();
	}
	public void onRowCancel(RowEditEvent event) {
	}
	*/
    
	/*
	<!-- 
	<p:idleMonitor timeout="8000">
		<p:ajax event="idle" listener="#{documentoMB.onInativo}" update="mensagem" />
		<p:ajax event="active" listener="#{documentoMB.onAtivo}" update="mensagem" />
	</p:idleMonitor>
	 -->
	public void onInativo() {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Inatividade.", "O que está fazendo?"));
	}
	public void onAtivo() {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Bem vindo de volta", "Bem, foi um longo cafézinho!"));
	}
	*/
}