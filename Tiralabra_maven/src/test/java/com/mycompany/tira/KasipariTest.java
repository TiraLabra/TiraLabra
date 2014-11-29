package com.mycompany.tira;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class KasipariTest {
    
    private Kasipari k;
    
    public KasipariTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        k = new Kasipari(1,3,1);
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void getPelaajanKasiToimii() {
        assertEquals(1, k.getPelaajanKasi());
    }
    
    @Test
    public void getKoneenKasiToimii() {
        assertEquals(3, k.getKoneenKasi());
    }
    
    @Test
    public void getVoittajaToimii() {
        assertEquals(1, k.getVoittaja());
    }
}
