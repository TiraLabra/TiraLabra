/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Algoritmi;


import Käyttöliittymä.Kuva;
import java.awt.Color;
import java.awt.Point;
import java.io.IOException;
import java.net.URISyntaxException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Jaakko
 */
public class ReitinhakuTest {
    
    static Reitinhaku testi;
    static Reitinhaku testi2;
    static Kuva ikkuna;
    
    public ReitinhakuTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {

    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        ikkuna = new Kuva();
        
        Point maali = new Point(500,500);
        Point lähtö = new Point(500,5);
        Point lähtö2 = new Point(3,3);
        
        Verkko testiverkko=new Verkko(ikkuna.kuva, maali);
        testi = new Reitinhaku(testiverkko, lähtö, 60000);
        testi2 = new Reitinhaku(testiverkko, lähtö2, 60000);        
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of Haku method, of class Reitinhaku.
     */
    @Test
    public void testHaku() {

        
        assertTrue(testi.Haku());
        
        assertFalse(testi2.Haku());
        
        
        Color c = new Color(ikkuna.kuva.getRGB(500, 200));
        assertEquals(255, c.getBlue());
        
        
    }

    /**
     * Test of TarkistaViereiset method, of class Reitinhaku.
     * 
     * Test of Lisää method, of class Reitinhaku.
     */
    @Test
    public void testTarkistaViereisetlaLisää() {
        
        Solmu sss=new Solmu(501, 6, 15, 255);
        
        testi.TarkistaViereiset(sss);
        
        assertEquals(testi.verkko.taulukko[500][5].Edeltävä, testi.verkko.taulukko[500][5]);
        assertEquals(testi.verkko.taulukko[501][4].Edeltävä, null);

        assertEquals(testi.verkko.taulukko[502][6].Edeltävä, sss);
        
    }

    /**
     * Test of Lisää method, of class Reitinhaku.
     */
    @Test
    public void testLisää() {
        
        Solmu sss=new Solmu(600, 600, 15, 255);
        
        testi.Lisää(600, 601, sss);
        
        assertEquals(testi.verkko.taulukko[600][601].Reittipituus, 1);
        assertEquals(testi.verkko.taulukko[600][601].Edeltävä, sss);

    }

    /**
     * Test of PiirräReitti method, of class Reitinhaku.
     */
    @Test
    public void PiirräReitti() {

        Color c = new Color(ikkuna.kuva.getRGB(500, 100));
        assertEquals(c.getBlue(), 255);
        
    }
    
}
