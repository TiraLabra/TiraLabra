package com.mycompany.tiralabra_maven;

import com.mycompany.tiralabra_maven.DataStructures.MyPriorityQueue;
import junit.framework.TestCase;

public class MyPriorityQueueTest extends TestCase {
    protected Node node1;
    protected Node node2;
    protected Node node3;
    protected Node node4;
    protected Node node5;
    protected MyPriorityQueue queue;


    protected void setUp() {
        queue = new MyPriorityQueue(100);
        node1 = new Node(1, 1);
        node2 = new Node(2, 2);
        node3 = new Node(3, 3);
        node4 = new Node(4, 4);
        node5 = new Node(5, 5);
        node1.setCost(1);
        node2.setCost(2);
        node3.setCost(3);
        node4.setCost(4);
        node5.setCost(5);
        node1.setHeuristic(1);
        node2.setHeuristic(2);
        node3.setHeuristic(3);
        node4.setHeuristic(4);
        node5.setHeuristic(5);
    }


    /** To be added after MinHeap works fully
     */
    public void testDummy()
    {
        assertTrue(queue.getHeapSize() == 0);
    }

    /** Tests inserting into PriorityQueue works
     */
    public void testInsert()
    {
        MyPriorityQueue queue = new MyPriorityQueue(5);
        Node node = new Node(1,1);
        queue.insert(node);
        assertTrue(queue.getHeapSize() == 1);
        assertTrue(queue.min().isSameNode(node));
    }


    /** Tests priority queue deletes minimum entry from the queue: */
    public void testDeleteMin()
    {
        queue.insert(node3);
        queue.insert(node2);
        queue.insert(node1);

        assertTrue(queue.min().isSameNode(node1));

        queue.deleteMinimum();

        assertFalse(queue.contains(node1));
    }

    /** Tests priority queue decreases cost of node */
    public void testDecreaseCost()
    {
        queue.insert(node1);
        queue.decreaseCost(queue.indexOf(node1), 0);

        assertTrue(queue.min().getHeuristic() == 0);
    }


    /** Tests priority queue removes wanted node from queue: */
    public void testRemoveNode()
    {
        queue.insert(node5);
        queue.insert(node4);
        queue.insert(node3);
        queue.insert(node2);
        queue.insert(node1);

        assertTrue(queue.min().isSameNode(node1));

        queue.removeNode(node3);

        assertFalse(queue.contains(node3));

        assertTrue(queue.min().isSameNode(node1));
    }

    /** Tests priority queue heapifies entries correctly: */
    public void testHeapifyWorks()
    {
        queue.insert(node5);
        queue.insert(node4);
        queue.insert(node3);
        queue.insert(node2);
        queue.insert(node1);

        assertTrue(queue.min().isSameNode(node1));

        String test = "";
        Node[] table = queue.getTable();
        for (int i = 0; i < queue.getHeapSize(); i++) {
           test += (table[i].getCost() + " ");
        }
        assertTrue(test.equals("1 2 4 5 3 "));

    }


}