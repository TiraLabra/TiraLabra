package com.mycompany.logiikka;

import com.mycompany.domain.Kasi;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class HeuristiikkaTets {

    private Heuristiikka h;

    public HeuristiikkaTets() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        h = new Heuristiikka();
    }

    @After
    public void tearDown() {
    }

    @Test
    public void antaaNullKunEiStatistiikkaaKasista() {
        assertEquals(null, h.pelaajaTuleePelaamaan());
    }

    @Test
    public void antaaOikeanKadenYksiSyotto() {
        h.setPelaajanKasi(new Kasi("KIVI"));
        h.setTietokoneenKasi(new Kasi("SAKSET"));
        h.setVoitto(1);
        h.updateKasilista();
        assertEquals(new Kasi("KIVI"), h.pelaajaTuleePelaamaan());
    }

    @Test
    public void antaaOikeanKadenMontaSyottoa() {
        for (int i = 0; i < 5; i++) {
            h.setPelaajanKasi(new Kasi("KIVI"));
            h.setTietokoneenKasi(new Kasi("SAKSET"));
            h.setVoitto(1);
            h.updateKasilista();
        }
        for (int i=0; i<3; i++) {
            h.setPelaajanKasi(new Kasi("SAKSET"));
            h.setTietokoneenKasi(new Kasi("KIVI"));
            h.setVoitto(-1);
            h.updateKasilista();
        }
        assertEquals(new Kasi("KIVI"), h.pelaajaTuleePelaamaan());
        assertNotEquals(new Kasi("SAKSET"), h.pelaajaTuleePelaamaan());
    }
    
}
