package com.mycompany.logiikka;

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
        this.s = new Statistiikka();
    }

    @After
    public void tearDown() {
    }

    @Test
    public void toStringOikeinAlussa() {
        String oletus = "Kierroksia : 0, pelaajan voittoja: 0, tasapelejä: 0";
        assertEquals(oletus, this.s.toString());
    }
    
    @Test
    public void updateToimiiOikein() {
        this.s.update(0);
        String oletus = "Kierroksia : 1, pelaajan voittoja: 1, tasapelejä: 0";
        assertEquals(oletus, this.s.toString());
    }
    
    @Test
    public void updateToimiiOikeinKaksi() {
        this.s.update(-1);
        String oletus = "Kierroksia : 1, pelaajan voittoja: 0, tasapelejä: 1";
        assertEquals(oletus, this.s.toString());
    }
    
    @Test
    public void updateToimiiOikeinKolme() {
        this.s.update(1);
        String oletus = "Kierroksia : 1, pelaajan voittoja: 0, tasapelejä: 0";
        assertEquals(oletus, this.s.toString());
    }
    
    @Test
    public void toStringToimiiOikeinMontaKierrosta() {
        for (int i=0; i<5; i++) {
            this.s.update(0);
        }
        String oletus = "Kierroksia : 5, pelaajan voittoja: 5, tasapelejä: 0";
        assertEquals(oletus, this.s.toString());
    }
    
    @Test
    public void toStringToimiiSekalainen() {
        for (int i=0; i<5; i++) {
            this.s.update(0);
        }
        for (int i=0; i<3; i++) {
            this.s.update(1);
        }
        for (int i=0; i<6; i++) {
            this.s.update(-1);
        }
        String oletus = "Kierroksia : 14, pelaajan voittoja: 5, tasapelejä: 6";
        assertEquals(oletus, this.s.toString());
    }
}
