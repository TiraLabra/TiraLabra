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
public class BFSTest {
    
    public BFSTest() {
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
    public void shortestPathTest() {
        Graph myGraph = new Graph();
        Vertex a = new Vertex("A");
        Vertex b = new Vertex("B");
        Vertex c = new Vertex("C");
        Vertex d = new Vertex("D");
        Vertex e = new Vertex("E");
        Vertex f = new Vertex("F");
        Vertex g = new Vertex("G");
        Vertex h = new Vertex("H");
        Vertex i = new Vertex("I");
        myGraph.connect(a, h, 1);
        myGraph.connectBothWays(a, b, 1);
        myGraph.connectBothWays(a, d, 1);
        myGraph.connectBothWays(b, c, 1);
        myGraph.connectBothWays(h, i, 1);
        myGraph.connect(i, d, 1);
        myGraph.connectBothWays(d, e, 1);
        myGraph.connect(d, c, 1);
        myGraph.connectBothWays(c, f, 1);
        myGraph.connectBothWays(e, g, 1);
        myGraph.connectBothWays(f, g, 1);
        assertEquals(myGraph.shortestPath(a, g, new BellmanFord(myGraph)).getLength(),myGraph.shortestPath(a, g, new BFS(myGraph)).getLength());
        assertEquals(myGraph.shortestPath(a, g, new Dijkstra(myGraph)).getLength(),myGraph.shortestPath(a, g, new BFS(myGraph)).getLength());
    }
}