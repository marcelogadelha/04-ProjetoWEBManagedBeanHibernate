package br.com.projeto.web.dao;

import java.util.List;

import br.com.projeto.web.model.Classe;

public interface DAOClasse {
	public abstract void alterar(Classe classe);
	public abstract Classe consultar(Classe classe);
	public abstract void excluir(Classe classe);
	public abstract boolean existe(Classe classe);
	public abstract void inserir(Classe classe);
	public abstract List<Classe> listar();
}