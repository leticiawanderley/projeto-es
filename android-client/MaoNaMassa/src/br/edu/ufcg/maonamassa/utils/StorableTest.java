package br.edu.ufcg.maonamassa.utils;

import java.util.ArrayList;
import java.util.List;

import br.edu.ufcg.maonamassa.models.Recipe;
import br.edu.ufcg.maonamassa.models.Step;
import br.edu.ufcg.maonamassa.models.User;
import junit.framework.TestCase;

public class StorableTest extends TestCase {

	Recipe that;
	
	protected void setUp() throws Exception {
		User user = new User(23223L, "joopeeds@gmail.com", "Joao Pedro", "3782392jadoasjhdks293823");
		that = new Recipe(121893792L, "teste", user);
	}

	public void testRecipe() {
		that.addIngredient("Chocolate");
		that.addIngredient("Melancia");
		that.addIngredient("Leite");
		that.addStep(new Step("Cortar melancia", 0));
		that.addStep(new Step("Cozinhar tudo", 20));
		System.out.println(that.jsonify().replaceAll("\"", "\\\""));
		assertEquals("{\"id\":121893792,\"name\":\"teste\",\"author\":{\"id\":23223,\"email\":\"joopeeds@gmail.com\",\"name\":\"Joao Pedro\",\"accessToken\":\"3782392jadoasjhdks293823\",\"book\":{\"id\":null,\"recipes\":[],\"SERVER_URL\":\"https://mao-na-massa.appspot.com\",\"CREATE_ROUTE\":\"create/\",\"UPDATE_ROUTE\":\"update/\",\"DELETE_ROUTE\":\"delete/\",\"SEARCH_ROUTE\":\"search/\"}},\"ingredients\":[\"Chocolate\",\"Melancia\",\"Leite\"],\"steps\":[{\"id\":null,\"description\":\"Cortar melancia\",\"time\":0.0,\"annex\":null,\"SERVER_URL\":\"https://mao-na-massa.appspot.com\",\"CREATE_ROUTE\":\"create/\",\"UPDATE_ROUTE\":\"update/\",\"DELETE_ROUTE\":\"delete/\",\"SEARCH_ROUTE\":\"search/\"},{\"id\":null,\"description\":\"Cozinhar tudo\",\"time\":20.0,\"annex\":null,\"SERVER_URL\":\"https://mao-na-massa.appspot.com\",\"CREATE_ROUTE\":\"create/\",\"UPDATE_ROUTE\":\"update/\",\"DELETE_ROUTE\":\"delete/\",\"SEARCH_ROUTE\":\"search/\"}],\"SERVER_URL\":\"https://mao-na-massa.appspot.com\",\"CREATE_ROUTE\":\"create/\",\"UPDATE_ROUTE\":\"update/\",\"DELETE_ROUTE\":\"delete/\",\"SEARCH_ROUTE\":\"search/\"}",that.jsonify());
		Recipe ok = (Recipe) that.desjsonify("{\"id\":121893792,\"name\":\"teste\",\"author\":{\"id\":23223,\"email\":\"joopeeds@gmail.com\",\"name\":\"Joao Pedro\",\"accessToken\":\"3782392jadoasjhdks293823\",\"book\":{\"id\":null,\"recipes\":[],\"SERVER_URL\":\"https://mao-na-massa.appspot.com\",\"CREATE_ROUTE\":\"create/\",\"UPDATE_ROUTE\":\"update/\",\"DELETE_ROUTE\":\"delete/\",\"SEARCH_ROUTE\":\"search/\"}},\"ingredients\":[\"Chocolate\",\"Melancia\",\"Leite\"],\"steps\":[{\"id\":null,\"description\":\"Cortar melancia\",\"time\":0.0,\"annex\":null,\"SERVER_URL\":\"https://mao-na-massa.appspot.com\",\"CREATE_ROUTE\":\"create/\",\"UPDATE_ROUTE\":\"update/\",\"DELETE_ROUTE\":\"delete/\",\"SEARCH_ROUTE\":\"search/\"},{\"id\":null,\"description\":\"Cozinhar tudo\",\"time\":20.0,\"annex\":null,\"SERVER_URL\":\"https://mao-na-massa.appspot.com\",\"CREATE_ROUTE\":\"create/\",\"UPDATE_ROUTE\":\"update/\",\"DELETE_ROUTE\":\"delete/\",\"SEARCH_ROUTE\":\"search/\"}],\"SERVER_URL\":\"https://mao-na-massa.appspot.com\",\"CREATE_ROUTE\":\"create/\",\"UPDATE_ROUTE\":\"update/\",\"DELETE_ROUTE\":\"delete/\",\"SEARCH_ROUTE\":\"search/\"}");
		assertEquals("teste",ok.getName());
		assertEquals(121893792L, ok.getId(), 0.1);
		List<String> ing = ok.getIngredients();
		List<String> exp = new ArrayList<String>();
		exp.add("Chocolate");
		exp.add("Melancia");
		exp.add("Leite");
		assertEquals(exp, ing);
		List<Step> steps = ok.getSteps();
		assertEquals(steps.get(0).getDescription(), "Cortar melancia");
		assertEquals(steps.get(0).getTime(), 0.0);
		assertEquals(steps.get(1).getDescription(), "Cozinhar tudo");
		assertEquals(steps.get(1).getTime(), 20.0);
	}

}
