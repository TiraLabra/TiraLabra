package sovelluslogiikka;

//import org.junit.After;
//import org.junit.AfterClass;
import org.junit.Before;
//import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class LaskinTest {

    Laskin laskin;

//    public LaskinTest() {
//    }
//    @BeforeClass
//    public static void setUpClass() {
//    }
//    @AfterClass
//    public static void tearDownClass() {
//    }
//    @After
//    public void tearDown() {
//    }
    @Before
    public void setUp() {
        laskin = new Laskin();
    }

    @Test
    public void yhteenlaskuToimiiOikein() {
        assertEquals(3, laskin.ratkaiseLaskutoimitus("1+2"));
    }

    @Test
    public void vahennyslaskuToimiiOikein() {
        assertEquals(-4, laskin.ratkaiseLaskutoimitus("10-14"));
    }

    @Test
    public void kertolaskuToimiiOikein() {
        assertEquals(20, laskin.ratkaiseLaskutoimitus("4*5"));
    }

    @Test
    public void jakolaskuToimiiOikein() {
        assertEquals(5, laskin.ratkaiseLaskutoimitus("100/20"));
    }
}