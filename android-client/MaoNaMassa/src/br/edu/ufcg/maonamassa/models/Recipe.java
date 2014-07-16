package br.edu.ufcg.maonamassa.models;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import br.edu.ufcg.maonamassa.utils.Storable;

/**
 * Exemplo de como deve funcionar as classes armazenaveis.
 * @author Jo�oPedro
 *
 */

public class Recipe extends Storable<Recipe> {

	private Long id;
	
	private String name;
	
	private List<String> ingredients;
	
	private List<Step> steps;
	
	public Recipe(String name) {
		this.name = name;
		this.ingredients = new ArrayList<String>();
		this.steps = new LinkedList<Step>();
	}
	
	public void addIngredient(String newIngredient) {
		this.ingredients.add(newIngredient);
	}
	
	public void removeIngredient(String ingredient) {
		this.ingredients.remove(ingredient);
	}
	
	public void addStep(Step newStep){
		this.steps.add(newStep);
	}
	
	public void removeStep(Step step) {
		this.steps.remove(step);
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
		this.update();
	}

	public List<String> getIngredients() {
		return ingredients;
	}

	public void setIngredients(List<String> ingredients) {
		this.ingredients = ingredients;
	}

	public List<Step> getSteps() {
		return steps;
	}

	public void setSteps(List<Step> steps) {
		this.steps = steps;
	}

	public Long getId() {
		return id;
	}	
}
