package test;

import file.StopWords;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class StopWordsTest {
    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void load() throws Exception {
        StopWords sw = new StopWords();
        sw.Load();
    }

    @Test
    public void check() throws Exception {
    }

    @Test
    public void get() throws Exception {
    }

}