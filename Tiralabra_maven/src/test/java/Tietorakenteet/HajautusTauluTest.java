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
     
    @Test (expected = Exception.class)
    public void muuntaminenHeittaaVirheenJosMuunnettavaTyhja() throws Exception {
        taulu.muunnaAvain("");
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
        taulu = new HajautusTaulu();
        assertEquals(100, taulu.paikka(524, 6));
    }
    
    @Test
    public void paikkaMetodiKelaaKaikkiMahdollisetPaikatLapi() throws Exception {
        taulu = new HajautusTaulu(251);
        int[] testiTaulu = new int[251];
        
        for (int i = 0; i < 251; i++) {
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
    
    @Test (expected = Exception.class)
    public void etsiminenHeittaaVirheenKunEtsittavaEiLoydy() throws Exception {
        taulu = new HajautusTaulu(3);
        taulu.etsi(5);
    }
    
    @Test
    public void etsiminenPalauttaaAvaimenPaikan() throws Exception {
        taulu = new HajautusTaulu(7);
        taulu.lisaa((char) 1 + "", "a");
        taulu.lisaa((char) 8 + "", "b");
        taulu.lisaa((char) 43 + "", "c");
        
        assertEquals(1, taulu.etsi((char) 1 + ""));
        assertEquals(5, taulu.etsi((char) 8 + ""));
        assertEquals(2, taulu.etsi((char) 43 + ""));
    }
    
    @Test
    public void testaaHajauttaminen() {
        int[] taulukko = {1, 3, 9, Integer.MIN_VALUE, 5};
        assertEquals(3, taulu.hajauta(7, taulukko));
    }
    
//    
//    @Test
//    public void uudelleenHajautaAvaimetKunTauluTuleeTayteen() throws Exception {
//        taulu = new HajautusTaulu();
//        for (int i = 0; i < 256; i++) {
//            taulu.lisaa((char) i + "", "");
//        }
//        
//        taulu.lisaa("aapinen", "kukko");
//        taulu.lisaa("moi", "mö");
//        assertEquals("mö", taulu.getArvo(31));
//    }
}