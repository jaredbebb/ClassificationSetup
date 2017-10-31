package test;

import lin.SpellCheck;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class SpellCheckTest {
    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void change() throws Exception {
        SpellCheck sp = new SpellCheck();
        String changed = sp.change("DOGG");
        Assert.assertEquals("DOG", changed);
    }
}