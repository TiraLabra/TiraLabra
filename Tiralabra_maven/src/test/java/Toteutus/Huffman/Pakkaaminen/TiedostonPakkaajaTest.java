package Toteutus.Huffman.Pakkaaminen;

import Apuvalineet.BinaariMuuntaja;
import Toteutus.Huffman.HuffmanPuu;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
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
    
    @Test
    public void lisaaTekstiToimii() {
        StringBuilder kirjoitettava = new StringBuilder();
        pakkaaja.lisaaTeksti(kirjoitettava, "abc", new HuffmanPuu());
        
        char nul = (char) 0;
        String teksti = nul + "" + nul + "" + nul + (char) 8;
        teksti += nul + "abc";
        
        assertEquals(teksti, kirjoitettava.toString());
    }
    
//    @Test
//    public void tekstiPakattunaPalauttaaPakatunTekstin() {
//        HashMap<String, String> esitykset = testattavatBittiEsitykset();
//        String teksti = "ebecbdca";
//
//        assertEquals("4�", pakkaaja.tekstiPakattuna(esitykset, teksti));
//    }
    
//    @Test
//    public void kirjoitettavanTekstinMuodostaminen() {
//        String kirjoitettava = pakkaaja.muodostaKirjoitettavaTeksti(testattavatBittiEsitykset(), new HuffmanPuu(), "ebecbdca");
//        
//        char nul = (char) 0;
//        assertEquals(nul + "" + nul + "" + nul + "" + (char) 7 + "4�", kirjoitettava);
//    }
    
    private HashMap<String, String> testattavatBittiEsitykset() {
        HashMap<String, String> esitykset = new HashMap<>();
        esitykset.put("a", "101");
        esitykset.put("b", "00");
        esitykset.put("c", "01");
        esitykset.put("d", "100");
        esitykset.put("e", "11");
        
        return esitykset;
    }
}