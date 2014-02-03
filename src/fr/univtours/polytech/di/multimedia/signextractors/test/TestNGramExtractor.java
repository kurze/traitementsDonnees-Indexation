package fr.univtours.polytech.di.multimedia.signextractors.test;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import fr.univtours.polytech.di.multimedia.signextractors.NGramExtractor;

public class TestNGramExtractor {
    private NGramExtractor NE;
    
    @Before
    public void setUp() throws Exception {
	
    }

    @Test
    public void test_NGramExtractor_n3() {
    	NE = new NGramExtractor(3);
		String str = "Ceci est un test de 39dé.";
		NE.setContent(str);
		Assert.assertEquals("0", "Cec", NE.nextToken());
		Assert.assertEquals("1", "eci", NE.nextToken());
		Assert.assertEquals("2", "est", NE.nextToken());
		Assert.assertEquals("3", "tes", NE.nextToken());
		Assert.assertEquals("4", "est", NE.nextToken());
		Assert.assertEquals("5", "39d", NE.nextToken());
		Assert.assertEquals("6", "9dé", NE.nextToken());
		Assert.assertEquals("7", null, NE.nextToken());
    }
    
    @Test
    public void test_NGramExtractor_n4() {
    	NE = new NGramExtractor(4);
		String str = "Ceci est un test de 39dé motSuper";
		NE.setContent(str);
		Assert.assertEquals("0", "Ceci", NE.nextToken());
		Assert.assertEquals("1", "test", NE.nextToken());
		Assert.assertEquals("2", "39dé", NE.nextToken());
		Assert.assertEquals("3", "motS", NE.nextToken());
		Assert.assertEquals("4", "otSu", NE.nextToken());
		Assert.assertEquals("5", "tSup", NE.nextToken());
		Assert.assertEquals("6", "Supe", NE.nextToken());
		Assert.assertEquals("7", "uper", NE.nextToken());
		Assert.assertEquals("8", null, NE.nextToken());
    }
    
    @Test
    public void test_NGramExtractor_n5() {
    	NE = new NGramExtractor(5);
		String str = "Ceci est un test de 39dé motSuper";
		NE.setContent(str);
		Assert.assertEquals("0", "motSu", NE.nextToken());
		Assert.assertEquals("1", "otSup", NE.nextToken());
		Assert.assertEquals("2", "tSupe", NE.nextToken());
		Assert.assertEquals("3", "Super", NE.nextToken());
		Assert.assertEquals("4", null, NE.nextToken());
    }
}