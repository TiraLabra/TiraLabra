package com.mycompany.tira;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class ListaSolmuTest {
    
    private ListaSolmu l;
    private Kasipari k;
    private ListaSolmu seur;
    
    public ListaSolmuTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        seur = new ListaSolmu(new Kasipari(1,3,1), null);
        k = new Kasipari(1, 3, 1);
        l = new ListaSolmu(k, seur);
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void getKasipariToimi() {
        assertEquals(k, l.getKasipari());
    }
    
    @Test
    public void getKasipariToimiiKaksi() {
        assertNotEquals(new Kasipari(1,2,1), l.getKasipari());
    }
    
    @Test
    public void seuraavaSolmuOikein() {
        assertEquals(seur, l.getSeuraavaListaSolmu());
    }
    
    @Test
    public void seuravaSolmuOikeinKaksi() {
        assertNotEquals(new ListaSolmu(new Kasipari(1,2,1), null), l.getSeuraavaListaSolmu());
    }
    
    @Test
    public void setListasolmuToimii() {
        ListaSolmu uusi = new ListaSolmu(new Kasipari(3,1,0), null);
        assertNotEquals(uusi, l.getSeuraavaListaSolmu());
        l.setSeuraavaListaSolmu(uusi);
        assertEquals(uusi, l.getSeuraavaListaSolmu());
    }
}
