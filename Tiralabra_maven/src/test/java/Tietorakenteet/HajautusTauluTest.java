package Tietorakenteet;

import java.util.Arrays;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class HajautusTauluTest {
    private HajautusTaulu taulu;
    
    @Before
    public void setUp() {
        this.taulu = new HajautusTaulu();
    }
    
    @Test
    public void testaaLisaaminen() throws Exception {
        String avain = "fghj";
        String arvo = "7230+";
        
        taulu.lisaa(avain, arvo);
        
        int paikka = taulu.etsi(avain);
        assertEquals(arvo, taulu.getArvo(paikka));
        
        taulu.lisaa(avain, "5555");
        assertEquals("5555", taulu.getArvo(paikka));
    }
    
    @Test
    public void testaaPoisto() throws Exception {
        String avain = "henna<";
        String arvo = "3mika";
        
        taulu.lisaa(avain, arvo);
        int paikka = taulu.etsi(avain);
        
        taulu.poista(avain, arvo);
        assertNull(taulu.getArvo(paikka));
    }
     
    @Test
    public void testaaMuuntaminen() {
        int arvo = taulu.muunna("jdsf4");
        int arvo2 = taulu.muunna("jds32hkcv");
        
        assertTrue(arvo == arvo2);
        assertEquals(321, arvo);
        
        assertEquals(17, taulu.muunna((char) 17 + ""));
    }
    
    @Test
    public void paikkaToimiiSatunnaisellaSyotteella() {
        assertEquals(114, taulu.paikka(524, 6));
    }
    
    @Test
    public void paikkaMetodiKelaaKaikkiMahdollisetPaikatLapi() throws Exception {
        taulu = new HajautusTaulu(256);
        int[] testiTaulu = new int[256];
        
        for (int i = 0; i < 256; i++) {
            String avain = (char) i + "";
            
            taulu.lisaa(avain, avain);
            testiTaulu[i] = taulu.etsi(avain);
        }
        
        Arrays.sort(testiTaulu);
        
        int edeltava = testiTaulu[0];
        for (int i = 1; i < testiTaulu.length; i++) {
            assertTrue(edeltava != testiTaulu[i]);
            edeltava = testiTaulu[i];
        }
    }
    
    @Test
    public void uudelleenHajautaAvaimetKunTauluTuleeTayteen() throws Exception {
        taulu = new HajautusTaulu(16);
        for (int i = 0; i < 16; i++) {
            taulu.lisaa((char) i + "", "");
        }
        
        taulu.lisaa("moi", "mö");
        assertEquals("mö", taulu.getArvo(31));
        
    }
}
