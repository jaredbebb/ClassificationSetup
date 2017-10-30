package test;

import lin.Levenshtein;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class LevenshteinTest {
    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void compute() throws Exception {
        String current = "";
        String compareTo="";

        Levenshtein leven = new Levenshtein();
        current = "ca";
        compareTo = "catfish";
        int difference = leven.compute(current, compareTo);
        Assert.assertEquals(5,difference);

        leven = new Levenshtein();
        current = "ca";
        compareTo = "cat";
        difference = leven.compute(current, compareTo);
        Assert.assertEquals(1,difference);

        leven = new Levenshtein();
        current = "size";
        compareTo = "seize";
        difference = leven.compute(current, compareTo);
        Assert.assertEquals(1,difference);

        leven = new Levenshtein();
        current = "BUTTON";
        compareTo = "BUTTON";
        difference = leven.compute(current, compareTo);
        Assert.assertEquals(0,difference);

        current = "ANT";
        compareTo = "CANT";
        difference = leven.compute(current, compareTo);
        Assert.assertEquals(1,difference);

        current = " ";
        compareTo = "RUNNING";
        difference = leven.compute(current, compareTo);
        Assert.assertEquals(7,difference);

        current = " E ";
        compareTo = "SCREECH";
        difference = leven.compute(current, compareTo);
        Assert.assertEquals(5,difference);
    }

}