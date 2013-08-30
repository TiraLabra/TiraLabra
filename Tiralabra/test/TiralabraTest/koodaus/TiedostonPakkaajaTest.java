/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package TiralabraTest.koodaus;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import tiralabra.koodaus.TiedostonPakkaaja;

/**
 *
 * @author joonaslongi
 */
public class TiedostonPakkaajaTest {
    
    private TiedostonPakkaaja pakkaaja;
    
    public TiedostonPakkaajaTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        this.pakkaaja = new TiedostonPakkaaja("testattu.txt", "test2.txt");
        pakkaaja.pakkaa();
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void oikeatToistotA(){
        assertEquals(4, pakkaaja.getLaskija().getToistot()[97]);
    }
    
    @Test
    public void oikeatToistotB(){
        assertEquals(3, pakkaaja.getLaskija().getToistot()[98]);
    }
    
    @Test
    public void oikeatToistotC(){
        assertEquals(5, pakkaaja.getLaskija().getToistot()[99]);
    }
    
    @Test
    public void eiYlimaaraisiaToistoja(){
        assertEquals(0, pakkaaja.getLaskija().getToistot()[96]);
        assertEquals(0, pakkaaja.getLaskija().getToistot()[100]);
    }
    
    @Test
    public void laskeeKirjaimetOikein(){
        assertEquals(12, pakkaaja.laskeKaikkiKirjaimet());
    }
    
    @Test
    public void laskeeEriKirjaimetOikein(){
        assertEquals(3, pakkaaja.laskeEriKirjaimet());
    }
    
    @Test
    public void muodostaaSanakirjan(){
        assertEquals("a11/b10/c0/", pakkaaja.muodostaSanakirja() );
    }
    
    @Test
    public void muodostaaJonon(){
        pakkaaja.muodostaJono();
        assertEquals(3, pakkaaja.getJono().koko());
    }
    

}
