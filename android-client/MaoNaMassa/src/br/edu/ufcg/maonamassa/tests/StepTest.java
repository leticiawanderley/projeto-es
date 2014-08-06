package br.edu.ufcg.maonamassa.tests;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import br.edu.ufcg.maonamassa.models.Step;

public class StepTest {

	String VAZIO = "";
	String DESCRICAO = "QUALQUER DESCRICAO";
	double TIME = 0.0;
	Step stepTest1;

	@Before
	public void before() {
		stepTest1 = new Step(DESCRICAO, TIME);
	}

	@Test
	public void ConstrTest() {
		Assert.assertEquals(stepTest1.getDescription(), DESCRICAO);
		Assert.assertTrue(stepTest1.getTime() == TIME);
		Assert.assertEquals(stepTest1.getAnnex(), null);
		Assert.assertEquals(stepTest1.getId(), null);
	}

	@Test
	public void toStringTest() {
		Assert.assertEquals(stepTest1.toString(), "Step [id=" + null
				+ ", description=" + DESCRICAO + ", time=" + TIME + ", annex="
				+ null + "]");
	}

	@Test
	public void isTimedTest() {
		// tempo=0
		Assert.assertFalse(stepTest1.isTimed());
		stepTest1.setTime(Integer.MAX_VALUE);
		Assert.assertTrue(stepTest1.isTimed());
	}
}
