package br.edu.ufcg.maonamassa.models;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

public class RecipeTest extends TestCase {

	Recipe that;
	
	protected void setUp() throws Exception {
		that = new Recipe("teste");
	}

	public void testRecipe() {
		that.addIngredient("Chocolate");
		that.addIngredient("Melancia");
		that.addIngredient("Leite");
		that.addStep(new Step("Cortar melancia", 0));
		that.addStep(new Step("Cozinhar tudo", 20));
		assertEquals("{\"id\":null,\"name\":\"teste\",\"ingredients\":[\"Chocolate\",\"Melancia\",\"Leite\"],\"steps\":[{\"id\":null,\"description\":\"Cortar melancia\",\"time\":0.0,\"annex\":null},{\"id\":null,\"description\":\"Cozinhar tudo\",\"time\":20.0,\"annex\":null}]}",that.jsonify());
		Recipe ok = (Recipe) that.desjsonify("{\"id\":null,\"name\":\"teste\",\"ingredients\":[\"Chocolate\",\"Melancia\",\"Leite\"],\"steps\":[{\"id\":null,\"description\":\"Cortar melancia\",\"time\":0.0,\"annex\":null},{\"id\":null,\"description\":\"Cozinhar tudo\",\"time\":20.0,\"annex\":null}]}", Recipe.class);
		assertEquals("teste",ok.getName());
		assertEquals(null,ok.getId());
		List<String> ing = ok.getIngredients();
		List<String> exp = new ArrayList<String>();
		exp.add("Chocolate");
		exp.add("Melancia");
		exp.add("Leite");
		assertEquals(exp, ing);

	}

}
