package br.edu.ufcg.maonamassa.models;

import java.io.ObjectInputStream.GetField;

/**
 * Exemplo de como deve funcionar as classes armazenaveis.
 * Todos os campos deve ser passados como string para
 * @author JoãoPedro
 *
 */


public class Recipe extends Storable {

	private int id;
	private String name;
	
	
	public Recipe(String name) {
		this.name = name;
	}
	
	public void create() {
		create(Integer.toString(id), name);
	}
	
	public void update() {
		update(Integer.toString(id), name);
	}
	
	public void delete() {
		delete(Integer.toString(id));
	}
	
}
