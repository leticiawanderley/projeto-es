package br.edu.ufcg.maonamassa.models;

import java.util.ArrayList;
import java.util.List;

import br.edu.ufcg.maonamassa.utils.Storable;

public class RecipeBook extends Storable<RecipeBook>{
	
	private Long id;
	
	private List<Recipe> recipes;
	
	public RecipeBook() {
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

	public List<Recipe> getRecipes() {
		return recipes;
	}

	public void setRecipes(List<Recipe> recipes) {
		this.recipes = recipes;
	}

	public Long getId() {
		return id;
	}

	@Override
	public String toString() {
		return "RecipeBook [id=" + id + ", recipes=" + recipes + "]";
	}
	
}
