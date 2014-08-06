package br.edu.ufcg.maonamassa.tests;

import java.util.ArrayList;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

import br.edu.ufcg.maonamassa.models.Recipe;
import br.edu.ufcg.maonamassa.models.User;

public class RecipeTest extends TestCase {
	String VAZIO = "";
	String ID = "1";
	Long ID2 = 2L;
	Long ID3 = 3L;
	String EMAIL = "qualquerEmail@";
	String NOME = "qualquer nome";
	String TOKEN = "token";
	User AUTOR = new User(ID, EMAIL, NOME, TOKEN);
	Recipe recipeTest1;
	Recipe recipeTest2;

	@Before
	public void before() {
		recipeTest1 = new Recipe(ID2, NOME, AUTOR);
	}

	@Test
	public void ConstrTest() {
		Assert.assertEquals(recipeTest1.getId(), ID2);
		Assert.assertEquals(recipeTest1.getName(), NOME);
		Assert.assertEquals(recipeTest1.getAuthor(), AUTOR);
		Assert.assertEquals(recipeTest1.getIngredients(),
				new ArrayList<String>());
		Assert.assertEquals(recipeTest1.getSteps(), new ArrayList<String>());
	}

	@Test
	public void toStringTest() {
		Assert.assertEquals(recipeTest1.toString(), NOME + "\ncreated by "
				+ AUTOR);
	}

	@Test
	public void testEquals() {
		Assert.assertFalse(recipeTest1.equals(recipeTest2));
		recipeTest1 = new Recipe(ID3, NOME, AUTOR);
		Assert.assertFalse(recipeTest1.equals(recipeTest2));

		try {
			recipeTest2 = new Recipe(ID2, NOME, AUTOR);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		Assert.assertTrue(recipeTest2.equals(recipeTest2));
	}

}
