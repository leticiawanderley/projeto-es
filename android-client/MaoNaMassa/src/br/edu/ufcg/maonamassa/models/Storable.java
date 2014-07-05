package br.edu.ufcg.maonamassa.models;

/*
 * Classe que deve ser herdada por todos os objetos que ir�o ser armazenados 
 * na Datastore do Google App Engine server. Essa classe abstrai a create, update e delete.
 * Breve colocarei mais detalhes de como funcionar�, porque ainda n�o pensei.
 */

public abstract class Storable {

	public void create(String... fields){
		
	}
	
	public void update(String... fields){
		
	}
	
	public void delete(String id){
		
	}
	
}
