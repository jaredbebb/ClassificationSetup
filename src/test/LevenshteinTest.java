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
    public void printMatrix() throws Exception {
        Levenshtein leven = new Levenshtein();
        leven = new Levenshtein();
        String current = "size";
        String compareTo = "seize";
        int difference = leven.compute(current, compareTo);
        Assert.assertEquals(1,difference);
        leven.printMatrix();
    }

    @Test
    public void min() throws Exception {
        Levenshtein leven = new Levenshtein();
        Assert.assertEquals(-1,leven.min(2,-1, 400));
        Assert.assertEquals(2,leven.min(2,400,5));
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