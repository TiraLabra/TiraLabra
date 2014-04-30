package com.mycompany.tiralabra_maven.datastructures;

import java.util.Iterator;
import static org.junit.Assert.*;
import org.junit.Test;

public class ListIteratorTest {
    Iterator iter;

    /**
     * Test of hasNext method, of class ListIterator.
     */
    @Test
    public void testHasNext() {
        List<Integer> list = new List<Integer>();
        assertFalse(list.iterator().hasNext());
        list.insertLast(1);
        assertTrue(list.iterator().hasNext());
    }

    /**
     * Test of next method, of class ListIterator.
     */
    @Test
    public void testNext() {
        List<Integer> list = new List<Integer>();
        list.insertLast(3);
        list.insertLast(2);
        list.insertLast(1);
        iter = list.iterator();
        assertTrue(iter.hasNext());
        assertEquals(1, iter.next());
        assertEquals(2, iter.next());
        assertEquals(3, iter.next());
        assertFalse(iter.hasNext());
    }

    /**
     * Test of remove method, of class ListIterator.
     */
    @Test
    public void testRemove() {
        List<Integer> list = new List<Integer>();
        list.insertLast(3);
        list.insertLast(2);
        list.insertLast(1);
        iter = list.iterator();
        assertTrue(iter.hasNext());
        assertEquals(1, iter.next());
        iter.remove();
        assertFalse(list.contains(1));
        assertEquals(2, list.size());
    }
    
}
