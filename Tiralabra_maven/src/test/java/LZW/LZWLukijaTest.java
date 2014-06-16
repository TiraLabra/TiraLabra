package LZW;

import Apuvalineet.BinaariMuuntaja;
import Tietorakenteet.HajTaulu;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class LZWLukijaTest {
    private LZWLukija lukija;
    private BinaariMuuntaja muuntaja;
    
    @Before
    public void setUp() {
        this.lukija = new LZWLukija();
        this.muuntaja = new BinaariMuuntaja();
    }
    
    @Test
    public void tekstiAlussaTyhja() {
        assertTrue(lukija.getTeksti().isEmpty());
    }
    
    @Test
    public void taulunAlustusToimii() {
        HajTaulu esitykset = lukija.getEsitykset();
        String[] avaimet = esitykset.getAvaimet();
        
        for (int i = 0; i < avaimet.length; i++) {
            String avain = avaimet[i];
            
            assertEquals((char) i + "", avain);
            assertEquals(muuntaja.binaariEsitysEtuNollilla8Bit(i), esitykset.getArvo(avain));
        }
        assertEquals(256, lukija.getIndex());
    }
    
    @Test
    public void indexKahdenPotenssi() {
        lukija = new LZWLukija();
        assertTrue(lukija.indexKahdenPotenssi());
        lukija.lisaaAvain("");
        assertFalse(lukija.indexKahdenPotenssi());
    }
    
    @Test
    public void lisaaArvojenEteenEtuNolla() {
        lukija = new LZWLukija();
        String[] arvot = new String[lukija.getEsitykset().getKoko()];
        System.arraycopy(lukija.getEsitykset().getArvot(), 0, arvot, 0, arvot.length);
        
        lukija.lisaaArvojenEteenEtuNolla();
        String[] uudetArvot = lukija.getEsitykset().getArvot();
        
        for (int i = 0; i < arvot.length; i++) {
            assertEquals((char) 0 + arvot[i], uudetArvot[i]);
        }
    }
    
    @Test
    public void lisaaAvain() {
        lukija = new LZWLukija();
        lukija.lisaaAvain("ab");
        
        assertEquals(257, lukija.getIndex());
        assertTrue(lukija.getEsitykset().sisaltaaAvaimen("ab"));
    }
}
