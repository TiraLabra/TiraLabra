package tira.list;

import java.util.Iterator;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import tira.common.Node;

/**
 *
 * @author joonaslaakkonen
 */
public class LinkedListTest {
    
    private LinkedList<Node> lista;
    private Node a;
    private Node b;
    private Node c;
    
    public LinkedListTest() {
    }
    
    
    @Before
    public void setUp() {
        lista = new LinkedList<Node>();
        a = new Node("aa");
        b = new Node("bb");
        c = new Node("cc");
        a.setShortest(5);
        b.setShortest(10);
        c.setShortest(15);     
    }
    

    /**
     * Test of empty method, of class LinkedList.
     */
    @Test
    public void testEmpty() {
        assertEquals(true, lista.empty());
        assertEquals(0,lista.size());
        lista.add(a);
        assertEquals(1, lista.size());      
    }

    /**
     * Test of add method, of class LinkedList.
     */
    @Test
    public void testAdd() {
        lista.add(a);
        lista.add(b);
        lista.add(c);
        
        assertEquals(3, lista.size());
        assertTrue(lista.contains(b));
    }

    /**
     * Test of search method, of class LinkedList.
     */
    @Test
    public void testSearch() {
        lista.add(b);
        assertNull(lista.search(a));
        Node haku = (Node)lista.search(b).getOlio();
        assertEquals(b, haku);
    }
    
    /**
     * Test of searchWithString method, of class LinkedList.
     */
    @Test
    public void testSearchWithString() {
        lista.add(a);
        assertNotNull(lista.searchWithString("aa"));
        Node aa = (Node)lista.searchWithString("aa").getOlio();
        assertTrue(aa.equals(a));
    }

    /**
     * Test of size method, of class LinkedList.
     */
    @Test
    public void testSize() {
        assertFalse(lista.size() == 2);
        lista.add(a);
        assertTrue(lista.size() == 1);
    }

    /**
     * Test of get method, of class LinkedList.
     */
    @Test
    public void testGet() {
        lista.add(b);
        Object h = lista.get(0);
        Node d = (Node)h;
        assertEquals(b, d);
    }

    /**
     * Test of contains method, of class LinkedList.
     */
    @Test
    public void testContains() {
        lista.add(a);
        assertTrue(lista.contains(a));
        assertFalse(lista.empty());
    }
    
    /**
     * Test of containsString method, of class LinkedList.
     */
    @Test
    public void testContainsString() {
        lista.add(c);
        assertTrue(lista.containsString("cc"));
    }

    /**
     * Test of iterator method, of class LinkedList.
     */
    @Test
    public void testIterator() {
        int i = 0;
        lista.add(a);
        lista.add(b);
        lista.add(c);
        
        for (Node help : this.lista) {
            i++;
        }
        assertEquals(3, i);
    }    
}