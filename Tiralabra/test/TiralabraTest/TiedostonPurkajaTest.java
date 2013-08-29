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
import tiralabra.tiivistys.TiedostonPurkaja;

/**
 *
 * @author joonaslongi
 */
public class TiedostonPurkajaTest {
    
    private TiedostonPurkaja purkaja;
    
    public TiedostonPurkajaTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        this.purkaja = new TiedostonPurkaja("purkutesti.txt","testattu.txt");
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
        assertEquals(99, purkaja.merkinReitti());
        
    }
    
    @Test
    public void tavuBiteiksi(){
        boolean[] oikein = {false, true, true, false, false, false, false, true};
        boolean[] bitit = purkaja.muunnaBiteiksi(97);
        for(int i = 0; i < 8 ; i++){
            assertEquals(oikein[i], bitit[i]);
        }
    }
    
    @Test
    public void bititMeneeJonoon(){
        purkaja.lueTavu();
        assertEquals(8, purkaja.getJono().getKoko());
    }
    
    @Test
    public void jonoKasvaa(){
        purkaja.lueTavu();
        purkaja.lueTavu();
        assertEquals(16, purkaja.getJono().getKoko());
    }
    


}
