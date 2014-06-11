package Apuvalineet;

import java.io.File;
import java.io.IOException;
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class KirjoittajaTest {
    private Kirjoittaja kirjoittaja;
    
    @Before
    public void luoTiedosto() throws IOException {
        this.kirjoittaja = new Kirjoittaja("KirjoittajaTest.txt");
    }
    
    @After
    public void tuhoaLuotuPakkaus() {
        tuhoaMahdOlemassaOlevaPakkaus();
    }
    
//    @Test
//    public void luoUusiTiedostoLuoUudenTiedoston() throws IOException {
//        tuhoaMahdOlemassaOlevaPakkaus();
//        File tiedosto = kirjoittaja.luoUusiTiedosto("TiedostonPakkaajaTest.txt");
//            
//        assertTrue(tiedosto.canWrite());
//        assertTrue(tiedosto.exists());    
//        assertTrue(tiedosto.isFile()); 
//    }
//    
//    @Test (expected = IOException.class)
//    public void luoUusiTiedostoHeittaaPoikkeuksenJosPakkausJoOlemassa() throws IOException {
//        tuhoaMahdOlemassaOlevaPakkaus();
//        
//        kirjoittaja.luoUusiTiedosto("TiedostonPakkaajaTest.txt");
//        kirjoittaja.luoUusiTiedosto("TiedostonPakkaajaTest.txt");
//    }
    
    private void tuhoaMahdOlemassaOlevaPakkaus() {
        File pakkaus = new File("TiedostonPakkaajaTest.txt.hemi");
        if (pakkaus.exists()) {
            pakkaus.delete();
        }
    }
    
    @Test
    public void testaaKirjoittaminen() throws IOException, Exception {
        kirjoittaja.kirjoita("asdhoashdoas");
        
        TekstinLukija lukija = new TekstinLukija();
        lukija.lueTiedosto("KirjoittajaTest.txt");
        assertEquals("asdhoashdoas", lukija.getTeksti());
    }
            
    @After
    public void tuhoaKirjoitettuTiedosto() {
        new File("KirjoittajaTest.txt").delete();
    }
}
