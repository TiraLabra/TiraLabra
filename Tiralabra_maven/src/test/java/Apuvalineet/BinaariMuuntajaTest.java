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
        assertTrue((int) merkki == 21);
        
        merkki = muuntaja.asciiMerkkina("01100110");
        assertTrue((int) merkki == 102);
        
        merkki = muuntaja.asciiMerkkina("");
        assertTrue((int) merkki == 0);
        
        merkki = muuntaja.asciiMerkkina("1111111");
        assertTrue((int) merkki == 127);
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
        assertEquals("0", muuntaja.binaariEsitys(0));
        assertEquals("1", muuntaja.binaariEsitys(1));
        assertEquals("10", muuntaja.binaariEsitys(2));
        assertEquals("101", muuntaja.binaariEsitys(5));
        assertEquals("1000111010111011111011111001010", muuntaja.binaariEsitys(1197340618));
    }
    
    @Test
    public void puuOsoittimenMuodostus() {
        char nul = (char) 0;
        assertEquals(nul + "" + nul + "" + nul + "" + (char) 5, muuntaja.muodostaOsoitin(0));
        assertEquals(nul + "" + nul + "" + (char) 1 + "" + nul, muuntaja.muodostaOsoitin(251));
        assertEquals(nul + "" + (char) 3 + "" + (char) 254 + "" + (char) 70, muuntaja.muodostaOsoitin(261697));
        assertEquals((char) 127 + "" + (char) 47 + "" + (char) 5 + "" + nul, muuntaja.muodostaOsoitin(2133787899));
    }
    
    @Test
    public void osoitinKokonaisLukuna() {
        char nul = (char) 0;
        
        String osoitin = nul + "" + nul + "" + nul + "a";
        assertEquals(97, muuntaja.osoitinKokonaisLukuna(osoitin));
        
        osoitin = nul + "" + nul + "" + " " + "M";
        assertEquals(8269, muuntaja.osoitinKokonaisLukuna(osoitin));
        
        osoitin = nul + "" + "3" + "" + nul + "" + "!";
        assertEquals(3342369, muuntaja.osoitinKokonaisLukuna(osoitin));
        
        osoitin = "1>=2";
        assertEquals(822083584 + 4063232 + 15616 + 50, muuntaja.osoitinKokonaisLukuna(osoitin));
    }
}
