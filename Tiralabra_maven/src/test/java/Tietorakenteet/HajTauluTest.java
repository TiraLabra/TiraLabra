package Tietorakenteet;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class HajTauluTest {
    private HajTaulu taulu;
    
    @Before
    public void setUp() {
        this.taulu = new HajTaulu();
    }
    
    @Test
    public void testaaLisaaminen() throws Exception {
        String avain = "fghj";
        String arvo = "7230+";
        
        taulu.lisaa(avain, arvo);
        assertEquals(arvo, taulu.getArvo(avain));
        
        taulu.lisaa(avain, "5555");
        assertEquals("5555", taulu.getArvo(avain));
    }
    
    @Test
    public void testaaSamankaltaistenAvaimienLisaaminen() {
        taulu = new HajTaulu();
        
        taulu.lisaa("abcd", (char) 1 + "" + (char) 1 + "");
        taulu.lisaa("abcf", (char) 1 + "" + (char) 1 + "");
        taulu.lisaa("abce", (char) 1 + "" + (char) 1 + "");
        
        assertEquals(3, taulu.getAvaimet().length);
    }
    
//    @Test
//    public void testaaPoisto() throws Exception {
//        taulu = new HajTaulu();
//        
//        String avain = "h<";
//        String arvo = "3m";
//        
//        taulu.lisaa(avain, arvo);
//        taulu.poista(avain);
//        assertNull(taulu.getArvo("h<"));
//    }
    
    @Test
    public void etsiminenPalauttaaNullKunEtsittavaEiLoydy() {
        taulu = new HajTaulu();
        assertNull(taulu.etsi("a"));
    }
    
    @Test
    public void etsiminenPalauttaaAvaimenPaikan() {
        taulu = new HajTaulu();
        taulu.lisaa((char) 1 + "", "a");
        taulu.lisaa((char) 8 + "", "b");
        taulu.lisaa((char) 43 + "", "c");
        
        assertEquals(1, taulu.etsi((char) 1 + "").getJ());
        assertEquals(8, taulu.etsi((char) 8 + "").getJ());
        assertEquals(43, taulu.etsi((char) 43 + "").getJ());
    }
}
