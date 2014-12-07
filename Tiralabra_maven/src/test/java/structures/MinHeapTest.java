package structures;

import map.Node;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 */
public class MinHeapTest {
    
    MinHeap minHeap;
    
    public MinHeapTest() {
    }
    
    @Before
    public void setUp() {
        minHeap = new MinHeap(10, 10);
    }
    
    @Test
    public void addingToEmptyHeapWorks() {
        Node node = new Node(1, 2);
        node.setF(2);
        minHeap.add(node);
        assertEquals("(1, F:2) -> ", minHeap.toString());
    }
    
    @Test
    public void addingTwoElements() {
        Node node = new Node(1, 2);
        node.setF(1);
        Node node2 = new Node(1, 2);
        node2.setF(6);
        minHeap.add(node);
        minHeap.add(node2);
        assertEquals("(1, F:1) -> (2, F:6) -> ", minHeap.toString());
    }
    
    @Test
    public void addingManyElementsWorks() {
        Node node = new Node(1, 2);
        node.setF(1);
        Node node2 = new Node(1, 2);
        node2.setF(6);
        Node node3 = new Node(1, 2);
        node3.setF(4);
        Node node4 = new Node(1, 2);
        node4.setF(5);
        minHeap.add(node);
        minHeap.add(node2);
        minHeap.add(node3);
        minHeap.add(node4);
        assertEquals("(1, F:1) -> (2, F:5) -> (3, F:4) -> (4, F:6) -> ", minHeap.toString());
    }
    
    @Test
    public void popElement() {
        Node node = new Node(1, 2);
        node.setF(1);
        Node node2 = new Node(1, 2);
        node2.setF(6);
        Node node3 = new Node(1, 2);
        node3.setF(4);
        Node node4 = new Node(1, 2);
        node4.setF(5);
        minHeap.add(node);
        minHeap.add(node2);
        minHeap.add(node3);
        minHeap.add(node4);
        minHeap.pop();
        assertEquals("(1, F:4) -> (2, F:5) -> (3, F:6) -> ", minHeap.toString());
    }
    
    @Test
    public void popToEmpty() {
        Node node = new Node(1, 2);
        node.setF(2);
        minHeap.add(node);
        minHeap.pop();
        assertEquals("empty heap", minHeap.toString());
    }
}
