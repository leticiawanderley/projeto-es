package br.edu.ufcg.maonamassa.models;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import br.edu.ufcg.maonamassa.utils.Storable;

/**
 * Recipe class
 * @author JoaoPedro
 *
 */

public class Recipe extends Storable<Recipe> {

	private Long id;
	
	private String name;
	
	private User author;
	
	private List<String> ingredients;
	
	private List<Step> steps;
	
	public Recipe(Long id, String name, User author) {
		this.id = id;
		this.name = name;
		this.author = author;
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

	public User getAuthor() {
		return author;
	}

	public void setAuthor(User author) {
		this.author = author;
	}

	@Override
	public String toString() {
		return  name + "\ncreated by " + author;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Recipe other = (Recipe) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
