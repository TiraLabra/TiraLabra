/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package HikeTests;

import Hike.Graph.Node;
import Hike.Structures.MinHeap;
import java.util.Random;
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
public class MinHeapTests {

    private MinHeap heap;

    public MinHeapTests() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {



        Random random = new Random();
        heap = new MinHeap(20);
        for (int i = 0; i < 20; i++) {
            Node node = new Node(1, 1, 5);
            node.setDistance(random.nextInt(20));
            heap.insert(node);
        }

    }

    @After
    public void tearDown() {
    }
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //

    @Test
    public void minHeapCorrectOrder() {
        assertTrue(atLeast(heap.getElement(0).getNode().getDistance(), heap.getElement(3).getNode().getDistance()));
        assertTrue(atLeast(heap.getElement(3).getNode().getDistance(), heap.getElement(10).getNode().getDistance()));
        assertTrue(atLeast(heap.getElement(2).getNode().getDistance(), heap.getElement(6).getNode().getDistance()));
    }

    @Test
    public void minHeapPopCorrectly() {
        int value = heap.getElement(0).getNode().getDistance();
        Node popped = heap.removeMin().getNode();
        assertEquals(value, popped.getDistance());
        value = heap.getElement(0).getNode().getDistance();
        popped = heap.removeMin().getNode();
        assertEquals(value, popped.getDistance());
        assertEquals(17, heap.getLast());
    }

    @Test
    public void minHeapDecrementTest() {
        heap = new MinHeap(6);
        Node node = new Node(1, 1, 1);
        node.setDistance(5);
        heap.insert(node);
        node = new Node(1, 1, 1);
        node.setDistance(5);
        heap.insert(node);
        node = new Node(1, 1, 1);
        node.setDistance(5);
        heap.insert(node);
        heap.insert(node);
        heap.insert(node);
        heap.insert(node);
        heap.decHeap(0, 1);
        heap.printHeap();
        assertEquals(0, heap.getElement(0).getNode().getDistance());
        heap.printHeap();



    }

    public boolean atLeast(int x, int y) {
        if (x <= y) {
            return true;
        }
        return false;

    }
}
