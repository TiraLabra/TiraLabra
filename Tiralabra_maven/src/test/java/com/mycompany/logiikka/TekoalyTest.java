package com.mycompany.logiikka;

import com.mycompany.domain.Kasi;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class TekoalyTest {

    private Statistiikka s;
    private Logiikka l;
    private Tekoaly t;

    public TekoalyTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        s = new Statistiikka(1);
        l = new Logiikka(new Heuristiikka());
        t = new Tekoaly(1, s, l, new Heuristiikka());
    }

    @After
    public void tearDown() {
    }

    @Test
    public void tekoalyPalauttaaPaperinAluksi() {
        assertEquals("PAPERI", this.t.tekoalynTarjoamaKasi().getNimi());
    }

    @Test
    public void tekoalySeuraaRotaatiotaKunPelaajaVoittaa() {
        Kasi pel = new Kasi("SAKSET");
        this.l.setPelaajanKasi(pel);
        this.l.setTekoalynKasi(this.t.tekoalynTarjoamaKasi());
        int v = this.l.pelaajaVoittaaKierroksen();
        assertEquals(1, v); // välitulos, pelaaja voittaa
        this.l.setPelaajanKasi(new Kasi("KIVI"));
        this.l.setTekoalynKasi(this.t.tekoalynTarjoamaKasi());
        assertEquals(-1, this.l.pelaajaVoittaaKierroksen());
    }
}
