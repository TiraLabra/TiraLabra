/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package HikeTests.Dijkstra;

import Hike.algorithms.Pathfinder;
import Hike.graph.Node;

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

        int h = 4;
        int w = 4;
        t = new Node[h][w];

        // Creates table for testing. Weights:
        // 0-5-5-5
        // 5-5-5-5
        // 5-5-5-5
        // 5-5-5-5

        for (int i = 0; i < h; i++) {
            for (int y = 0; y < w; y++) {
                Node node = new Node(i, y, 5);
                t[i][y] = node;

            }
        }
        t[0][0].setNeighbours(t);
        nodeTable = t[0][0].getTable();


    }

    @After
    public void tearDown() {
    }

    @Test
    public void ShortestRouteInTableWithIdenticalValues() { // 
        Pathfinder route = new Pathfinder(nodeTable, 3, 3, "Dijkstra");

        nodeTable = route.getRouteTable();
        assertEquals(30, (int) nodeTable[3][3].getDistance());

    }

    @Test
    public void ShortestRouteInTableWithEasyTopRowAndRight() { // Top row and rightmost row easy, should be 1+1+1+1+1+1 = 6
        nodeTable[0][0].setWeight(1);
        nodeTable[0][1].setWeight(1);
        nodeTable[0][2].setWeight(1);
        nodeTable[0][3].setWeight(1);
        nodeTable[1][3].setWeight(1);
        nodeTable[2][3].setWeight(1);
        nodeTable[3][3].setWeight(1);
        nodeTable[0][0].setNeighbours(t);
        Pathfinder route = new Pathfinder(nodeTable, 3, 3, "Dijkstra");
        nodeTable = route.getRouteTable();
        assertEquals(6, (int) nodeTable[3][3].getDistance());

    }

    @Test
    public void ShortestRouteInTableWithEasyBottomRowAndLeft() { //Should be 6;
        nodeTable[0][0].setWeight(1);
        nodeTable[1][0].setWeight(1);
        nodeTable[2][0].setWeight(1);
        nodeTable[3][0].setWeight(1);
        nodeTable[3][1].setWeight(1);
        nodeTable[3][2].setWeight(1);
        nodeTable[3][3].setWeight(1);

        nodeTable[0][0].setNeighbours(t);
        Pathfinder route = new Pathfinder(nodeTable, 3, 3, "Dijkstra");
        nodeTable = route.getRouteTable();
        assertEquals(6, (int) nodeTable[3][3].getDistance());

    }

    @Test
    public void largeDijkstra() {
        nodeTable = new Node[10][10];
        for (int i = 0; i < 10; i++) {
            for (int y = 0; y < 10; y++) {
                Node node = new Node(i, y, 50);
                nodeTable[i][y] = node;
            }

        }
        nodeTable[0][0].setNeighbours(nodeTable);
        Pathfinder bigroute = new Pathfinder(nodeTable, 9, 9, "Dijkstra");
        assertEquals(18 * 50, (int) bigroute.getRouteTable()[9][9].getDistance());

    }

    @Test
    public void ludicrousSizeDijkstra() {
        nodeTable = new Node[1000][1000];
        for (int i = 0; i < 1000; i++) {
            for (int y = 0; y < 1000; y++) {
                Node node = new Node(i, y, 1);
                nodeTable[i][y] = node;
            }

        }
        nodeTable[0][0].setNeighbours(nodeTable);
        Pathfinder ludicrous = new Pathfinder(nodeTable, 1000 - 1, 1000 - 1, "Dijkstra");
        assertEquals((1000 - 1 + 1000 - 1), (int) nodeTable[1000 - 1][1000 - 1].getDistance());

    }
}