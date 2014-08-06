package br.edu.ufcg.maonamassa.tests;

import java.util.ArrayList;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import br.edu.ufcg.maonamassa.models.Recipe;
import br.edu.ufcg.maonamassa.models.RecipeBook;
import br.edu.ufcg.maonamassa.models.User;

public class RecipeBookTest {

	String ID = "1";
	Long ID2 = 2L;
	Long ID3 = 3L;
	Long ID4 = 4L;
	Long ID5 = 5L;
	String EMAIL = "qualquerEmail@";
	String NOME = "qualquer nome";
	String TOKEN = "token";
	User AUTOR = new User(ID, EMAIL, NOME, TOKEN);
	RecipeBook recipeBookTest1;

	@Before
	public void Before() {
		recipeBookTest1 = new RecipeBook();
	}

	@Test
	public void ConstrTest() {
		Assert.assertEquals(recipeBookTest1.getId(), null);
		Assert.assertEquals(recipeBookTest1.getRecipes(),
				new ArrayList<String>());

	}

	@Test
	public void addRecipeTest() throws Exception {
		Assert.assertEquals(recipeBookTest1.getRecipes().size(), 0);
		Assert.assertEquals(recipeBookTest1.searchRecipe("receita1"), null);

		recipeBookTest1.addRecipe(new Recipe(ID2, "receita1", AUTOR));
		// {ID2 (receita1)}

		Assert.assertEquals(recipeBookTest1.getRecipes().size(), 1);
		Assert.assertEquals(recipeBookTest1.searchRecipe("receita1"),
				new Recipe(ID2, "receita1", AUTOR));// primeira ocorrencia de
													// "receita1" = ID2

		recipeBookTest1.addRecipe(new Recipe(ID3, "receita1", AUTOR));
		// {ID2 (receita1), ID3 (receita1)}
		Assert.assertEquals(recipeBookTest1.getRecipes().size(), 2);
		Assert.assertEquals(recipeBookTest1.searchRecipe("receita1"),
				new Recipe(ID2, "receita1", AUTOR));// primeira ocorrencia de
													// "receita1" = ID2

		recipeBookTest1.addRecipe(new Recipe(ID4, "receita1", AUTOR));
		// {ID2 (receita1), ID3 (receita1), ID4 (receita1)}
		Assert.assertEquals(recipeBookTest1.getRecipes().size(), 3);
		Assert.assertEquals(recipeBookTest1.searchRecipe("receita1"),
				new Recipe(ID2, "receita1", AUTOR));// primeira ocorrencia de
													// "receita1" = ID2

		recipeBookTest1.addRecipe(new Recipe(ID5, "receita2", AUTOR));
		// {ID2 (receita1), ID3 (receita1), ID4 (receita1), ID5 (receita2)}
		Assert.assertEquals(recipeBookTest1.getRecipes().size(), 4);
		Assert.assertEquals(recipeBookTest1.searchRecipe("receita1"),
				new Recipe(ID2, "receita1", AUTOR));// primeira ocorrencia de
													// "receita1" = ID2
		Assert.assertEquals(recipeBookTest1.searchRecipe("receita2"),
				new Recipe(ID5, "receita1", AUTOR));// primeira ocorrencia de
													// "receita2" = ID5		
	}
	
	@Test
	public void removeRecipeTest() {
		recipeBookTest1.addRecipe(new Recipe(ID2, "receita1", AUTOR));
		recipeBookTest1.addRecipe(new Recipe(ID3, "receita1", AUTOR));
		recipeBookTest1.addRecipe(new Recipe(ID4, "receita1", AUTOR));
		recipeBookTest1.addRecipe(new Recipe(ID5, "receita2", AUTOR));
		
		recipeBookTest1.removeRecipe(new Recipe(ID3, "receita1", AUTOR));
		// {ID2 (receita1), REMOVIDO, ID4 (receita1), ID5 (receita2)}
		Assert.assertEquals(recipeBookTest1.getRecipes().size(), 3);
		Assert.assertEquals(recipeBookTest1.searchRecipe("receita1"),
				new Recipe(ID2, "receita1", AUTOR));// pesquise a primeira
													// ocorrencia do nome
													// "receita1" = ID2

		recipeBookTest1.removeRecipe(new Recipe(ID2, "receita1", AUTOR));
		// {REMOVIDO, REMOVIDO, ID4 (receita1), ID5 (receita2)}
		Assert.assertEquals(recipeBookTest1.getRecipes().size(), 2);
		Assert.assertEquals(recipeBookTest1.searchRecipe("receita1"),
				new Recipe(ID4, "receita1", AUTOR));// pesquise a primeira
													// ocorrencia do nome
													// "receita1" = ID4
	}

	@Test
	public void toStringTest() {
		Assert.assertEquals(recipeBookTest1.toString(), "RecipeBook [id="
				+ null + ", recipes=" + "[]"/* nenhuma recipe */+ "]");

		recipeBookTest1.addRecipe(new Recipe(ID2, "receita1", AUTOR));
		recipeBookTest1.addRecipe(new Recipe(ID3, "receita1", AUTOR));
		recipeBookTest1.addRecipe(new Recipe(ID4, "receita1", AUTOR));
		recipeBookTest1.addRecipe(new Recipe(ID5, "receita2", AUTOR));

		Assert.assertEquals(recipeBookTest1.toString(), "RecipeBook [id="
				+ null + ", recipes=" + recipeBookTest1.getRecipes().toString()
				+ "]");
	}
}
