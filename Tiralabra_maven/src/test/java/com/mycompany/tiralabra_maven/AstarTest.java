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
 * @author Tiina
 */
public class AstarTest {
    
    public AstarTest() {
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

    /**
     * Test of seuraavaNode method, of class Astar.
     */
    @Test
    public void testSeuraavaNode() {
        System.out.println("seuraavaNode");
        Node current = null;
        Astar instance = new Astar();
        instance.seuraavaNode(current);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of Heuristics method, of class Astar.
     */
    @Test
    public void testHeuristics() {
        System.out.println("Heuristics");
        Node node = null;
        Astar instance = new Astar();
        int expResult = 0;
        int result = instance.Heuristics(node);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
