/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package TiralabraTest;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import tiralabra.tiedostonkasittely.TiedostonKirjoittaja;
import tiralabra.tiedostonkasittely.TiedostonLukija;

/**
 *
 * @author joonaslongi
 */
public class TiedostonKirjoittajaTest {
    
    private TiedostonKirjoittaja kirjoittaja;
    private TiedostonLukija lukija;
    
    public TiedostonKirjoittajaTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        this.kirjoittaja = new TiedostonKirjoittaja("testi");
        this.lukija = new TiedostonLukija("testi");
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void kirjoittaa(){
        kirjoittaja.kirjoita(120);
        int kirjain = lukija.lue();
        assertEquals(120, kirjain);
    }
    
    @Test
    public void kirjoittaa2(){
        kirjoittaja.kirjoita(111);
        int kirjain = lukija.lue();
        assertEquals(111, kirjain);
    }
    
}
