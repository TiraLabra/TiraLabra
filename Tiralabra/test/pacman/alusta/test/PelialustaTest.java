/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pacman.alusta.test;

import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import pacman.alusta.Pelialusta;

/**
 *
 * @author hhkopper
 */
public class PelialustaTest {
    private Pelialusta alusta;
    
    public PelialustaTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        alusta = new Pelialusta(19,21);
        alusta.luoPelialusta();
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void alustaLuodaanOikeanKokoiseksi() {
        assertEquals(21, alusta.getKorkeus());
        assertEquals(19, alusta.getLeveys());
    }
    
    @Test
    public void hakeeRuudunOikein() {
        assertEquals(0, alusta.getPeliruutu(0, 9).getX());
        assertEquals(9, alusta.getPeliruutu(0, 9).getY());
    }
    
    @Test
    public void eiLaitaPistepalloaMinneSeEiKuulu() {
        assertFalse(alusta.getPeliruutu(0, 7).getOnkoPallo());
        assertFalse(alusta.getPeliruutu(9, 11).getOnkoPallo());
        assertFalse(alusta.getPeliruutu(9, 9).getOnkoPallo());
        assertFalse(alusta.getPeliruutu(17, 7).getOnkoPallo());
    }
    
    @Test
    public void laittaaEkstraPistepallonOikeaanPaikkaan() {
        assertEquals(2, alusta.getPeliruutu(1, 5).getRuudunTyyppi());
        assertTrue(alusta.getPeliruutu(1, 5).getOnkoExtraPallo());
    }
    
    @Test
    public void laittaaPistepallonOikeaanPaikkaan() {
        assertEquals(1, alusta.getPeliruutu(1, 1).getRuudunTyyppi());
        assertTrue(alusta.getPeliruutu(1, 1).getOnkoPallo());
    }
    
    @Test
    public void asettaaSeinan() {
        assertEquals(0, alusta.getPeliruutu(0, 0).getRuudunTyyppi());        
    }
}
