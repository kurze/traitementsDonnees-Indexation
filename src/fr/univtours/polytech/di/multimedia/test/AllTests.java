package fr.univtours.polytech.di.multimedia.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import fr.univtours.polytech.di.multimedia.filters.test.*;
import fr.univtours.polytech.di.multimedia.signextractors.test.*;

@RunWith(Suite.class)
@SuiteClasses({
	TestWordExtractor.class, 
	TestNGramExtractor.class,
	TestCaseFilter.class,
	AccentFilterTest.class
	})
public class AllTests {
}
