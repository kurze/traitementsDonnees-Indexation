package fr.univtours.polytech.di.multimedia.signextractors.test;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import fr.univtours.polytech.di.multimedia.signextractors.WordExtractor;

public class TestWordExtractor {

    private WordExtractor WE;
    
    @Before
    public void setUp() throws Exception {
	WE = new WordExtractor();
    }

    @Test
    public void test_WordExtractor() {
	String str = "Ceci est un test de 39dé.";
	WE.setContent(str);
	Assert.assertEquals("0", "Ceci", WE.nextToken());
	Assert.assertEquals("1", "est", WE.nextToken());
	Assert.assertEquals("2", "un", WE.nextToken());
	Assert.assertEquals("3", "test", WE.nextToken());
	Assert.assertEquals("4", "de", WE.nextToken());
	Assert.assertEquals("5", "39dé", WE.nextToken());
	Assert.assertEquals("6", null, WE.nextToken());
    }
}
