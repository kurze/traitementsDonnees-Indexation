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
	String stopWord = stopWordFilterCaseAccent.filter("D�");
	assertEquals(stopWord, null);
	
	stopWord = stopWordFilterCaseAccent.filter("d�");
	assertEquals(stopWord, null);
	
	stopWord = stopWordFilterCaseAccent.filter("De");
	assertEquals(stopWord, null);
	
	stopWord = stopWordFilterCaseAccent.filter("de");
	assertEquals(stopWord, null);	
    }
    
    @Test
    public void testCase() {
	String stopWord = stopWordFilterCase.filter("D�");
	assertEquals(stopWord, "d�");
	
	stopWord = stopWordFilterCase.filter("d�");
	assertEquals(stopWord, "d�");
	
	stopWord = stopWordFilterCase.filter("De");
	assertEquals(stopWord, null);
	
	stopWord = stopWordFilterCase.filter("de");
	assertEquals(stopWord, null);	
    }
    
    @Test
    public void testAccent() {
	String stopWord = stopWordFilterAccent.filter("D�");
	assertEquals(stopWord, "De");
	
	stopWord = stopWordFilterAccent.filter("d�");
	assertEquals(stopWord, null);
	
	stopWord = stopWordFilterAccent.filter("De");
	assertEquals(stopWord, "De");
	
	stopWord = stopWordFilterAccent.filter("de");
	assertEquals(stopWord, null);
    }
    
    @Test
    public void test() {
	String stopWord = stopWordFilter.filter("D�");
	assertEquals(stopWord, "D�");
	
	stopWord = stopWordFilter.filter("d�");
	assertEquals(stopWord, "d�");
	
	stopWord = stopWordFilter.filter("De");
	assertEquals(stopWord, "De");
	
	stopWord = stopWordFilter.filter("de");
	assertEquals(stopWord, null);
    }

}
