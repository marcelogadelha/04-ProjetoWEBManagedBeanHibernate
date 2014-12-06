package br.com.projeto.web.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity @Table (name="classe")
public class Classe implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id @Column(nullable=false, length=45)
	private Long numero;
	@Column(nullable=false, length=45)
	private String nome;
	@Temporal(TemporalType.DATE)/*.TIME, .TIMESTAMP*/ @Column(length=45)
	private Date data;
	
	public Classe() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Classe(Long numero, String nome, Date data) {
		super();
		this.numero = numero;
		this.nome = nome;
		this.data = data;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Classe other = (Classe) obj;
		if (data == null) {
			if (other.data != null)
				return false;
		} else if (!data.equals(other.data))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (numero == null) {
			if (other.numero != null)
				return false;
		} else if (!numero.equals(other.numero))
			return false;
		return true;
	}
	public Date getData() {
		return data;
	}
	public String getNome() {
		return nome;
	}
	public Long getNumero() {
		return numero;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((data == null) ? 0 : data.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((numero == null) ? 0 : numero.hashCode());
		return result;
	}
	public void setData(Date data) {
		this.data = data;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public void setNumero(Long numero) {
		this.numero = numero;
	}
	@Override
	public String toString() {
		return "Classe [numero=" + numero + ", nome=" + nome + ", data=" + data
				+ "]";
	}
}