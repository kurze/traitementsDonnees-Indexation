package fr.univtours.polytech.di.multimedia.filters.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import fr.univtours.polytech.di.multimedia.filters.StopWordFilter;

public class StopWordFilterTest {

    private StopWordFilter stopWordFilterCaseAccent;
    private StopWordFilter stopWordFilterCase;
    private StopWordFilter stopWordFilterAccent;
    private StopWordFilter stopWordFilter;
    
    @Before
    public void setUp() throws Exception {
	stopWordFilterCaseAccent = new StopWordFilter(true, true);
	stopWordFilterCase = new StopWordFilter(true, false);
	stopWordFilterAccent = new StopWordFilter(false, true);
	stopWordFilter = new StopWordFilter(false, false);
    }

    @Test
    public void testCaseAccent() {
	String stopWord = stopWordFilterCaseAccent.filter("Dé");
	assertEquals(stopWord, null);
	
	stopWord = stopWordFilterCaseAccent.filter("dé");
	assertEquals(stopWord, null);
	
	stopWord = stopWordFilterCaseAccent.filter("De");
	assertEquals(stopWord, null);
	
	stopWord = stopWordFilterCaseAccent.filter("de");
	assertEquals(stopWord, null);	
    }
    
    @Test
    public void testCase() {
	String stopWord = stopWordFilterCase.filter("Dé");
	assertEquals(stopWord, "dé");
	
	stopWord = stopWordFilterCase.filter("dé");
	assertEquals(stopWord, "dé");
	
	stopWord = stopWordFilterCase.filter("De");
	assertEquals(stopWord, null);
	
	stopWord = stopWordFilterCase.filter("de");
	assertEquals(stopWord, null);	
    }
    
    @Test
    public void testAccent() {
	String stopWord = stopWordFilterAccent.filter("Dé");
	assertEquals(stopWord, "De");
	
	stopWord = stopWordFilterAccent.filter("dé");
	assertEquals(stopWord, null);
	
	stopWord = stopWordFilterAccent.filter("De");
	assertEquals(stopWord, "De");
	
	stopWord = stopWordFilterAccent.filter("de");
	assertEquals(stopWord, null);
    }
    
    @Test
    public void test() {
	String stopWord = stopWordFilter.filter("Dé");
	assertEquals(stopWord, "Dé");
	
	stopWord = stopWordFilter.filter("dé");
	assertEquals(stopWord, "dé");
	
	stopWord = stopWordFilter.filter("De");
	assertEquals(stopWord, "De");
	
	stopWord = stopWordFilter.filter("de");
	assertEquals(stopWord, null);
    }

}
