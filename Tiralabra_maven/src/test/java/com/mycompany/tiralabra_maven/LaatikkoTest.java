/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mycompany.tiralabra_maven;

import com.mycompany.tiralabra_maven.domain.Laatikko;
import com.mycompany.tiralabra_maven.domain.Laatikkotyyppi;
import com.mycompany.tiralabra_maven.domain.Sijainti;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Sami
 */
public class LaatikkoTest {
    
    private Laatikko l;
    
    public LaatikkoTest() {
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
    public void orientaatiotOikein(){
        Laatikkotyyppi lt = new Laatikkotyyppi(11, 12, 13);
        
        this.l = new Laatikko(lt, new Sijainti(), 0);
        assertEquals(11, this.l.getX());
        assertEquals(12, this.l.getY());
        assertEquals(13, this.l.getZ());
        
        this.l.setOrientaatio(1);
        assertEquals(11, this.l.getX());
        assertEquals(13, this.l.getY());
        assertEquals(12, this.l.getZ());
        
        this.l.setOrientaatio(2);
        assertEquals(12, this.l.getX());
        assertEquals(11, this.l.getY());
        assertEquals(13, this.l.getZ());
        
        this.l.setOrientaatio(3);
        assertEquals(12, this.l.getX());
        assertEquals(13, this.l.getY());
        assertEquals(11, this.l.getZ());
        
        this.l.setOrientaatio(4);
        assertEquals(13, this.l.getX());
        assertEquals(12, this.l.getY());
        assertEquals(11, this.l.getZ());
        
        this.l.setOrientaatio(5);
        assertEquals(13, this.l.getX());
        assertEquals(11, this.l.getY());
        assertEquals(12, this.l.getZ());
    }
}
