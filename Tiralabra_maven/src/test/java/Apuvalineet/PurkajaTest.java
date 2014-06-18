package Apuvalineet;

import Huffman.HuffmanPurkaja;
import java.io.File;
import java.io.IOException;
import org.junit.After;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

public class PurkajaTest {
    private Purkaja purkaja;
    private final String n = (char) 0 + "";
    private final String y = (char) 1 + "";
    
    @Before
    public void setUp() throws IOException {
        this.purkaja = new HuffmanPurkaja();
        luoTestiTiedostoJosTarpeen();
    }
    
    @After
    public void tuhoaTestiTiedosto() {
        File testi = new File("TiedostonPurkajaTest.hemi");
        testi.delete();
    }
    
    private void luoTestiTiedostoJosTarpeen() throws IOException {
        File testi = new File("TiedostonPurkajaTest.hemi");
        if (! testi.exists()) {
            Kirjoittaja kirjoittaja = new Kirjoittaja(testi.getPath());
            kirjoittaja.kirjoita("a"+n+n + "b"+n+y + "c"+y+n + "d"+y+y + (char) 127 + (char) 127 + n + (char) 135) ;
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
    public void testinLukeminenOnnistuu() throws IOException {
        File tiedosto = new File("TiedostonPurkajaTest.hemi");
        assertEquals("a"+n+n + "b"+n+y + "c"+y+n + "d"+y+y + (char) 127 + (char) 127 + n + (char) 135,
                     purkaja.lueTeksti(tiedosto));
    }
    
    @Test
    public void luotavanTiedostonPolkuPalauttaaPolunOikein() {
        String polku = "a.hemi";
        assertEquals("a", purkaja.luotavanTiedostonPolku(polku));
        
        polku = ".hemi";
        assertEquals("", purkaja.luotavanTiedostonPolku(polku));
    }
}
