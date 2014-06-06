package Toteutus.Huffman.Purkaminen;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
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
    
//    @Test
//    public void testinLukeminenOnnistuu() throws FileNotFoundException {
//        File tiedosto = new File("TiedostonPurkajaTest.hemi");
//        assertEquals("jaflsdjvöjsd gjfgdf", purkaja.lueTeksti(tiedosto));
//    }
    
    @Test
    public void luotavanTiedostonPolkuPalauttaaPolunOikein() {
        String polku = "a.hemi";
        assertEquals("a", purkaja.luotavanTiedostonPolku(polku));
        
        polku = ".hemi";
        assertEquals("", purkaja.luotavanTiedostonPolku(polku));
    }
    
    @Test
    public void tekstiBinaarinaTavallisillaAsciiMerkeilla() {
        String teksti = "8a�" + (char) 2 + ".?,*)";
        assertEquals("10111000111111001011000010101000101001", purkaja.tekstiBinaarina(teksti, 3));
    }
    
    @Test
    public void tavuIlmanEtuNolliaTavallisillaAsciiMerkeilla() {
        String teksti = (char) 0 + "+/";
        assertEquals("00101011", purkaja.tavuIlmanEtuNollia(teksti, 0));
        
        teksti = "abc" + (char) 5 + "" + (char) 7 + "!D";
        assertEquals("111", purkaja.tavuIlmanEtuNollia(teksti, 3));
    }
    
    @Test
    public void lisaaMuuTekstiTavallisillaAsciiMerkeilla() {
        String teksti = "tty56B4";
        assertTrue(purkaja.lisaaMuuTeksti(teksti, 5).isEmpty());
        assertEquals("0100001000110100", purkaja.lisaaMuuTeksti(teksti, 3));
    }
    
    @Test
    public void kirjoitettavaTeksti() {
        HashMap<String, String> bittijonotJaMerkit = bittijonotJaMerkit();

        assertTrue(purkaja.kirjoitettavaTeksti("", bittijonotJaMerkit).isEmpty());
        assertEquals("bcadabc", purkaja.kirjoitettavaTeksti("101100111010110", bittijonotJaMerkit));
    }
    
    private HashMap<String, String> bittijonotJaMerkit() {
        HashMap<String, String> bittijonotJaMerkit = new HashMap<>();
        bittijonotJaMerkit.put("0", "a");
        bittijonotJaMerkit.put("10", "b");
        bittijonotJaMerkit.put("110", "c");
        bittijonotJaMerkit.put("111", "d");
        
        return bittijonotJaMerkit;
    }
}
