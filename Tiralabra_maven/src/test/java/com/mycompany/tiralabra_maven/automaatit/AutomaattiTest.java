/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.tiralabra_maven.automaatit;

import com.mycompany.tiralabra_maven.tietorakenteet.Jono;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;

/**
 *
 * @author John Lång <jllang@cs.helsinki.fi>
 */
public class AutomaattiTest {
    
    public AutomaattiTest() {
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

    @Ignore // Kesken...
    @Test
    public void testKasittele() {
        Jono<String> s = new Jono<String>();
        s.lisaa("a");
        s.lisaa("b");
        s.lisaa("|");
        Automaatti a = new Automaatti(s);
        assertFalse(a.kasittele());
    }
}