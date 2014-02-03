package fr.univtours.polytech.di.multimedia.filters.test;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import fr.univtours.polytech.di.multimedia.filters.AccentFilter;

public class AccentFilterTest {
	
	private AccentFilter AF;

	@Before
	public void setUp() throws Exception {
		AF = new AccentFilter();
	}

	@Test
	public void test_AccentFilter_Base() {
		String str = "Ceci est un test.";
		Assert.assertEquals("base", "Ceci est un test.", AF.filter(str));
	}
	
	@Test
	public void test_AccentFilter_Accent() {
		String str = "Cècï ést ùn tëst.";
		Assert.assertEquals("accent", "Ceci est un test.", AF.filter(str));
	}
	
	@Test
	public void test_AccentFilter_Special() {
		String str = "Ceçi est un test.";
		Assert.assertEquals("special", "Ceci est un test.", AF.filter(str));
	}

}
