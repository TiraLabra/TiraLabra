/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Nimetön;

import Algoritmi.Solmu;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Jaakko
 */
public class SolmuTest {
    
    public SolmuTest() {
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
    public void testKonstruktori() {
       
        Solmu testi = new Solmu(1, 2, 3);
        
        assertEquals(testi.Edeltävä, null);
        assertEquals(testi.Heurestiikaarvo, 3);
        assertEquals(testi.koordinaattiX, 1);
        assertEquals(testi.koordinaattiY, 2);
        assertFalse(testi.seinä);
        
    }
    
}
