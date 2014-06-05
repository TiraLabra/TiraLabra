/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package HikeTests;

import Hike.Algorithms.Dijkstra;
import Hike.Graph.Node;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author petri
 */
public class DijkstraTest {

    private Node[][] t;
    private Node[][] nodeTable;

    public DijkstraTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        t = new Node[4][4];

        // Creates table for testing. Weights:
        // 0-5-5-5
        // 5-5-5-5
        // 5-5-5-5
        // 5-5-5-5

        t[0][0] = new Node(1, 1, 0);
        t[0][1] = new Node(1, 1, 5);
        t[0][2] = new Node(1, 1, 5);
        t[0][3] = new Node(1, 1, 5);
        t[1][0] = new Node(1, 1, 5);
        t[1][1] = new Node(1, 1, 5);
        t[1][2] = new Node(1, 1, 5);
        t[1][3] = new Node(1, 1, 5);
        t[2][0] = new Node(1, 1, 5);
        t[2][1] = new Node(1, 1, 5);
        t[2][2] = new Node(1, 1, 5);
        t[2][3] = new Node(1, 1, 5);
        t[3][0] = new Node(1, 1, 5);
        t[3][1] = new Node(1, 1, 5);
        t[3][2] = new Node(1, 1, 5);
        t[3][3] = new Node(1, 1, 5);
        t[0][0].setNeighbours(t, 4, 4);
        nodeTable = t[0][0].getTable();

    }

    @After
    public void tearDown() {
    }

    @Test
    public void ShortestRouteInTableWithIdenticalValues() { // Straight Diagonally, 5+5+5.
        t[0][0].printTable();
        Dijkstra route = new Dijkstra(t);
        System.out.println("");
        t = route.getDijkstraTable();
        t[0][0].printTable();
        assertEquals(15, t[3][3].getDistance());

    }

    @Test
    public void ShortestRouteInTableWithEasyTopRowAndRight() { // Top row and rightmost row easy, should be 1+1+1+1+1 = 5 with diagonal jump in corner;
        t[0][0].setWeight(1);
        t[0][1].setWeight(1);
        t[0][2].setWeight(1);
        t[0][3].setWeight(1);
        t[1][3].setWeight(1);
        t[2][3].setWeight(1);
        t[3][3].setWeight(1);
        Dijkstra route = new Dijkstra(t);
        t = route.getDijkstraTable();
        t[0][0].printTable();
        assertEquals(5, t[3][3].getDistance());

    }

    @Test
    public void ShortestRouteInTableWithEasyBottomRowAndLeft() { //Should be 5;
        t[0][0].setWeight(1);
        t[1][0].setWeight(1);
        t[2][0].setWeight(1);
        t[3][0].setWeight(1);
        t[3][1].setWeight(1);
        t[3][2].setWeight(1);
        t[3][3].setWeight(1);
        Dijkstra route = new Dijkstra(t);
        t = route.getDijkstraTable();
        assertEquals(5, t[3][3].getDistance());

    }

    @Test
    public void correctPathPrinting() { //Uses the simple table. Should be diagonally up and left from 3,3.
        Dijkstra route = new Dijkstra(t);
        t = route.getDijkstraTable();
        route.buildPath(3, 3);
        Node test = route.nextPath();
        assertEquals(t[2][2].getX(), test.getX());
        assertEquals(t[2][2].getY(), test.getY());
        test = route.nextPath();
        assertEquals(t[1][1].getX(), test.getX());
        assertEquals(t[1][1].getY(), test.getY());
        test = route.nextPath();
        assertEquals(t[0][0].getX(), test.getX());
        assertEquals(t[0][0].getY(), test.getY());


    }

    @Test
    public void ShortestRouteInLargeMap() {
        int v = 6;
        int v2 = 6;
        t = new Node[v][v2];
        for (int i = 0; i < v; i++) {
            for (int z = 0; z < v2; z++) {
                Node test = new Node(1,1,z);
                t[i][z] = test;
                
            }
        }
        t[0][0].setNeighbours(t, v, v2);
        Dijkstra route = new Dijkstra(t);
        System.out.println(route.nextPath());
        t[0][0].getNeighbours();
        route.getDijkstraTable()[0][0].printTable();
        
        

    }
}