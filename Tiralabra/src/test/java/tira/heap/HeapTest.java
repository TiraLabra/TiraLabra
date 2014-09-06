package tira.heap;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import tira.common.Node;

/**
 *
 * @author joonaslaakkonen
 */
public class HeapTest {
    
    private Heap<Node> keko;
    private Node a;
    private Node b;
    private Node c;
    
    public HeapTest() {
    }
    
    @Before
    public void setUp() {
        keko = new Heap<Node>(20);
        a = new Node("aa");
        b = new Node("bb");
        c = new Node("cc");
        a.setShortest(5);
        b.setShortest(10);
        c.setShortest(15);
        keko.insert(c);
        keko.insert(b);
        keko.insert(a);
    }

    /**
     * Test of insert method, of class Heap.
     */
    @Test
    public void testInsert() {      
        Node d = new Node("dd");
        d.setShortest(12);
        assertFalse(keko.empty());
        assertEquals(3, keko.size());
        keko.insert(d);
        assertEquals(4, keko.size());
    }

    /**
     * Test of poll method, of class Heap.
     */
    @Test
    public void testPoll() {
        Node o = keko.poll();
        assertTrue(o.equals(a));
        Node d = new Node("dd");
        d.setShortest(4);
        keko.insert(d);
        assertTrue(keko.poll().equals(d));
    }

    /**
     * Test of empty method, of class Heap.
     */
    @Test
    public void testEmpty() {
        assertEquals(false, keko.empty());
        keko.poll();
        keko.poll();
        keko.poll();
        assertTrue(keko.empty());
        assertNull(keko.poll());
        assertEquals(0, keko.size());
    }
    
    /**
     * Test decreaseKey methdod, of class Heap.
     */
    @Test
    public void testDecreaseKey() {
        Node eka = new Node("eka");
        Node toka = new Node("toka");
        assertTrue(this.keko.size()==3);
        eka.setShortest(24);
        toka.setShortest(35);
        keko.insert(eka);
        keko.insert(toka);
        toka.setShortest(3);
        keko.decreaseKey(toka);
        assertTrue(this.keko.size()==5);
        Node ekana = keko.poll();
        assertTrue(ekana.equals(toka));
        Node tokana = keko.poll();
        assertTrue(tokana.equals(this.a));
        eka.setShortest(7);
        keko.decreaseKey(eka);
        Node kolmas = keko.poll();
        assertTrue(kolmas.equals(eka));
        assertTrue(keko.size()==2);
    }
}