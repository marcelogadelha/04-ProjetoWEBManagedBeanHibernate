package br.com.projeto.web.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/*
Tabela por classe Concreta: só as filhas
Tabela por Hierarquia: tudo numa só
Tabela por Sub-Classe: pais e filhas
*/
@Entity @Table (name="pessoa", schema="model")
@Inheritance(strategy=InheritanceType.JOINED)//indica qual do 3 tipos de herança será mapeada
@DiscriminatorColumn(name = "Tipo")
public class Pessoa implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue(strategy = GenerationType.SEQUENCE) @Column
	private long id;
	@Column(nullable=false, length=45)
	private String nome;
	@Column(nullable=false, length=45)
	private float numero;
	@Temporal(TemporalType.DATE)/*.TIME, .TIMESTAMP*/ @Column
	private Date cadastro;
	//@Column(unique=true, nullable=false, length=15, precision='2', columnDefinition="default '0'")
	
	public Pessoa() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Pessoa(long id, String nome, float numero, Date cadastro) {
		super();
		this.id = id;
		this.nome = nome;
		this.numero = numero;
		this.cadastro = cadastro;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pessoa other = (Pessoa) obj;
		if (cadastro == null) {
			if (other.cadastro != null)
				return false;
		} else if (!cadastro.equals(other.cadastro))
			return false;
		if (id != other.id)
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (Float.floatToIntBits(numero) != Float.floatToIntBits(other.numero))
			return false;
		return true;
	}
	public Date getCadastro() {
		return cadastro;
	}
	public long getId() {
		return id;
	}
	public String getNome() {
		return nome;
	}
	public float getNumero() {
		return numero;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((cadastro == null) ? 0 : cadastro.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + Float.floatToIntBits(numero);
		return result;
	}
	public void setCadastro(Date cadastro) {
		this.cadastro = cadastro;
	}
	public void setId(long id) {
		this.id = id;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public void setNumero(float numero) {
		this.numero = numero;
	}
	@Override
	public String toString() {
		return "Pessoa [id=" + id + ", nome=" + nome + ", numero=" + numero
				+ ", cadastro=" + cadastro + "]";
	}
}