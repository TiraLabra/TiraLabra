package com.mycompany.logiikka;

import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class TekoalyTest {

    private Tekoaly t;
    private Tekoaly laaja;

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
        this.t = new Tekoaly(1);
        this.laaja = new Tekoaly(2);
        
    }

    @After
    public void tearDown() {
    }

    @Test
    public void koneenKasiOikeinAlussaMoodiYksi() {
        assertEquals(1, this.t.getKoneenKasi());
    }
    
    @Test
    public void koneenKasiOikeinAlussaMoodiKaksi() {
        assertEquals(1, this.t.getKoneenKasi());
    }

}
