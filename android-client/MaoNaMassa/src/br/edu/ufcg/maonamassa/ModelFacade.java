package br.edu.ufcg.maonamassa;

import br.edu.ufcg.maonamassa.models.Recipe;
import br.edu.ufcg.maonamassa.models.RecipeBook;
import br.edu.ufcg.maonamassa.models.Step;
import br.edu.ufcg.maonamassa.models.User;

public class ModelFacade {

	private RecipeBook book;
	private User user;

	public ModelFacade(RecipeBook book, User user) {
		this.book = book;
		this.user = user;
	}

	public Recipe createRecipe(Long id, String name) {
		return new Recipe(id, name, user);
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
