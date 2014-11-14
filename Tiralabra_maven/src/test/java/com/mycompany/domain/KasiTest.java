package com.mycompany.domain;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class KasiTest {
    
    public KasiTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void kadenNimiOnOikein()  {
        Kasi k = new Kasi("ASDF");
        assertEquals("ASDF", k.getNimi());
    }
    
    @Test
    public void kadenNimiOnIsolla() {
        Kasi k = new Kasi("pieni");
        assertEquals("PIENI", k.getNimi());
    }
    
}
