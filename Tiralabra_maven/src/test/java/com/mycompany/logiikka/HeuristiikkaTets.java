package com.mycompany.logiikka;

import com.mycompany.tira.Kasipari;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

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
    public void paivitysToimii() {
        this.h.paivitaHeuristiikka(1, 0);
        Kasipari k = new Kasipari(1, 0);

        assertEquals(k, this.h.getViimeisinKasipari());
    }
    
    @Test
    public void getViimeisinToimiiOikein() {
        this.h.paivitaHeuristiikka(1, 2);
        this.h.paivitaHeuristiikka(2, 1);
        this.h.paivitaHeuristiikka(0, 0);
        Kasipari k = new Kasipari(0,0);
        assertEquals(k, this.h.getViimeisinKasipari());
    }
    
    @Test
    public void pelaajaTuleePelaamaanToimiiOikeinYksi() {
        for (int i=0; i<4; i++) {
            this.h.paivitaHeuristiikka(1, 1);
        }
        assertEquals(1, this.h.pelaajaTuleePelaamaan());
    }
    
    @Test
    public void pelaajaTuleePelaamaanToimiiOikeinKaksi() {
        for (int i=0; i<5; i++) {
            this.h.paivitaHeuristiikka(1, 0);
        }
        for (int j=0; j<3; j++) {
            this.h.paivitaHeuristiikka(3, 1);
        }
        assertEquals(1, this.h.pelaajaTuleePelaamaan());
    }
    
    

}
