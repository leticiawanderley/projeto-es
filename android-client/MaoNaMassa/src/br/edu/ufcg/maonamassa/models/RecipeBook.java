package br.edu.ufcg.maonamassa.models;

import java.util.ArrayList;
import java.util.List;

public class RecipeBook extends Storable<RecipeBook>{
	
	private Long id;
	
	private User owner;
	
	private List<Recipe> recipes;
	
	public RecipeBook(User owner) {
		this.owner = owner;
		this.recipes = new ArrayList<Recipe>();
	}

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}

	public List<Recipe> getRecipes() {
		return recipes;
	}

	public void setRecipes(List<Recipe> recipes) {
		this.recipes = recipes;
	}

	public Long getId() {
		return id;
	}
}
