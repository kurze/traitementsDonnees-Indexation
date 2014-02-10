package fr.univtours.polytech.di.multimedia.filters.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import fr.univtours.polytech.di.multimedia.filters.StemmingFilter;

public class StemmingFilterTest {

    private StemmingFilter stemmingFilter;
    
    @Before
    public void setUp() throws Exception {
	stemmingFilter = new StemmingFilter();
    }

    @Test
    public void test() {
	//Test d'un mot n'ayant pas de suffixe à traiter
	assertTrue(stemmingFilter.filter("je").compareTo("je") == 0);
	
	//Test d'un mot ayant un suffixe à traiter
	String stemm = stemmingFilter.filter("technologique");
	assertEquals(stemm, "technolog");
	
	//Test du singulier et du pluriel
	stemm = stemmingFilter.filter("créatrice");
	assertEquals(stemm, "cré");
	
	stemm = stemmingFilter.filter("créatrices");
	assertEquals(stemm, "cré");
	
	//On teste que le fait de contenir "atrices" ne change rien au mot
	stemm = stemmingFilter.filter("cccccatricesccc");
	assertEquals(stemm, "cccccatricesccc");
    }

}
