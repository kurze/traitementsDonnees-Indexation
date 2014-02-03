package fr.univtours.polytech.di.multimedia.filters.test;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import fr.univtours.polytech.di.multimedia.filters.CaseFilter;

public class TestCaseFilter {

	private CaseFilter CF;
	
	@Before
    public void setUp() throws Exception {
		CF = new CaseFilter();
    }
	
	@Test
	public void test_CaseFilter_Normal() {
		String str = "Ceci Est Un TEST.";
		Assert.assertEquals("normal", "ceci est un test.", CF.filter(str));
	}
	
	@Test
	public void test_CaseFilter_Accent() {
		String str = "Ceci Est Un TEST Avec DES Accents ���.";
		Assert.assertEquals("Accent", "ceci est un test avec des accents ���.", CF.filter(str));
	}
	
	public void test_CaseFilter_Special() {
		String str = "Ceci Est Un TEST Avec DES CARAT�RES SP�CIAUX ��.";
		Assert.assertEquals("Special", "ceci est un test avec des carat�res sp�ciaux ��.", CF.filter(str));
	}

}
