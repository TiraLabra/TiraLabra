/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Algoritmi;

import Algoritmi.Solmu;
import Algoritmi.Reitinhaku;
import Käyttöliittymä.Kuva;
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
    
    public ReitinhakuTest() {
    }
    
    @BeforeClass
    public static void setUpClass() throws IOException, URISyntaxException {
        Kuva ikkuna = new Kuva();
        
        Point maali = new Point(500,500);
        Point lähtö = new Point(50,50);
        
        Verkko testiverkko=new Verkko(ikkuna.kuva, maali);
        testi = new Reitinhaku(testiverkko, lähtö);
        
        
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of Haku method, of class Reitinhaku.
     */
    @Test
    public void testHaku() {

        testi.Haku();
        
        
        
        assertTrue(true);
        
        
    }

    /**
     * Test of TarkistaViereiset method, of class Reitinhaku.
     */
    @Test
    public void testTarkistaViereiset() {
        
        
    }

    /**
     * Test of Lisää method, of class Reitinhaku.
     */
    @Test
    public void testLisää() {

    }

    /**
     * Test of tulosta method, of class Reitinhaku.
     */
    @Test
    public void testTulosta() {

    }
    
}
