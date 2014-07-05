package br.edu.ufcg.maonamassa.models;

/**
 * Exemplo de como deve funcionar as classes armazenaveis.
 * @author JoãoPedro
 *
 */


public class Recipe extends Storable<Recipe> {

	private int id;
	private String name;
	
	public Recipe(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
		this.update();
	}


	public int getId() {
		return id;
	}


	
	
	
	
	
	
}
