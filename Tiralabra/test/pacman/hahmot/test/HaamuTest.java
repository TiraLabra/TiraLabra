package pacman.hahmot.test;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import pacman.alusta.Pelialusta;
import pacman.hahmot.Haamu;
import pacman.hahmot.Suunta;

public class HaamuTest {

    private Pelialusta alusta;
    private Haamu haamu;

    public HaamuTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() throws Exception {
        alusta = new Pelialusta(19, 21);
        alusta.luoPelialusta();
        haamu = new Haamu(9, 8, Suunta.ALAS, "RED", alusta);
        haamu.luoHaamuAlustalle();
    }

    @After
    public void tearDown() {
    }

    @Test
    public void haamuLuodaanOikein() {
        assertEquals(9, haamu.getX());
        assertEquals(8, haamu.getY());
        assertEquals(Suunta.ALAS, haamu.getSuunta());
        assertEquals("RED", haamu.getNimi());
        assertEquals("vahva", haamu.getTyyppi());
    }

    @Test
    public void haamuLiikkuuOikeinAlas() {
        haamu.liiku();
        assertEquals(9, haamu.getX());
        assertEquals(9, haamu.getY());
        assertTrue(alusta.getPeliruutu(9, 9).getOnkoHaamu());
        assertFalse(alusta.getPeliruutu(9, 8).getOnkoHaamu());
    }

    @Test
    public void haamuLiikkuuOikeinYlos() {
        haamu.setSuunta(Suunta.YLOS);
        haamu.liiku();
        assertEquals(Suunta.YLOS, haamu.getSuunta());
        assertEquals(9, haamu.getX());
        assertEquals(7, haamu.getY());
        assertTrue(alusta.getPeliruutu(9, 7).getOnkoHaamu());
        assertFalse(alusta.getPeliruutu(9, 8).getOnkoHaamu());
    }

    @Test
    public void haamuLiikkuuOikeinOikealla() {
        haamu.setX(7);
        haamu.setY(7);
        haamu.setSuunta(Suunta.OIKEA);
        haamu.liiku();
        assertEquals(Suunta.OIKEA, haamu.getSuunta());
        assertEquals(8, haamu.getX());
        assertEquals(7, haamu.getY());
        assertTrue(alusta.getPeliruutu(8, 7).getOnkoHaamu());
        assertFalse(alusta.getPeliruutu(7, 7).getOnkoHaamu());
    }

    @Test
    public void haamuLiikkuuOikeinVasemmalle() {
        haamu.setX(7);
        haamu.setY(7);
        haamu.setSuunta(Suunta.VASEN);
        haamu.liiku();
        assertEquals(Suunta.VASEN, haamu.getSuunta());
        assertEquals(6, haamu.getX());
        assertEquals(7, haamu.getY());
        assertTrue(alusta.getPeliruutu(6, 7).getOnkoHaamu());
        assertFalse(alusta.getPeliruutu(7, 7).getOnkoHaamu());
    }

    @Test
    public void huomioiKaikkiSuunnat() {
        haamu.setY(9);
        haamu.liiku();
        assertEquals(3, haamu.getSuuntaLista().size());
    }

    @Test
    public void palaaAlkuunOikein() {
        haamu.palaaAlkuun();
        assertEquals(9, haamu.getX());
        assertEquals(9, haamu.getY());
    }

    @Test
    public void katsooOikeinVoikoLiikkuaSivuille() {
        haamu.setX(8);
        haamu.setY(7);
        haamu.setSuunta(Suunta.OIKEA);
        assertTrue(haamu.katsoVoikoLiikkuaSivuille());
    }
}
