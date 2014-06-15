package Tietorakenteet;

import Tietorakenteet.HajTaulu.Hajautettava;
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
    public void testaaLisaaminen() {
        arvoKorvataanKunLisataanSamalleAvaimelle();
        rivinKokoTuplaantuuJosSeOnTaysi();
    }
    
    private void arvoKorvataanKunLisataanSamalleAvaimelle() {
        String avain = "fghj";
        String arvo = "7230+";
        
        taulu.lisaa(avain, arvo);
        assertEquals(arvo, taulu.getArvo(avain));
        
        taulu.lisaa(avain, "5555");
        assertEquals("5555", taulu.getArvo(avain));
        
        assertEquals(1, taulu.getAvaimet().length);
    }
    
    private void rivinKokoTuplaantuuJosSeOnTaysi() {
        int j = 77;
        
        taulu = new HajTaulu();
        taulu.lisaa(taulu.hajautettava("a", "b"), j);
        taulu.lisaa(taulu.hajautettava("adds", null), j);
        
        Hajautettava[] rivi = taulu.getRivi(j);
        
        assertEquals(2, rivi.length);
        assertEquals("b", rivi[0].getArvo());
        assertEquals("adds", rivi[1].getAvain());
    }
    
    @Test
    public void getAvaimetPalauttaaAvaimet() {
        taulu = new HajTaulu();
        
        taulu.lisaa("abcd", (char) 1 + "" + (char) 1 + "");
        taulu.lisaa("abcf", (char) 1 + "" + (char) 1 + "");
        taulu.lisaa("abce", (char) 1 + "" + (char) 1 + "");
        
        String[] avaimet = taulu.getAvaimet();
        assertEquals(3, avaimet.length);
        
        for (String avain : avaimet) {
            assertTrue(avain.equals("abcd") || avain.equals("abce") || avain.equals("abcf"));
        }
    }
    
    @Test
    public void testaaPoisto() {
        taulu = new HajTaulu();
        
        for (int i = 0; i < 5; i++) {
            String avain = (char) i + "";       // 0 1 2 3 4
            String arvo = (char) (i + 65) + ""; // A B C D E
            
            taulu.lisaa(avain, arvo);
        }
        
        avaimenPoistoJokaEiOleTaulussa();
        poistettavanIndeksi();
        poistaessaHajautettujaSiirretaanEteenpain();
    }
    
    private void avaimenPoistoJokaEiOleTaulussa() {
        String avain = null;
        taulu.poista(avain);
        
        avain = (char) 1 + "";
        
        assertEquals("B", taulu.getArvo(avain));
        taulu.poista(avain);
        assertNull(taulu.getArvo(avain));
    }
    
    private void poistettavanIndeksi() {
        int j = 50;
        taulu.tuplaaKoko(j);
        assertEquals(2, taulu.getRivi(j).length);
        
        Hajautettava haj = taulu.hajautettava("5", "?");
        assertEquals(2, taulu.poistettavanIndeksi(haj, j));
        taulu.lisaa(haj, j);
        
        Hajautettava haj2 = taulu.hajautettava("6", "&");
        taulu.lisaa(haj2, j);
        
        assertEquals(0, taulu.poistettavanIndeksi(haj, j));
        assertEquals(1, taulu.poistettavanIndeksi(haj2, j));
    }
    
    private void poistaessaHajautettujaSiirretaanEteenpain() {
        taulu = new HajTaulu();
        int j = 7;
        
        for (int i = 0; i < 5; i++) {
            Hajautettava haj = taulu.hajautettava((char) (i + 65) + "", "asd");
            taulu.lisaa(haj, j);
        }
        
        int k = taulu.poistettavanIndeksi(taulu.hajautettava((char) 67 + "", "das"), j);
        taulu.poista(k, j);
        Hajautettava[] rivi = taulu.getRivi(j);

        for (int i = 0; i < rivi.length; i++) {
            if (i < 2) {
                assertEquals((char) (i + 65) + "", rivi[i].getAvain());
            }
            else if (i < 4) {
                assertEquals((char) (i + 66) + "", rivi[i].getAvain());
            }
            else {
                assertNull(rivi[i]);
            }
        }
    }
    
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
    
    @Test
    public void getArvo() {
        taulu = new HajTaulu();
        taulu.lisaa("moi", "mö");
        
        assertEquals("mö", taulu.getArvo("moi"));
        assertNull(taulu.getArvo("mö"));
    }
    
    @Test
    public void tuplaaKokoTuplaaRivinKoon() {
        taulu = new HajTaulu();
        int j = 31;
        taulu.lisaa(taulu.hajautettava(" ", null), j);
        
        assertEquals(1, taulu.getRivi(j).length);
        taulu.tuplaaKoko(j);
        assertEquals(2, taulu.getRivi(j).length);
        taulu.tuplaaKoko(j);
        taulu.tuplaaKoko(j);
        
        assertEquals(8, taulu.getRivi(j).length);
        
        for (int i = 0; i < 8; i++) {
            if (i == 0) {
                assertEquals(" ", taulu.getRivi(j)[i].getAvain());
            }
            else {
                assertNull(taulu.getRivi(j)[i]);
            }
        }
    }
    
    @Test
    public void onTyhja() {
        taulu = new HajTaulu();
        assertTrue(taulu.onTyhja());
        
        taulu.lisaa("aafsda", "jkojsd");
        assertFalse(taulu.onTyhja());
        
        taulu.poista("aafsda");
        assertTrue(taulu.onTyhja());
    }
    
    @Test
    public void sisaltaaAvaimen() {
        taulu = new HajTaulu();
        assertFalse(taulu.sisaltaaAvaimen("abc"));
        
        taulu.lisaa("abc", "def");
        assertTrue(taulu.sisaltaaAvaimen("abc"));
        assertFalse(taulu.sisaltaaAvaimen("def"));
    }
    
    @Test
    public void lisaaTaulukkoonToimii() {
        taulu = new HajTaulu();
        
        for (int i = 0; i < 5; i++) {
            taulu.lisaa("bac" + (char) i + "", "koas" + (char) i + "");
        }
        
        String[] taulukko = new String[taulu.getKoko()];
        String[] taulukko2 = new String[taulu.getKoko()];
        
        taulu.lisaaTaulukkoon(taulukko, true);
        taulu.lisaaTaulukkoon(taulukko2, false);
        
        String[] avaimet = taulu.getAvaimet();
        String[] arvot = taulu.getArvot();
        
        for (int i = 0; i < taulu.getKoko(); i++) {
            assertEquals(taulukko[i], avaimet[i]);
            assertEquals(taulukko2[i], arvot[i]);
        }
    }
}
