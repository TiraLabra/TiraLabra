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

        assertEquals(arvo, taulu.getArvo(avain));
        
        taulu.lisaa(avain, "5555");
        assertEquals("5555", taulu.getArvo(avain));
    }
    
    @Test
    public void testaaSamankaltaistenAvaimienLisaaminen() throws Exception {
        taulu = new HajautusTaulu(3);
        
        taulu.lisaa("abcd", (char) 1 + "" + (char) 1 + "");
        taulu.lisaa("abcf", (char) 1 + "" + (char) 1 + "");
        taulu.lisaa("abce", (char) 1 + "" + (char) 1 + "");
        
        assertEquals(3, taulu.getKoko());
    }
    
    @Test
    public void testaaPoisto() throws Exception {
        taulu = new HajautusTaulu();
        
        String avain = "henna<";
        String arvo = "3mika";
        
        taulu.lisaa(avain, arvo);
        taulu.poista(avain, arvo);
        assertNull(taulu.getArvo("henna<"));
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
        taulu.etsi("a");
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
    public void testaaHajauttaminenKunPaikkaOlemassa() throws Exception {
        String[] taulukko = {"aabo", "aaba", "c", null, "e"};
        assertEquals(3, taulu.hajauta(7, taulukko));
        
        String[] taulukko2 = {"beef", "bee", "hfdgd", "i", "ksad", "5067", null};
        assertEquals(6, taulu.hajauta(10, taulukko2));
    }
    
    @Test
    public void testaaHajauttaminenKunTaulukkoOnTaysi() throws Exception {
        taulu = new HajautusTaulu(1);
        taulu.lisaa("acv", "fjls");
        
    }
    
    @Test
    public void uusienTaulukoidenAlustaminen() throws Exception {
        String[] uudetAvaimet = new String[7];
        String[] uudetArvot = new String[7];
        
        taulu = new HajautusTaulu(5);
        
        String n = (char) 0 + "";
        String y = (char) 1 + "";
        
        taulu.lisaa("a", n+n+n);
        taulu.lisaa("b", n+n+y);
        taulu.lisaa("c", n+y+n);
        taulu.lisaa("d", n+y+y);
        taulu.lisaa("e", y);
        
        taulu.alustaUudetTaulukot(uudetAvaimet, uudetArvot);

        taulu.setArvot(uudetArvot);
        taulu.setAvaimet(uudetAvaimet);
        
        assertEquals(5, taulu.getKoko());
    }
    
    @Test
    public void uudelleenHajautaAvaimet() throws Exception {
        taulu = new HajautusTaulu(3);
        taulu.lisaa("abcd", (char) 1 + "" + (char) 1 + "");
        taulu.lisaa("abcf", (char) 1 + "" + (char) 1 + "");
        taulu.lisaa("abce", (char) 1 + "" + (char) 1 + "");
        
        taulu.uudelleenHajautaAvaimet();
        
        assertEquals(3, taulu.getKoko());
        assertEquals(294, taulu.etsi("abcd"));
    }
    
//    @Test
//    public void uudelleenHajautaAvaimetKunTauluTuleeTayteen() throws Exception {
//        taulu = new HajautusTaulu(3);
//        for (int i = 0; i < 3; i++) {
//            taulu.lisaa((char) i + "", "");
//        }
//        
//        taulu.lisaa("aapinen", "kukko");
//        assertEquals("kukko", taulu.getArvo("aapinen"));
//    }
}