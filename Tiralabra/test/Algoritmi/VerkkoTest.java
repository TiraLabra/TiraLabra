/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Algoritmi;

import Käyttöliittymä.Kuva;
import java.awt.Point;
import java.io.IOException;
import java.net.URISyntaxException;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Jaakko
 */
public class VerkkoTest {
    
    static Verkko testi;
    
    public VerkkoTest() {
    }
    
    @BeforeClass
    public static void setUpClass() throws IOException, URISyntaxException {
        Kuva ikkuna = new Kuva();
        
        Point p1 = new Point(5,5);
        testi=new Verkko(ikkuna.kuva, p1);
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

    
    @Test
    public void testKonstruktori(){
        
        assertEquals(testi.taulukko.length, testi.kuva.getHeight());
        assertEquals(testi.taulukko[0].length, testi.kuva.getWidth());
        
    }
    
    /**
     * Test of Täytätaulukko method, of class Verkko.
     */
    @Test
    public void testTäytätaulukko() {

        assertTrue(testi.taulukko[3][3].haeSeina());
        assertFalse(testi.taulukko[400][400].haeSeina());
        
        assertTrue(testi.taulukko[4][300].haeSeina());
        assertFalse(testi.taulukko[5][300].haeSeina());
    }

    /**
     * Test of HeuristiikkaArvo method, of class Verkko.
     */
    @Test
    public void testHeuristiikkaArvo() {

        int y = 5;
        int x = 5;
        testi.maaliX=10;
        testi.maaliY=10;

        assertEquals(10, testi.HeuristiikkaArvo(y, x));

    }
    
}
