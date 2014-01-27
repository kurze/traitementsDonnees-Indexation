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
    public void test() {
	String str = "Ceci est un test";
	WE.setContent(str);
	Assert.assertEquals("ahah", "Ceci", WE.nextToken());
    }

}
