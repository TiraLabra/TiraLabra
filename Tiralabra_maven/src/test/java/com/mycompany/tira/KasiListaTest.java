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

//    @Test
//    public void ekaSolmuNullAluksi() {
//        assertEquals(null, l.getEnsimmainenSolmu());
//    }
//    
//    @Test
//    public void ekaSolmuOikein() {
//        ListaSolmu s = new ListaSolmu(new Kasipari(1,3,1), null);
//        l.lisaaKasipari(new Kasipari(1,3,1));
//        assertEquals(s.getKasipari().getPelaajanKasi(), l.getEnsimmainenSolmu().getKasipari().getPelaajanKasi());
//        assertEquals(s.getKasipari().getKoneenKasi(), l.getEnsimmainenSolmu().getKasipari().getKoneenKasi());
//        assertEquals(s.getKasipari().getVoittaja(), l.getEnsimmainenSolmu().getKasipari().getVoittaja());
//    }
//    
//    @Test
//    public void ekaSolmuOikeinKaksi() {
//        ListaSolmu s = new ListaSolmu(new Kasipari(3,1,-1), null);
//        for (int i=0; i<3; i++) {
//            l.lisaaKasipari(new Kasipari(1,3,1));
//        }
//        l.lisaaKasipari(new Kasipari(3,1,-1));
//        assertEquals(s.getKasipari().getPelaajanKasi(), l.getEnsimmainenSolmu().getKasipari().getPelaajanKasi());
//        assertEquals(s.getKasipari().getKoneenKasi(), l.getEnsimmainenSolmu().getKasipari().getKoneenKasi());
//        assertEquals(s.getKasipari().getVoittaja(), l.getEnsimmainenSolmu().getKasipari().getVoittaja());
//    }
//    
//    @Test
//    public void ekaSolmuOikeinListaYmpari() {
//        ListaSolmu s = new ListaSolmu(new Kasipari(3,1,-1), null);
//        for (int i=0; i<55; i++) {
//            l.lisaaKasipari(new Kasipari(1,3,1));
//        }
//        l.lisaaKasipari(new Kasipari(3,1,-1));
//        assertEquals(s.getKasipari().getPelaajanKasi(), l.getEnsimmainenSolmu().getKasipari().getPelaajanKasi());
//        assertEquals(s.getKasipari().getKoneenKasi(), l.getEnsimmainenSolmu().getKasipari().getKoneenKasi());
//        assertEquals(s.getKasipari().getVoittaja(), l.getEnsimmainenSolmu().getKasipari().getVoittaja());
//    }
    
}
