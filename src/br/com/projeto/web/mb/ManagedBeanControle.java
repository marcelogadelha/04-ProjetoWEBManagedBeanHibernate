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
  	//VALIDANDO SESS�O
    public String LogIn() {
    	if(!(dao.existe(classe))){
			//if(dao.consultar(classe.getNumero()).getNumero()!=classe.getNumero()){
				dao.inserir(classe);
				
				//COLOCAR OBJETO NA SESS�O
				FacesContext contexto = FacesContext.getCurrentInstance();
				contexto.getExternalContext().getSessionMap().put("objeto", classe);
				contexto.getExternalContext().getSessionMap().put("nomear", classe.getNome());
				contexto.getExternalContext().getSessionMap().put("lista", dao.listar());
				//PEGAR OBJETO DE SESS�O
				//FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("atributo");
				return "/paginas/sucesso/sucessoJSTLEL.jsp";
			//}
			//FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Longin inv�lido!"));
		}
    	return "/index.jsp";
    }
    //INVALIDANDO A SESS�O
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
  	  		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Manuten��o: ", "Atualiza��o bem sucedida!"));
  		}
  		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Manuten��o: ", "Atualiza��o N�O sucedida!"));
  	}
  	public void consultar(){
  		classe=dao.consultar(classe);
  		if(classe != null){
  			if(classe.getNome().equalsIgnoreCase("")) {
  				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Manuten��o: ", "Nome Encontrado!"));
  			}else{
  				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Manuten��o: ", "Nome N�O Encontrado!"));
  			}
  		}
  		listar();
  	}
  	public void excluir(){
  		if(classe!=null){
  			dao.excluir(classe);
  	  		listar();
  	  		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Manuten��o: ", "Exclus�o bem sucedida!"));
  		}else{
  			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Manuten��o: ", "Exclus�o N�O sucedida!"));
  		}
  	}
  	public void inserir(){
  		if(classe!=null){
  			if(tratarValor()&&tratarRepeticao()){
  				dao.inserir(classe);
  		  		listar();
  		  		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Manuten��o: ", "Inser��o bem sucedida!"));
  			}else{
  				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Manuten��o: ", "Inser��o N�O sucedida!"));
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
  	
	//BUSCA SE H� REPETI��O DO VALOR 
	public boolean tratarRepeticao(){
		try{
			return (!(dao.existe(classe)))? true:false;
		}catch(NumberFormatException e){
			e.printStackTrace();
			return false;
		}
	}
	//VERIFICA SE O VALOR DE ENTRADA � V�LIDO
	public boolean tratarValor(){
		return (String.valueOf(classe.getNumero()).matches("([0-9]+)"))? true:false;
	}
	
    //CONTROLE DE ACESSO
	//https://dhn3.dhn.mb/grnhid/aplica/sigdem20/grnhid.nsf/0/51F04E71E6DD531283257D480044E6B2?OpenDocument
	//https://dhn3.dhn.mb/grnhid/aplica/sigdem20/psigdem.nsf/FormFichaDocumento?OpenAgent&304&MR-2009/04-38362&admingnho/grnhid/Mar&&&&&&&
    //"[a-z]{2,20}@[a-z]{2,20}.com(.|)[a-z]{0,2}"
	//"[a-zA-Z0-9]{2,20}"
  	
	//EDI��O DE LINHAS
  	/*
	public void onRowEdit(RowEditEvent event) {
		if(event.getObject() == null) {
        	FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Manuten��o: ", "Atualiza��o N�O realizada!"));
        }else{
			dao.alterar((Classe)event.getObject());
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Manuten��o: ", "Atualiza��o bem sucedida!"));
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
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Inatividade.", "O que est� fazendo?"));
	}
	public void onAtivo() {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Bem vindo de volta", "Bem, foi um longo caf�zinho!"));
	}
	*/
}