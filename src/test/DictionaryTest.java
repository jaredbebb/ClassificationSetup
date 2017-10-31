package test;

import lin.Dictionary;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.lang.management.MemoryUsage;

import static org.junit.Assert.*;

public class DictionaryTest {
    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void load() throws Exception {
    }

    @Test
    public void index() throws Exception {
        Dictionary dict = new Dictionary();
        dict.index();
    }

    @Test
    public void size() throws Exception {
        Dictionary dict = new Dictionary();
        Assert.assertEquals(370099, dict.size());
    }

    @Test
    public void check() throws Exception {
        Dictionary d = new Dictionary();
        Assert.assertEquals(true,d.check("APPLE"));
        Assert.assertEquals(true,d.check("COOKER"));
    }

    @Test
    public void get() throws Exception {
    }

}