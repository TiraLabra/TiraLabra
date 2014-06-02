package Toteutus.Huffman.Pakkaaminen;

import Apuvalineet.BinaariMuuntaja;
import java.io.File;
import java.io.IOException;
import org.junit.After;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class TiedostonPakkaajaTest {
    private TiedostonPakkaaja pakkaaja;
    private BinaariMuuntaja muuntaja;
    
    @Before
    public void setUp() {
        this.pakkaaja = new TiedostonPakkaaja();
        this.muuntaja = new BinaariMuuntaja();
    }
    
    @After
    public void tuhoaLuotuPakkaus() {
        tuhoaMahdOlemassaOlevaPakkaus();
    }
    
    @Test
    public void luoUusiTiedostoLuoUudenTiedoston() throws IOException {
        tuhoaMahdOlemassaOlevaPakkaus();
        File tiedosto = pakkaaja.luoUusiTiedosto("TiedostonPakkaajaTest.txt");
            
        assertTrue(tiedosto.canWrite());
        assertTrue(tiedosto.exists());    
        assertTrue(tiedosto.isFile()); 
    }
    
    @Test (expected = IOException.class)
    public void luoUusiTiedostoHeittaaPoikkeuksenJosPakkausJoOlemassa() throws IOException {
        tuhoaMahdOlemassaOlevaPakkaus();
        
        pakkaaja.luoUusiTiedosto("TiedostonPakkaajaTest.txt");
        pakkaaja.luoUusiTiedosto("TiedostonPakkaajaTest.txt");
    }
    
    private void tuhoaMahdOlemassaOlevaPakkaus() {
        File pakkaus = new File("TiedostonPakkaajaTest.txt.hemi");
        if (pakkaus.exists()) {
            pakkaus.delete();
        }
    }
    
    @Test
    public void kirjoitaTiedostoonKirjoittaaTiedostoonTekstin() throws IOException {
        tuhoaMahdOlemassaOlevaPakkaus();
        File tiedosto = pakkaaja.luoUusiTiedosto("TiedostonPakkaajaTest.txt");
        String teksti = "teksti";
        
        pakkaaja.kirjoitaTiedostoon(tiedosto, teksti);
        assertEquals(6, tiedosto.length());
    }
}