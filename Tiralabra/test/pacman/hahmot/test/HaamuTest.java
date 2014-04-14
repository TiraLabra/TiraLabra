package pacman.hahmot.test;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import pacman.alusta.Pelialusta;
import pacman.hahmot.Haamu;
import pacman.hahmot.Red;
import pacman.hahmot.Suunta;
import pacman.tietorakenteet.Lista;

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
        haamu = new Red(9, 8, Suunta.ALAS, "RED", alusta);
        haamu.luoHaamuAlustalle();
    }

    @After
    public void tearDown() {
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

    @Test
    public void lisaaOikeatKielletytRuudut() {
        Lista kielruu = haamu.getKielletyt();
        
        assertEquals(16, kielruu.koko());
        assertTrue(kielruu.sisaltaa(alusta.getPeliruutu(0, 7)));
        assertTrue(kielruu.sisaltaa(alusta.getPeliruutu(1, 11)));
        assertTrue(kielruu.sisaltaa(alusta.getPeliruutu(17, 7)));
        assertTrue(kielruu.sisaltaa(alusta.getPeliruutu(16, 11)));
        assertTrue(kielruu.sisaltaa(alusta.getPeliruutu(9, 9)));
        
    }
    
    @Test
    public void tarkistaaOikeinOnkoHuonoRuutu() {
        assertTrue(haamu.tarkistaOnkoHuonoRuutu(-1, -100));
        assertFalse(haamu.tarkistaOnkoHuonoRuutu(9, 11));
        assertTrue(haamu.tarkistaOnkoHuonoRuutu(18, 19));
        assertTrue(haamu.tarkistaOnkoHuonoRuutu(17, 7));
    }
    
    @Test
    public void liikkuuOikeinAnnettuunRuutuun() {
        assertEquals(9, haamu.getX());
        assertEquals(8, haamu.getY());
        assertTrue(alusta.getPeliruutu(9, 8).getOnkoHaamu());
        haamu.liiku(alusta.getPeliruutu(9, 9));
        assertEquals(9, haamu.getX());
        assertEquals(9, haamu.getY());
        assertTrue(alusta.getPeliruutu(9, 9).getOnkoHaamu());
        assertFalse(alusta.getPeliruutu(9, 8).getOnkoHaamu());
        
    }
}
