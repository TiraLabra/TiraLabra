package com.mycompany.logiikka;

import com.mycompany.domain.Kasi;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class StatistiikkaTest {

    private Statistiikka s;

    public StatistiikkaTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        this.s = new Statistiikka(1);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void tasapelitNollaAlussa() {
        assertEquals(0, this.s.getTasapelit());
    }
    
    @Test
    public void tasapelitKasvaaOikein() {
        this.s.asetaTasapeli();
        this.s.asetaTasapeli();
        assertEquals(2, this.s.getTasapelit());
    }
    
    @Test
    public void kierrostenMaaraKasvaa() {
        this.s.paivitaKierros(new Kasi("KIVI"));
        assertEquals(1, this.s.getKierrokset());
    }
    
    @Test
    public void kierrostenMaaraAlussaNolla() {
        assertEquals(0, this.s.getKierrokset());
    }
    
    @Test
    public void pelaajallaAlussaNollaVoittoa() {
        String s = "Kierroksia : " + this.s.getKierrokset() +
                ", pelaajan voittoja: 0" +
                ", tasapelejä: " + this.s.getTasapelit() + "";
        assertEquals(s, this.s.toString());
    }
    
    @Test
    public void pelaajanVoittojenMaaraKasvaaOikein() {
        this.s.lisaaPelaajanVoitto();
        this.s.lisaaPelaajanVoitto();
        this.s.lisaaPelaajanVoitto();
        String s = "Kierroksia : " + this.s.getKierrokset() +
                ", pelaajan voittoja: 3" +
                ", tasapelejä: " + this.s.getTasapelit() + "";
        assertEquals(s, this.s.toString());
    }
    
    @Test
    public void vahitenPelattuKasiToimiiOikein() {
        this.s.paivitaKierros(new Kasi("KIVI"));
        this.s.paivitaKierros(new Kasi("PAPERI"));
        Kasi k = new Kasi("SAKSET");
        assertEquals(k.getNimi(), this.s.pelaajanVahitenPelattuKasi().getNimi());
    }
    
    @Test
    public void vahitenPelattuKasiToimiiMoodiKaksi() {
        Statistiikka s2 = new Statistiikka(2);
        s2.paivitaKierros(new Kasi("LISKO"));
        s2.paivitaKierros(new Kasi("PAPERI"));
        s2.paivitaKierros(new Kasi("KIVI"));
        s2.paivitaKierros(new Kasi("SAKSET"));
        Kasi k = new Kasi("SPOCK");
        assertEquals(k.getNimi(), s2.pelaajanVahitenPelattuKasi().getNimi());
    }
    
    @Test
    public void vahitenPelattuKasiProsentitToimii() {
        this.s.paivitaKierros(new Kasi("KIVI"));
        this.s.paivitaKierros(new Kasi("PAPERI"));
        assertEquals(0, this.s.vahitenPelattuKasiProsentit());
    }
    
    @Test
    public void vahitenPelattuKasiProsentitToimiiKaksi() {
        this.s.paivitaKierros(new Kasi("KIVI"));
        this.s.paivitaKierros(new Kasi("PAPERI"));
        this.s.paivitaKierros(new Kasi("KIVI"));
        this.s.paivitaKierros(new Kasi("PAPERI"));
        this.s.paivitaKierros(new Kasi("KIVI"));
        this.s.paivitaKierros(new Kasi("SAKSET"));
        // total 6
        // floor 1/6 * 100 = 16
        assertEquals(16, s.vahitenPelattuKasiProsentit());
    }
    
    @Test
    public void toStringToimiiOikein() {
        this.s.paivitaKierros(new Kasi("KIVI"));
        assertEquals("Kierroksia : 1, pelaajan voittoja: 0" + 
                ", tasapelejä: 0", this.s.toString());
    }

}
