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
        assertTrue(atLeast(heap.getNode(0).getDistance(), heap.getNode(3).getDistance()));
        assertTrue(atLeast(heap.getNode(3).getDistance(), heap.getNode(10).getDistance()));
        assertTrue(atLeast(heap.getNode(2).getDistance(), heap.getNode(6).getDistance()));
    }

    @Test
    public void minHeapPopCorrectly() {
        int value = heap.getNode(0).getDistance();
        Node popped = heap.removeMin();
        assertEquals(value, popped.getDistance());
        value = heap.getNode(0).getDistance();
        popped = heap.removeMin();
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

        heap.decHeap(2, 1);

        assertEquals(1, heap.getNode(0).getDistance());




    }

    public boolean atLeast(int x, int y) {
        if (x <= y) {
            return true;
        }
        return false;

    }
}
