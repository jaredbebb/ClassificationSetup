package test;

import lin.StringFormat;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class StringFormatTest {
    String line1;
    String line1Punc;
    @Before
    public void setUp() throws Exception {
        line1 = "EASIER FOR ME TO PURCHASE FROM AMAZON " +
                "THAN TO GO OUT TO THE STORES I AM SATISFIED THEY FIT MY VACUUM PERFECTLY";
        line1Punc = "EA_SIER F.OR ME TO PURCHA-SE FROM AMAZON " +
                "THAN ...TO GO OUT TO THE STORES I AM SATI;SFIED THEY FIT MY VACUUM PERFECTLY";
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void removePunctuation() throws Exception {
        StringFormat sf = new StringFormat(line1);
        sf.RemovePunctuation();
        Assert.assertEquals("EASIER FOR ME TO PURCHASE FROM AMAZON " +
                "THAN TO GO OUT TO THE STORES I AM SATISFIED THEY FIT MY VACUUM PERFECTLY", sf.Get());
        StringFormat sf1 = new StringFormat(line1Punc);
        sf1.RemovePunctuation();
        Assert.assertEquals("EASIER FOR ME TO PURCHASE FROM AMAZON " +
                "THAN TO GO OUT TO THE STORES I AM SATISFIED THEY FIT MY VACUUM PERFECTLY", sf1.Get());
    }

    @Test
    public void removeStopWords() throws Exception {
        StringFormat sf = new StringFormat(line1);
        sf.RemoveStopWords();
        Assert.assertEquals("EASIER PURCHASE AMAZON " +
                "GO STORES SATISFIED FIT VACUUM PERFECTLY", sf.Get());
    }

    @Test
    public void get() throws Exception {
        StringFormat sf = new StringFormat(line1);
        Assert.assertEquals("EASIER FOR ME TO PURCHASE FROM AMAZON " +
                "THAN TO GO OUT TO THE STORES I AM SATISFIED THEY FIT MY VACUUM PERFECTLY", sf.Get());
    }

    @Test
    public void stem() throws Exception {
        String running = "running";
        String ran = "ran";
        String ACCIDENTALLY = "ACCIDENTALLY".toLowerCase();
        StringFormat sf = new StringFormat();
        Assert.assertEquals("RUN",sf.stem(running));
        Assert.assertEquals("BROWN",sf.stem("BROWNED"));
        Assert.assertEquals("CLEANER",sf.stem("CLEANER"));
        Assert.assertEquals("ACCIDENT",sf.stem(ACCIDENTALLY));
        Assert.assertEquals("CAMP",sf.stem("CAMPING"));
    }

}