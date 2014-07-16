package br.edu.ufcg.maonamassa;

import br.edu.ufcg.maonamassa.models.Recipe;
import br.edu.ufcg.maonamassa.models.RecipeBook;
import br.edu.ufcg.maonamassa.models.Step;

public class ModelFacade {

	private RecipeBook book;

	public ModelFacade(RecipeBook book) {
		this.book = book;
	}

	public Recipe createRecipe(String name) {
		return new Recipe(name);
	}

	public void addNewIngredient(Recipe recipe, String newIngredient) {
		recipe.addIngredient(newIngredient);
	}

	public void addNewStep(Recipe recipe, String description, double time) {
		Step newStep = new Step(description, time);
		recipe.addStep(newStep);
	}

	public void addRecipe(Recipe recipe) {
		this.book.addRecipe(recipe);
	}

	public Recipe searchRecipe(String name) {
		return this.book.searchRecipe(name);
	}

}
