package com.mycompany.logiikka;

import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class LogiikkaTest {
    
    private Logiikka l;
    private Statistiikka s;
    
    public LogiikkaTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        s = new Statistiikka();
        this.l = new Logiikka(this.s, 1);
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void kierrosToimiiOikein() {
        this.l.asetaPelaajanKasi(0);
        this.l.pelaaKierros();
        String oletus = "Kierroksia : 1, pelaajan voittoja: 0, tasapelejä: 0";
        assertEquals(oletus, this.s.toString());
    }
    
    @Test
    public void kierrosToimiiOikeinKaksi() {
        this.l.asetaPelaajanKasi(0);
        this.l.pelaaKierros();
        this.l.pelaaKierros();
        String oletus = "Kierroksia : 2, pelaajan voittoja: 1, tasapelejä: 0";
        assertEquals(oletus, this.s.toString());
    }
}
