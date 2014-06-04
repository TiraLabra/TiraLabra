package Toteutus.Huffman.Pakkaaminen;

import Apuvalineet.BinaariMuuntaja;
import Toteutus.Huffman.BittiEsitykset;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import org.junit.After;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class TiedostonPakkaajaTest {
    private TiedostonPakkaaja pakkaaja;
    
    @Before
    public void setUp() {
        this.pakkaaja = new TiedostonPakkaaja();
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
        BittiEsitykset esitykset = new BittiEsitykset(testattavatBittiEsitykset());

        StringBuilder teksti = new StringBuilder();
        for (String avain : esitykset.getEsitykset().keySet()) {
            teksti.append(avain);
            teksti.append(esitykset.getEsitykset().get(avain));     // a101 b00 c01 (127)(127)
        }
        
        teksti.append((char) 127);
        teksti.append((char) 127);
        teksti.append((char) 0);
        teksti.append("1010001");
        
        pakkaaja.lisaaTeksti(kirjoitettava, "1010001", esitykset);
        assertEquals(teksti.toString(), kirjoitettava.toString());
    }
    
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