package fr.univtours.polytech.di.multimedia.test;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import fr.univtours.polytech.di.multimedia.signextractors.test.*;

@RunWith(Suite.class)
@SuiteClasses({TestWordExtractor.class, TestNGramExtractor.class})
public class AllTests {
}
