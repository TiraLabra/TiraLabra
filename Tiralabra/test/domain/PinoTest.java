package domain;

//import org.junit.After;
//import org.junit.AfterClass;
import org.junit.Before;
//import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class PinoTest {

    Pino pino;

//    public PinoTest() {
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
        pino = new Pino(10);
    }

    @Test
    public void konstruktoriAsettaaPinonKoonJaHuipunViitteenOikein() {
        assertEquals(10, pino.getSize());
        assertEquals(-1, pino.getTop());
    }

    @Test
    public void pushAsettaaHuipunViitteenJaPinonPaallimaisenOlionOikein() {
        pino.push("lisättävä olio");
        assertEquals(0, pino.getTop());
        assertEquals("lisättävä olio", pino.peek());
    }

    @Test
    public void popPalauttaaPinonPaallimaisenOlionJaAsettaaHuipunViitteenOikeinYhdenPushinJalkeen() {
        pino.push("lisättävä olio");
        assertEquals("lisättävä olio", pino.pop());
        assertEquals(-1, pino.getTop());
    }

    @Test
    public void pinoOnTyhjaKunSiihenEiOleLisattyYhtakaanOliota() {
        assertEquals(true, pino.empty());
    }

    @Test
    public void pinossaOnYksiOlioYhdenPushinJalkeen() {
        pino.push("lisättävä olio");
        assertEquals(1, pino.getOlioidenLkm());
    }
}