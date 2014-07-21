package br.edu.ufcg.maonamassa.utils;

import java.util.ArrayList;
import java.util.List;

import br.edu.ufcg.maonamassa.models.Recipe;
import br.edu.ufcg.maonamassa.models.Step;
import br.edu.ufcg.maonamassa.models.User;
import junit.framework.TestCase;

public class StorableTest extends TestCase {

	Recipe that;
	List<String> ing;
	List<String> exp;
	
	protected void setUp() throws Exception {
		User user = new User(23223L, "joopeeds@gmail.com", "Joao Pedro", "3782392jadoasjhdks293823");
		that = new Recipe(121893792L, "teste", user);
		that.addIngredient("Chocolate");
		that.addIngredient("Melancia");
		that.addIngredient("Leite");
		that.addStep(new Step("Cortar melancia", 0));
		that.addStep(new Step("Cozinhar tudo", 20));
		exp = new ArrayList<String>();
		exp.add("Chocolate");
		exp.add("Melancia");
		exp.add("Leite");
	}

	public void testRecipe() {
		
		//System.out.println(that.jsonify().replaceAll("\"", "\\\""));
		assertEquals("{\"id\":121893792,\"name\":\"teste\",\"author\":{\"id\":23223,\"email\":\"joopeeds@gmail.com\",\"name\":\"Joao Pedro\",\"accessToken\":\"3782392jadoasjhdks293823\",\"book\":{\"id\":null,\"recipes\":[]}},\"ingredients\":[\"Chocolate\",\"Melancia\",\"Leite\"],\"steps\":[{\"id\":null,\"description\":\"Cortar melancia\",\"time\":0.0,\"annex\":null},{\"id\":null,\"description\":\"Cozinhar tudo\",\"time\":20.0,\"annex\":null}]}",that.jsonify());
		Recipe ok = that.desjsonify("{\"id\":121893792,\"name\":\"teste\",\"author\":{\"id\":23223,\"email\":\"joopeeds@gmail.com\",\"name\":\"Joao Pedro\",\"accessToken\":\"3782392jadoasjhdks293823\",\"book\":{\"id\":null,\"recipes\":[]}},\"ingredients\":[\"Chocolate\",\"Melancia\",\"Leite\"],\"steps\":[{\"id\":null,\"description\":\"Cortar melancia\",\"time\":0.0,\"annex\":null},{\"id\":null,\"description\":\"Cozinhar tudo\",\"time\":20.0,\"annex\":null}]}");
		assertEquals("teste",ok.getName());
		assertEquals(121893792L, ok.getId(), 0.1);
		ing = ok.getIngredients();
		assertEquals(exp, ing);
		List<Step> steps = ok.getSteps();
		assertEquals(steps.get(0).getDescription(), "Cortar melancia");
		assertEquals(steps.get(0).getTime(), 0.0);
		assertEquals(steps.get(1).getDescription(), "Cozinhar tudo");
		assertEquals(steps.get(1).getTime(), 20.0);
	}

	
	public void testListRecipe(){
		List<Recipe> total = new ArrayList<Recipe>();
		total.add(that);
		
		String jsonstr = that.jsonifyList(total);
		
		List<Recipe> result = that.desjsonifyList(jsonstr);
		assertEquals(result.size(), total.size());
		assertEquals(total, result);
		
		
		
	}
	public void testSearch() throws Exception{
		List<Recipe> oi = that.search(new StorableQuery());
		System.out.println(oi.get(0).getSteps());
		
		
	}
}
