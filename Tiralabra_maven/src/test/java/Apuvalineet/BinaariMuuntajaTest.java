package Apuvalineet;

import java.util.HashMap;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

public class BinaariMuuntajaTest {
    private BinaariMuuntaja muuntaja;
    private HashMap<String, String> bittijonot;
    private String teksti;
    
    @Before
    public void setUp() {
        this.muuntaja = new BinaariMuuntaja();
        
        this.bittijonot = new HashMap<>();
        bittijonot.put("a", "01");
        bittijonot.put("b", "000");
        bittijonot.put("c", "001");
        bittijonot.put("d", "10");
        bittijonot.put("e", "11");
        
        this.teksti = "aecdbead";
    }    
    
    @Test
    public void pakattuTekstiOnOikeanlainen() {
        tekstiIlmanEtuNollia();
        assertEquals("01010100", muuntaja.lisaaEtuNollat("01010100"));
        assertEquals("01010100", muuntaja.lisaaEtuNollat("1010100"));
        
        assertEquals("000000011100110000110110", muuntaja.ykkosinaJaNollina(teksti, bittijonot));
    }

    private void tekstiIlmanEtuNollia() {
        String ilmanEtuNollia = muuntaja.ilmanEtuNollia(teksti, bittijonot);
        assertEquals(ilmanEtuNollia, "011100110000110110");
    }
    
    @Test
    public void etuNollienLisaaminenKasvattaaNiidenLaskettuaMaaraa() {
        muuntaja = new BinaariMuuntaja();
        assertEquals(0, muuntaja.getLisatytEtuNollat());
        muuntaja.lisaaEtuNollat("011001");
        assertEquals(2, muuntaja.getLisatytEtuNollat());
    }
    
    @Test
    public void asciiMerkkinaPalauttaaOikeanMerkin() {
        char merkki = muuntaja.asciiMerkkina("10101");
        assertTrue(merkki == 21);
        
        merkki = muuntaja.asciiMerkkina("01100110");
        assertTrue(merkki == 102);
        
        merkki = muuntaja.asciiMerkkina("");
        assertTrue(merkki == 0);
        
        merkki = muuntaja.asciiMerkkina("1111111");
        assertTrue(merkki == 127);
        
        merkki = muuntaja.asciiMerkkina("10000111");
        assertTrue(merkki == 135);
        
        merkki = muuntaja.asciiMerkkina("10101100");
        assertTrue(merkki == 172);
    }
    
    @Test
    public void seuraavanTavunHakuOnnistuu() {
        String ykkosinaJaNollina = muuntaja.ykkosinaJaNollina(teksti, bittijonot);
        char merkki = muuntaja.seuraavaTavuAsciiMerkkina(ykkosinaJaNollina, 0);
        assertTrue((int) merkki == 1);
        
        merkki = muuntaja.seuraavaTavuAsciiMerkkina(ykkosinaJaNollina, 8);
        assertTrue((int) merkki == 204);
    }
    
    @Test
    public void tekstinPakkaaminenTavuiksiToimii() {
        assertEquals(muuntaja.pakatuksiTekstiksi(""), "");
        assertEquals(muuntaja.pakatuksiTekstiksi("01100110"), "f");
        assertEquals(muuntaja.pakatuksiTekstiksi("011011010110111101101001"), "moi");
    }
    
    @Test
    public void testaaArvojenBinaariEsitykset() {
        assertEquals("0", muuntaja.binaariEsitysIlmanEtuNollia(0, 7));
        assertEquals("1", muuntaja.binaariEsitysIlmanEtuNollia(1, 7));
        assertEquals("10", muuntaja.binaariEsitysIlmanEtuNollia(2, 7));
        assertEquals("101", muuntaja.binaariEsitysIlmanEtuNollia(5,7));
        assertEquals("1000111010111011111011111001010", muuntaja.binaariEsitysIlmanEtuNollia(1197340618, 30));
    }
    
    @Test
    public void etuMerkkienPoistoToimii() {
        String sana = "sana";
        assertEquals("sana", muuntaja.poistaEtuMerkkeja(sana, 0));
        assertEquals("", muuntaja.poistaEtuMerkkeja(sana, sana.length()));
    }
}
