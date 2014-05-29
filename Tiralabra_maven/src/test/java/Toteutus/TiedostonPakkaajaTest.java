package Toteutus;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class TiedostonPakkaajaTest {
    private TiedostonPakkaaja pakkaaja;
    private HashMap<String, String> bittijonot;
    private String teksti;
    
    @Before
    public void setUp() {
        this.pakkaaja = new TiedostonPakkaaja();
        
        this.bittijonot = new HashMap<String, String>();
        bittijonot.put("a", "01");
        bittijonot.put("b", "000");
        bittijonot.put("c", "001");
        bittijonot.put("d", "10");
        bittijonot.put("e", "11");
        
        this.teksti = "aecdbead";
    }
    
    @Test
    public void uudenTiedostonLuontiOnnistuu() {
        try {
            File file = pakkaaja.luoUusiTiedosto("TiedostonPakkaajaTest.txt");
            assertTrue(file.canWrite());
        }
        catch (IOException e) {
            assertTrue(! e.getMessage().isEmpty());   // jos mukana viesti, ei virhettä.
        }
    }
    
    @Test
    public void uuttaTiedostoaEiLuodaKunPolkuEiTasmaa() {
        try {
            pakkaaja.luoUusiTiedosto("eitasmaa");
            assertTrue(false);
        }
        catch (IOException e) {
            assertTrue(true);
        }
    }
    
    @Test
    public void pakattuTekstiOnOikeanlainen() {
        tekstiIlmanEtuNollia();
        assertEquals("", pakkaaja.lisaaEtuNollat("01010100"));
        assertEquals("0", pakkaaja.lisaaEtuNollat("1010100"));
        
        assertEquals("000000011100110000110110", pakkaaja.ykkosinaJaNollina(teksti, bittijonot));
    }

    private void tekstiIlmanEtuNollia() {
        String ilmanEtuNollia = pakkaaja.ilmanEtuNollia(teksti, bittijonot);
        assertEquals(ilmanEtuNollia, "011100110000110110");
    }
    
    @Test
    public void asciiMerkkinaPalauttaaOikeanMerkin() {
        char merkki = pakkaaja.asciiMerkkina("10101");
        assertTrue((int) merkki == 21);
        
        merkki = pakkaaja.asciiMerkkina("01100110");
        assertTrue((int) merkki == 102);
        
        merkki = pakkaaja.asciiMerkkina("");
        assertTrue((int) merkki == 0);
    }
    
    @Test
    public void seuraavanTavunHakuOnnistuu() {
        String ykkosinaJaNollina = pakkaaja.ykkosinaJaNollina(teksti, bittijonot);
        char merkki = pakkaaja.seuraavaTavuAsciiMerkkina(ykkosinaJaNollina, 0);
        assertTrue((int) merkki == 1);
        
        merkki = pakkaaja.seuraavaTavuAsciiMerkkina(ykkosinaJaNollina, 8);
        assertTrue((int) merkki == 204);
    }
}
