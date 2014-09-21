/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mycompany.tiralabra_maven;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author iivo
 */
public class VerkkoTest {
    
    public VerkkoTest() {
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
    public void konstruktori() {
        Verkko verkko = new Verkko();
        assertTrue(verkko.getSolmut().onkoTyhja());
    }
    
    @Test
    public void solmunLisays() {
        Verkko verkko = new Verkko();
        Solmu solmu = new Solmu(0, 0, 0);
        
        verkko.lisaaSolmu(solmu);
        assertFalse(verkko.getSolmut().onkoTyhja());
        assertEquals(1, verkko.getSolmut().koko());
    }
    
    @Test
    public void solmunHaku() {
        Verkko verkko = new Verkko();
        Solmu solmu = new Solmu(1, 2, 0);
        
        verkko.lisaaSolmu(solmu);
        assertSame(solmu, verkko.getSolmu(1, 2));
        assertNull(verkko.getSolmu(3,3));
    }
    
    @Test
    public void vieruslistojenLuonti() {
        
        /*
        000
        083
        #00
        */
        Verkko verkko2 = new Verkko();
        
        Solmu solmu1 = new Solmu(0, 0, 0);
        Solmu solmu5 = new Solmu(1, 1, 8);
        Solmu solmu8 = new Solmu(1, 2, 0);
        
        //Luo solmut
        verkko2.lisaaSolmu( solmu1 );
        verkko2.lisaaSolmu( new Solmu(1, 0, 0) );
        verkko2.lisaaSolmu( new Solmu(2, 0, 0) );
        verkko2.lisaaSolmu( new Solmu(0, 1, 0) );
        verkko2.lisaaSolmu( solmu5 );
        verkko2.lisaaSolmu( new Solmu(2, 1, 3) );
        verkko2.lisaaSolmu( new Solmu(0, 2, -1) );
        verkko2.lisaaSolmu( solmu8 );
        verkko2.lisaaSolmu( new Solmu(2, 2, 0) );
        
        verkko2.luoVieruslistat();
        
        assertEquals(2, solmu1.getVierus().koko());
        assertEquals(4, solmu5.getVierus().koko());
        assertEquals(2, solmu8.getVierus().koko());
    }
}
