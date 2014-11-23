/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package superpakkaussofta;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Jouko
 */
public class NodeHeapTest {
    
    NodeHeap heap;
    
    public NodeHeapTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        byte[] data = {10, 10, 10, 20, 20};
        heap = new NodeHeap(data);
    }
    
    @After
    public void tearDown() {
    }
    
    /**
     * Tests building a heap from a byte array
     */
    @Test
    public void testBuildHeapFromData(){
        byte[] b = {20, 10, 20};
        
        NodeHeap res = new NodeHeap(b);
        NodeHeap exp = new NodeHeap();
        exp.add(new HuffmanNode((byte) 20, 2));
        exp.add(new HuffmanNode((byte) 10, 1));
        
        assertEquals(exp, res);
    }
    
    /**
     * Tests building a heap from a String
     */
    @Test
    public void testBuildHeapFromString(){
        
        NodeHeap res = new NodeHeap("20a2b10a1b");
        NodeHeap exp = new NodeHeap();
        exp.add(new HuffmanNode((byte) 20, 2));
        exp.add(new HuffmanNode((byte) 10, 1));
        
        assertEquals(exp, res);
    }
    
    /**
     * Test of add method, of class NodeHeap.
     */
    @Test
    public void testAdd() {
        HuffmanNode n = new HuffmanNode((byte) 30, 1);
        heap.add(n);
        
        assertEquals(new HuffmanNode((byte) 30, 1), heap.poll());
    }
    /**
     * Test of poll method, of class NodeHeap.
     */
    @Test
    public void testPoll() {
        
        HuffmanNode eres = new HuffmanNode((byte) 20, 2);
        HuffmanNode res = heap.poll();
        if(heap.size() != 1){
            fail("Poll didn't remove the head!");
        }
        assertEquals(eres, res);
    }

    /**
     * Test of peek method, of class NodeHeap.
     */
    @Test
    public void testPeek() {
        HuffmanNode eres = new HuffmanNode((byte) 20, 2);
        HuffmanNode res = heap.peek();
        if(heap.size() != 2){
            fail("Peek removed the head or did something else..");
        }
        assertEquals(eres, res);
    }

    /**
     * Test of size method, of class NodeHeap.
     */
    @Test
    public void testSize() {
        HuffmanNode n = new HuffmanNode((byte) 30, 1);
        heap.add(n);
        
        assertEquals(3, heap.size());
    }
    
}
