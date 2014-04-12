/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pacman.peli.test;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import pacman.peli.HaamujenKasittelija;
import pacman.peli.Pacman;
import pacman.tietorakenteet.AStar;

/**
 *
 * @author Hanna
 */
public class HaamujenKasittelijaTest {

    private HaamujenKasittelija kasittelija;
    private Pacman peli;
    private AStar haku;

    public HaamujenKasittelijaTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        peli = new Pacman();
        kasittelija = peli.getKasittelija();
    }

    @After
    public void tearDown() {
    }

    @Test
    public void asetetaankoHaamuHeikoksi() {
        kasittelija.heikennaHaamut();
        assertEquals("heikko", kasittelija.getCyan().getTyyppi());
        assertEquals("heikko", kasittelija.getGreen().getTyyppi());
        assertEquals("heikko", kasittelija.getMagenta().getTyyppi());
        assertEquals("heikko", kasittelija.getRed().getTyyppi());

    }
    
    @Test
    public void asetetaanHaamuVahvaksi() {
        kasittelija.heikennaHaamut();
        kasittelija.getGreen().setHeikkous(0);
        kasittelija.getCyan().setHeikkous(0);
        kasittelija.getMagenta().setHeikkous(0);
        kasittelija.getRed().setHeikkous(0);
        kasittelija.liikutaHaamut();
        assertEquals("vahva", kasittelija.getCyan().getTyyppi());
        assertEquals("vahva", kasittelija.getGreen().getTyyppi());
        assertEquals("vahva", kasittelija.getMagenta().getTyyppi());
        assertEquals("vahva", kasittelija.getRed().getTyyppi());
    }
    
    @Test
    public void kertooOikeinHeikkojenMaaran() {
        kasittelija.heikennaHaamut();
        assertEquals(4, kasittelija.tarkistaOnkoHeikkoja());
        kasittelija.getGreen().setTyyppi("vahva");
        assertEquals(3, kasittelija.tarkistaOnkoHeikkoja());
    }
    
    @Test
    public void liikuttaaOikeinVahvaRed() {
        assertEquals(9, kasittelija.getRed().getX());
        assertEquals(7, kasittelija.getRed().getY());
        assertTrue(peli.getAlusta().getPeliruutu(9, 7).getOnkoHaamu());
        kasittelija.liikutaHaamut();
        assertEquals(8, kasittelija.getRed().getX());
        assertEquals(7, kasittelija.getRed().getY());
        assertTrue(peli.getAlusta().getPeliruutu(8, 7).getOnkoHaamu());
        
    }
    
    @Test
    public void liikuttaaOikeinVahvaGreen() {
        assertEquals(10, kasittelija.getGreen().getX());
        assertEquals(9, kasittelija.getGreen().getY());
        assertTrue(peli.getAlusta().getPeliruutu(10, 9).getOnkoHaamu());
        kasittelija.liikutaHaamut();
        assertEquals(9, kasittelija.getGreen().getX());
        assertEquals(9, kasittelija.getGreen().getY());
    }
    
    @Test
    public void liikuttaaOikeinVahvaCyan() {
        
    }
    
    @Test
    public void liikuttaaOikeinVahvaMagenta() {
        
    }
    
    @Test
    public void liikuttaaOikeinHeikkoRed() {
        
    }
    
    @Test
    public void liikuttaaOikeinHeikkoGreen() {
        
    }
    
    @Test
    public void liikuttaaOikeinHeikkoMagenta() {
        
    }
    
    @Test
    public void liikuttaaOikeinHeikkoCyan() {
        
    }
}
