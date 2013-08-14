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
import tiralabra.tiedostonkasittely.Purkaja;

/**
 *
 * @author joonaslongi
 */
public class PurkajaTest {
    
    private Purkaja purkaja;
    
    public PurkajaTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        this.purkaja = new Purkaja("src/Tiralabra/tiedostot/purkutesti.txt","testattu.txt");
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void laskeeEriMerkitOikein(){
        purkaja.pura();
        assertEquals(3, purkaja.getEriMerkit());
    }
    @Test
    public void laskeeKaikkiMerkitOikein(){
        purkaja.pura();
        assertEquals(12, purkaja.getKaikkiMerkit());
    }
    
    @Test
    public void muodostaaPuun(){
        purkaja.pura();
        assertEquals(99, purkaja.getPuu().getRoot().getVasen().getMerkki());
        assertEquals(98, purkaja.getPuu().getRoot().getOikea().getVasen().getMerkki());
        assertEquals(97, purkaja.getPuu().getRoot().getOikea().getOikea().getMerkki());
    }
    
    @Test
    public void lukeeReititOikein(){
        purkaja.pura();
        String[] reitit = purkaja.getPuu().getReitit();
        assertEquals("11", reitit[97]);
        assertEquals("10", reitit[98]);
        assertEquals("0", reitit[99]);
        
    }
    
    @Test
    public void etsiiReitit(){
        purkaja.pura();
        assertEquals(0, purkaja.merkinReitti());
        
    }
    


}
