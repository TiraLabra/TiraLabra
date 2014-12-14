package com.mycompany.tiralabra_maven;

import junit.framework.TestCase;

public class HeuristicTest extends TestCase {

    /** Test that euclidean distance is counted right
     */
    public void testEuclideanCountingWorks()
    {
        Heuristic heuristic = new Heuristic(1);
        int xp = 3;
        int yp = 4;
        Node end = new Node(0,0);
        double cost = heuristic.cost(xp, yp, end);
        assertTrue(cost == 5);
    }

    /** Test that Manhattan distance is counted right
     */
    public void testManhattanCountingWorks()
    {
        Heuristic heuristic = new Heuristic(2);
        int xp = 4;
        int yp = 4;
        Node end = new Node(0,0);
        double cost = heuristic.cost(xp, yp, end);
        assertTrue(cost == 8);
    }
    /** Test that Diagonal distance is counted right
     */
    public void testDiagonalCountingWorks()
    {
        Heuristic heuristic = new Heuristic(3);
        int xp = 4;
        int yp = 4;
        Node end = new Node(0,0);
        double cost = heuristic.cost(xp, yp, end);
        assertTrue(cost == 8);
    }
    /** Test that selecting Dijkstra simply returns zero
     */
    public void testDijkstraReturnsZero()
    {
        Heuristic heuristic = new Heuristic(0);
        int xp = 3;
        int yp = 4;
        Node end = new Node(0,0);
        double cost = heuristic.cost(xp, yp, end);
        assertTrue(cost == 0);
    }




}
