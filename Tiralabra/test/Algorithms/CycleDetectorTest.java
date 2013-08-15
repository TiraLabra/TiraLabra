/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Algorithms;

import Structures.Graph.Graph;
import Structures.Graph.Vertex;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Kalle
 */
public class CycleDetectorTest {
    public CycleDetectorTest() {
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
     * Test of hasCycle method, of class CycleDetector.
     */
    @Test
    public void hasCycleTest() {
        Graph g = new Graph();
        Vertex a = new Vertex("A");
        Vertex b = new Vertex("B");
        g.connectBothWays(a, b);
        assertEquals(true,g.hasCycle());
        g.disconnect(b, a);
        assertEquals(false,g.hasCycle());
        Vertex c = new Vertex("C");
        g.connect(b, c);
        assertEquals(false,g.hasCycle());
        g.connect(c, a);
        assertEquals(true,g.hasCycle());
        g.disconnect(c, a);
        assertEquals(false,g.hasCycle());
    }
}