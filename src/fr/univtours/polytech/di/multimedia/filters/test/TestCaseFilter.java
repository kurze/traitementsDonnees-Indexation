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
		String str = "Ceci Est Un TEST Avec DES Accents ÉÀÙ.";
		Assert.assertEquals("Accent", "ceci est un test avec des accents éàù.", CF.filter(str));
	}
	
	public void test_CaseFilter_Special() {
		String str = "Ceci Est Un TEST Avec DES CARATÈRES SPÉCIAUX ÆÐ.";
		Assert.assertEquals("Special", "ceci est un test avec des caratères spéciaux æð.", CF.filter(str));
	}

}
