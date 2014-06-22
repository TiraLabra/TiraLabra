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
        File testi = new File("TiedostonPurkajaTest.huff");
        testi.delete();
    }
    
    private void luoTestiTiedostoJosTarpeen() throws IOException {
        File testi = new File("TiedostonPurkajaTest.huff");
        if (! testi.exists()) {
            Kirjoittaja kirjoittaja = new Kirjoittaja(testi.getPath());
            kirjoittaja.kirjoita("a"+n+n + "b"+n+y + "c"+y+n + "d"+y+y + (char) 127 + (char) 127 + n + (char) 135) ;
        }
    }
    
    @Test
    public void polkuOnValidi() throws IOException {
        purkaja.tarkistaOnkoPolkuValidi("abc.huff");
        purkaja.tarkistaOnkoPolkuValidi("Tiedosto.HUFF");
    }
    
    @Test (expected = IOException.class)
    public void eiValidiPolkuHeittaaPoikkeuksen() throws IOException {
        purkaja.tarkistaOnkoPolkuValidi("abc.huff2");
    }
    
    @Test
    public void pakkauksenHakuOnnistuu() throws IOException {
        File pakkaus = purkaja.haePakkaus("TiedostonPurkajaTest.huff");
        assertTrue(pakkaus.isFile());
    }
    
    @Test (expected = IOException.class)
    public void pakkauksenHaussaHeitetaanPoikkeusJosTiedostoEiOlemassa() throws IOException {
        purkaja.haePakkaus("lol");
    }
    
    @Test
    public void testinLukeminenOnnistuu() throws IOException {
        File tiedosto = new File("TiedostonPurkajaTest.huff");
        assertEquals("a"+n+n + "b"+n+y + "c"+y+n + "d"+y+y + (char) 127 + (char) 127 + n + (char) 135,
                     purkaja.lueTeksti(tiedosto));
    }
    
    @Test
    public void luotavanTiedostonPolkuPalauttaaPolunOikein() {
        String polku = "a.huff";
        assertEquals("a", purkaja.luotavanTiedostonPolku(polku));
        
        polku = ".huff";
        assertEquals("", purkaja.luotavanTiedostonPolku(polku));
    }
    
    @Test
    public void tekstiBinaarina() {
        String teksti = "8a¤" + (char) 2 + ".?,*)";
        assertEquals(y+n+y+y+y+n+n+n+y+y+y+y+y+y+n+n+y+n+y+y+n+n+n+n+y+n+y+n+y+n+n+n+y+n+y+n+n+y, 
                     purkaja.tekstiBinaarina(teksti, 3));
    
    }
}
