package com.mycompany.tira;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class KasiListaTest {
    
    private KasiLista l;
    
    public KasiListaTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        l = new KasiLista(10);
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void ekaSolmuNullAluksi() {
        assertEquals(null, l.getEkaSolmu());
    }
    
    @Test
    public void viimeisinPariOikein() {
        Kasipari pari = new Kasipari(0,1);
        this.l.lisaaKasipari(pari);
        assertEquals(pari, l.getViimeisinPari());
    }
    
    @Test
    public void ekaSolmuOikein() {
        Kasipari pari = new Kasipari(0,1);
        this.l.lisaaKasipari(pari);
        Listasolmu s = new Listasolmu(pari, null);
        assertEquals(s.getKasipari(), this.l.getEkaSolmu().getKasipari());
    }
    
    @Test
    public void getViimeisinOikeinIsommallaSyotteella() {
        for (int i=0; i<7; i++) {
            this.l.lisaaKasipari(new Kasipari(0,1));
        }
        Kasipari pari = new Kasipari(1,1);
        this.l.lisaaKasipari(pari);
        assertEquals(pari, this.l.getViimeisinPari());
    }
    
    @Test
    public void getEkaSolmuOikeinIsomallaSyotteella() {
        for (int i=0; i<7; i++) {
            this.l.lisaaKasipari(new Kasipari(0,1));
        }
        Kasipari pari = new Kasipari(1,1);
        this.l.lisaaKasipari(pari);
        Listasolmu s = new Listasolmu(pari, null);
        assertEquals(s.getKasipari(), this.l.getEkaSolmu().getKasipari());
    }
}
