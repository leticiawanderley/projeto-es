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
	
	public void addRecipe(Recipe newRecipe) {
		this.recipes.add(newRecipe);
	}
	
	public void removeRecipe(Recipe recipe) {
		this.recipes.remove(recipe);
	}
	
	public Recipe searchRecipe(String name) {
		for (Recipe r : this.recipes) {
			if (r.getName().equals(name)) {
				return r;
			}
		}
		return null;
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
