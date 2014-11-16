package com.mycompany.tiralabra_maven;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

/** jUnit tests for Astar-class
 *  currently tests non-main methods
 */
public class AstarTest {

    Node node;

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
        node = new Node(0,0);
    }

    @After
    public void tearDown() {
    }

    public void test() {

    }

    /** Tests that isWalkable-method returns true if node is walkable
     *
     */
    @org.junit.Test
    public void testNodeIsWalkable() {
        assertTrue(Astar.isWalkable(node, 1, 1));

    }

    /** Tests that isWalkable-method returns false if node is unwalkable
     *
     */
    @org.junit.Test
    public void testUnwalkableNode() {
        Node unwalkable = new Node(1,2);
        assertFalse(Astar.isWalkable(unwalkable, 1, 1));

    }

    /** Tests that calculateHeuristic-method gives correct results
     *
     */
    @org.junit.Test
    public void testCalculateHeuristic() {
        Node end = new Node(2,2);
        assertTrue(Astar.calculateHeuristic(node, end) == 4);


        Node end2 = new Node(4,4);
        assertTrue(Astar.calculateHeuristic(node, end2) == 8);
    }

    /** Tests that the calculateEuclidean-method gives correct results */
    @org.junit.Test
    public void testCalculateEuclidean() {
        Node test1 = new Node(2,-1);
        assertTrue(Astar.calculateEuclidean(-2,2, test1) == 5);
    }


}
