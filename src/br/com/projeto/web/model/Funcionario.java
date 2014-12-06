package br.com.projeto.web.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity @Table (name="funcionario", schema="model")
@PrimaryKeyJoinColumn(name = "id")
@DiscriminatorValue("Funcionario")
public class Funcionario extends Pessoa implements Serializable{
	private static final long serialVersionUID = 1L;
	
	//private long id;//NÃO PRECISA, USA-SE O id DE Pessoa
	@Column(nullable=false, length=45)
	private String tipo;
	@Column(nullable=false, length=45)
	private float numero;
	@Temporal(TemporalType.DATE)/*.TIME, .TIMESTAMP*/ @Column
	private Date nascimento;
	
	public Funcionario() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Funcionario(long id, String nome, float numero, Date cadastro) {
		super(id, nome, numero, cadastro);
		// TODO Auto-generated constructor stub
	}
	public Funcionario(String tipo, float numero, Date nascimento) {
		super();
		this.tipo = tipo;
		this.numero = numero;
		this.nascimento = nascimento;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Funcionario other = (Funcionario) obj;
		if (nascimento == null) {
			if (other.nascimento != null)
				return false;
		} else if (!nascimento.equals(other.nascimento))
			return false;
		if (Float.floatToIntBits(numero) != Float.floatToIntBits(other.numero))
			return false;
		if (tipo == null) {
			if (other.tipo != null)
				return false;
		} else if (!tipo.equals(other.tipo))
			return false;
		return true;
	}
	public Date getNascimento() {
		return nascimento;
	}
	public float getNumero() {
		return numero;
	}
	public String getTipo() {
		return tipo;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result
				+ ((nascimento == null) ? 0 : nascimento.hashCode());
		result = prime * result + Float.floatToIntBits(numero);
		result = prime * result + ((tipo == null) ? 0 : tipo.hashCode());
		return result;
	}
	public void setNascimento(Date nascimento) {
		this.nascimento = nascimento;
	}
	public void setNumero(float numero) {
		this.numero = numero;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	@Override
	public String toString() {
		return "Funcionario [tipo=" + tipo + ", numero=" + numero
				+ ", nascimento=" + nascimento + "]";
	}
}