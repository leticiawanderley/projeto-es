package br.edu.ufcg.maonamassa.tests;

import java.util.ArrayList;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import br.edu.ufcg.maonamassa.models.User;

public class UserTest {

	String VAZIO = "";
	String ID = "1";
	String ID2 = "2";
	String EMAIL = "qualquerEmail@";
	String NOME = "qualquer nome";
	String TOKEN = "token";
	User userTest1;
	User userTest2;

	@Before
	public void before() {
		userTest1 = new User(ID, EMAIL, NOME, TOKEN);
	}

	@Test
	public void Constr1Test() {	
		Assert.assertEquals(userTest1.getId(), ID);
		Assert.assertEquals(userTest1.getEmail(), EMAIL);
		Assert.assertEquals(userTest1.getName(), NOME);
		Assert.assertEquals(userTest1.getAccessToken(), TOKEN);
	}

	@Test
	public void Constr2Test() {
		Assert.assertEquals(userTest1.getId(), ID);
		Assert.assertEquals(userTest1.getEmail(), EMAIL);
		Assert.assertEquals(userTest1.getName(), NOME);
		Assert.assertEquals(userTest1.getAccessToken(), TOKEN);
		Assert.assertEquals(userTest1.getBook().getRecipes(),
				new ArrayList<String>());
	}

	@Test
	public void testEquals() {
		Assert.assertFalse(userTest1.equals(userTest2));
		userTest2 = new User(ID2, EMAIL, NOME, TOKEN);
		Assert.assertFalse(userTest1.equals(userTest2));
		userTest2 = new User(ID, EMAIL, NOME, TOKEN);
		Assert.assertTrue(userTest1.equals(userTest2));

		userTest2 = new User(ID2, EMAIL, NOME, TOKEN);
		Assert.assertFalse(userTest1.equals(userTest2));
		userTest2 = new User(ID, EMAIL, NOME, TOKEN);
		Assert.assertTrue(userTest1.equals(userTest2));
	}
}
