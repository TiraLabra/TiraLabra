/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilities;

import Domain.Koordinaatit;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Emleri
 */
public class KoordinaattiNodeTest {
    KoordinaattiNode n;
    
    public KoordinaattiNodeTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        n = new KoordinaattiNode(new Koordinaatit(0,0));
    }
    
    @After
    public void tearDown() {
    }
    
    // Yksinkertaiset getterit ja setterit toistaiseksi testaamatta.
    
    @Test
    public void isViimeinenTest() {
        assertTrue(n.isViimeinen());
        n.setSeuraava(new KoordinaattiNode(new Koordinaatit(0,0)));
        assertTrue(!n.isViimeinen());
    }
}
