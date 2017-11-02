package test;

import file.Reading;
import org.junit.*;

import static org.junit.Assert.*;

public class ReadingTest {
    private final static String workingDirectory = System.getProperty("user.dir");
    private String line1 = "  THE DOg ... JUMPED in  the   pond  .";
    private String line2 = "APYOBQE6M18AA,MY DAUGHTER WANTED BOOK PRICE AMAZON BEST SHE HAPPY,PERFECT";
    private String line3 = "A1JVQTAGHYOL7F,I BOUGHT MY DAUGHTERR SHE LOVES FUN MAKE HER ICE CREAM,PERFECT";
    private Reading r;
    private String fileLong;
    private String fileShort;

    @Before
    public void setUp() throws Exception {
        fileShort = workingDirectory + "\\src\\data\\short.csv";
        r = new Reading(fileShort);
        fileLong = workingDirectory + "\\src\\data\\home_and_kitchen.csv";
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void buildDictionary() throws Exception {
    }

    @Test
    public void buildDictionary1() throws Exception {
        String fileShort = workingDirectory + "\\src\\data\\short.csv";
        Reading re = new Reading(fileShort);
        re.buildDictionary(true);
    }

    @Test
    public void getText1() throws Exception {
        String fileShort = workingDirectory + "\\src\\data\\short.csv";
        Reading re = new Reading(fileShort);
        re.buildDictionary(true);
        String text = re.getText("APYOBQE6M18AA,MY DAUGHTER WANTED BOOK PRICE AMAZON BEST SHE HAPPY,PERFECT", 1,true);
        assertEquals("DAUGHTER WANT BOOK PRICE AMAZON BEST HAPPI", text);
    }

    @Test
    public void getText2() throws Exception {

    }
    @Test
    public void readLine() throws Exception {
        String fileShort = workingDirectory + "\\src\\data\\short.csv";
        Reading re = new Reading(fileShort);
        re.buildDictionary();
        re.getDict().printList();
        Assert.assertEquals(18, re.getDict().getList().length);
        String csv = re.readLine(line2);
        //"AMAZON BEST BOOK BOUGHT CREAM DAUGHTER DAUGHTERR FUN HAPPY HER I ICE LOVES MAKE MY PRICE SHE WANTED"
        //"APYOBQE6M18AA,MY DAUGHTER WANTED BOOK PRICE AMAZON BEST SHE HAPPY,PERFECT"
        Assert.assertEquals("APYOBQE6M18AA,TRUE,TRUE,TRUE,FALSE,FALSE," +
                "TRUE,FALSE,FALSE,TRUE,FALSE,FALSE,FALSE,FALSE,FALSE,TRUE,TRUE,TRUE,TRUE,PERFECT", csv);

        //"AMAZON BEST BOOK BOUGHT CREAM DAUGHTER DAUGHTERR FUN HAPPY HER I ICE LOVES MAKE MY PRICE SHE WANTED"
        //"I BOUGHT MY DAUGHTERR SHE LOVES FUN MAKE HER ICE CREAM"
        String csv1 = re.readLine(line3);
        Assert.assertEquals("A1JVQTAGHYOL7F,FALSE,FALSE,FALSE,TRUE,TRUE," +
                "FALSE,TRUE,TRUE,FALSE,TRUE,TRUE,TRUE,TRUE,TRUE,TRUE,FALSE,TRUE,FALSE,PERFECT", csv1);
        String text2 = r.readLine("A3S3R88HA0HZG3,,PERFECT");
        Assert.assertEquals(null, text2);
    }

    @Test
    public void readLine1() throws Exception {
        String fileShort = fileLong;
        Reading re1 = new Reading(fileShort);
        re1.buildDictionary();
        re1.getDict().printList();
        Assert.assertEquals(7773, re1.getDict().getList().length);
        String csv = re1.readLine(line2);
    }
    @Test
    public void readLine2() throws Exception {
        String fileShort = workingDirectory + "\\src\\data\\short.csv";
        Reading re = new Reading(fileShort);
        re.buildDictionary(true);
        re.getDict().printList();
        Assert.assertEquals(14, re.getDict().getList().length);

        String line2 = "APYOBQE6M18AA,MY DAUGHTER WANTED BOOK PRICE AMAZON BEST SHE HAPPY,PERFECT";
        String csv = re.readLine(line2,true);
        Assert.assertEquals("APYOBQE6M18AA,TRUE,TRUE,TRUE,FALSE,FALSE," +
                "TRUE,FALSE,FALSE,TRUE,FALSE,FALSE,FALSE,TRUE,TRUE,PERFECT", csv);

        String line3 = "A1JVQTAGHYOL7F,I BOUGHT MY DAUGHTERR SHE LOVES FUN MAKE HER ICE CREAM,PERFECT";
        String csv1 = re.readLine(line3,true);
        Assert.assertEquals("A1JVQTAGHYOL7F,FALSE,FALSE,FALSE,TRUE,TRUE,FALSE,TRUE,TRUE,FALSE,TRUE,TRUE,TRUE,FALSE,FALSE,PERFECT", csv1);
    }

    @Test
    public void format() throws Exception {
        Reading r = new Reading(fileShort);
        String out = r.format(line1);
        Assert.assertEquals("THE DOG JUMPED IN THE POND", out);
    }

    @Test
    public void getText() throws Exception {
        String text = r.getText("APYOBQE6M18AA,MY DAUGHTER WANTED BOOK PRICE AMAZON BEST SHE HAPPY,PERFECT", 1);
        assertEquals("MY DAUGHTER WANTED BOOK PRICE AMAZON BEST SHE HAPPY", text);

        String text1 = r.getText("A1JVQTAGHYOL7F,I BOUGHT MY DAUGHTERR SHE LOVES FUN MAKE HER ICE CREAM,PERFECT", 1);
        assertEquals("I BOUGHT MY DAUGHTERR SHE LOVES FUN MAKE HER ICE CREAM", text1);

        String text2 = r.getText("A3S3R88HA0HZG3,,PERFECT", 1);
        assertEquals("", text2);
    }

    @Test
    public void printLines() throws Exception {
        String fileShort = workingDirectory + "\\src\\data\\short.csv";
        Reading re = new Reading(fileShort);
        re.buildDictionary();
        re.printLines();
    }

    @Ignore
    @Test
    public void printLines1() throws Exception {
        Reading re = new Reading(fileLong);
        re.buildDictionary();
        re.printLines();
    }

    @Test
    public void writeLines() throws Exception {
        String out = workingDirectory + "\\src\\data\\out.csv";
        Reading re = new Reading(fileShort);
        re.buildDictionary();
        System.out.println(re.getDict().getHeaders());
        re.writeLines(out);
    }

    @Test
    public void writeLines1() throws Exception {
        String out = workingDirectory + "\\src\\data\\out.csv";
        Reading re = new Reading(fileLong);
        re.buildDictionary();
        re.writeLines(out);
    }
    @Test
    public void writeLines2() throws Exception {
        String out = workingDirectory + "\\src\\data\\out.csv";
        Reading re = new Reading(fileLong);
        re.buildDictionary(true);
        re.writeLines(out,true);
    }

    @Test
    public void stahhp() throws Exception {
    }
}