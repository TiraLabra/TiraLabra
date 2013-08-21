/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Structures.Graph;

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
public class GraphTest {
    public GraphTest() {
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
    public void connectAndDisconnectTest() {
        Graph g = new Graph();
        Vertex a = new Vertex("A");
        Vertex b = new Vertex("B");
        Vertex c = new Vertex("C");
        g.connect(a, b);
        g.connect(b, c);
        assertEquals(true,g.isConnected(a, b));
        assertEquals(false,g.isConnected(a, c));
        g.disconnect(a, b);
        assertEquals(false,g.isConnected(a, b));
        g.disconnect(b, c);
        assertEquals(false,g.isConnected(b, c));
        g.connectBothWays(b, c);
        assertEquals(true,g.isConnected(b, c));
        assertEquals(true,g.isConnected(c, b));
    }
    public void setWeightTest(){
        Graph g = new Graph();
        Vertex a = new Vertex("A");
        Vertex b = new Vertex("B");
        Vertex c = new Vertex("C");
        g.connect(a, b, 3);
        g.connect(b, c, -4);
        assertEquals(3, g.getWeight(a, b));
        g.setWeight(a, b, 2);
        assertEquals(2, g.getWeight(a, b));
    }
}