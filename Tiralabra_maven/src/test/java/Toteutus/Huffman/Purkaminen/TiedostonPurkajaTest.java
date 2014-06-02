package Toteutus.Huffman.Purkaminen;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class TiedostonPurkajaTest {
    private TiedostonPurkaja purkaja;
    
    @Before
    public void setUp() throws IOException {
        this.purkaja = new TiedostonPurkaja();
        luoTestiTiedostoJosTarpeen();
    }
    
    private void luoTestiTiedostoJosTarpeen() throws IOException {
        File testi = new File("TiedostonPurkajaTest.hemi");
        if (! testi.exists()) {
            testi.createNewFile();
            
            FileWriter kirjoittaja = new FileWriter(testi);
            kirjoittaja.write("jaflsdjvöjsd gjfgdf");
            kirjoittaja.close();
        }
    }
    
    @Test
    public void polkuOnValidi() throws IOException {
        purkaja.tarkistaOnkoPolkuValidi("abc.hemi");
        purkaja.tarkistaOnkoPolkuValidi("Tiedosto.HEMI");
    }
    
    @Test (expected = IOException.class)
    public void eiValidiPolkuHeittaaPoikkeuksen() throws IOException {
        purkaja.tarkistaOnkoPolkuValidi("abc.hemi2");
    }
    
    @Test
    public void pakkauksenHakuOnnistuu() throws IOException {
        File pakkaus = purkaja.haePakkaus("TiedostonPurkajaTest.hemi");
        assertTrue(pakkaus.isFile());
    }
    
    @Test (expected = IOException.class)
    public void pakkauksenHaussaHeitetaanPoikkeusJosTiedostoEiOlemassa() throws IOException {
        purkaja.haePakkaus("lol");
    }
    
    @Test
    public void tiedostonMuodostusOnnistuu() throws IOException {
        File tiedosto = purkaja.muodostaTiedosto("TiedostonPurkajaTest.hemi");
        assertFalse(tiedosto.exists());
        assertEquals("TiedostonPurkajaTest", tiedosto.getPath());
    }
    
    @Test (expected = IOException.class) 
    public void tiedostonMuodostusHeittaaPoikkeuksenKunPurettuTiedostoJoOlemassa() throws IOException {
        purkaja.muodostaTiedosto("TiedostonPurkajaTest.hemi.hemi");
    }
    
    @Test
    public void testinLukeminenOnnistuu() throws FileNotFoundException {
        File tiedosto = new File("TiedostonPurkajaTest.hemi");
        assertEquals("jaflsdjvöjsd gjfgdf", purkaja.lueTeksti(tiedosto));
    }
    
    @Test
    public void puunOsoitePalauttaaOsoitteen() {
        char nul = (char) 0;
        assertEquals(100, purkaja.puunOsoite(nul + "" + nul + "" + nul + "" + "d" + "fjsdlkfjlsd"));
    }
    
    @Test
    public void luotavanTiedostonPolkuPalauttaaPolunOikein() {
        String polku = "a.hemi";
        assertEquals("a", purkaja.luotavanTiedostonPolku(polku));
        
        polku = ".hemi";
        assertEquals("", purkaja.luotavanTiedostonPolku(polku));
    }
}
