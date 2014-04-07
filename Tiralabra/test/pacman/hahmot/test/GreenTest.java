/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pacman.hahmot.test;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import pacman.alusta.Pelialusta;
import pacman.hahmot.Green;
import pacman.hahmot.Man;
import pacman.hahmot.Suunta;

/**
 *
 * @author Hanna
 */
public class GreenTest {
    private Green green;
    private Pelialusta alusta;
    
    public GreenTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        alusta = new Pelialusta(19, 21);
        alusta.luoPelialusta();
        this.green = new Green(9, 8, Suunta.ALAS, "green", alusta);
        this.green.luoHaamuAlustalle();
    }
    
    @After
    public void tearDown() {
    }

   @Test
    public void GreenLuodaanOikein() {
        assertEquals(9, green.getX());
        assertEquals(8, green.getY());
        assertEquals(Suunta.ALAS, green.getSuunta());
        assertEquals("green", green.getNimi());
        assertEquals("vahva", green.getTyyppi());
    }
    
    @Test
    public void haamuLiikkuuOikeinAlas() {
        green.liiku();
        assertEquals(9, green.getX());
        assertEquals(9, green.getY());
        assertTrue(alusta.getPeliruutu(9, 9).getOnkoHaamu());
        assertFalse(alusta.getPeliruutu(9, 8).getOnkoHaamu());
    }

    @Test
    public void haamuLiikkuuOikeinYlos() {
        green.setSuunta(Suunta.YLOS);
        green.liiku();
        assertEquals(Suunta.YLOS, green.getSuunta());
        assertEquals(9, green.getX());
        assertEquals(7, green.getY());
        assertTrue(alusta.getPeliruutu(9, 7).getOnkoHaamu());
        assertFalse(alusta.getPeliruutu(9, 8).getOnkoHaamu());
    }

    @Test
    public void haamuLiikkuuOikeinOikealla() {
        green.setX(7);
        green.setY(7);
        green.setSuunta(Suunta.OIKEA);
        green.liiku();
        assertEquals(Suunta.OIKEA, green.getSuunta());
        assertEquals(8, green.getX());
        assertEquals(7, green.getY());
        assertTrue(alusta.getPeliruutu(8, 7).getOnkoHaamu());
        assertFalse(alusta.getPeliruutu(7, 7).getOnkoHaamu());
    }

    @Test
    public void haamuLiikkuuOikeinVasemmalle() {
        green.setX(7);
        green.setY(7);
        green.setSuunta(Suunta.VASEN);
        green.liiku();
        assertEquals(Suunta.VASEN, green.getSuunta());
        assertEquals(6, green.getX());
        assertEquals(7, green.getY());
        assertTrue(alusta.getPeliruutu(6, 7).getOnkoHaamu());
        assertFalse(alusta.getPeliruutu(7, 7).getOnkoHaamu());
    }

    @Test
    public void huomioiKaikkiSuunnat() {
        green.setY(9);
        green.liiku();
        assertEquals(3, green.getSuuntaLista().koko());
    }
    
    @Test
    public void liikkuuOikeinKunVastassaSeina() {
        green.setX(10);
        green.setY(9);
        green.setSuunta(Suunta.OIKEA);
        green.liiku();
        assertEquals(9, green.getX());
        assertEquals(9, green.getY());
    }
}
