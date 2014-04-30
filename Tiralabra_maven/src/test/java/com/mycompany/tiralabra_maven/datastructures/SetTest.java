package com.mycompany.tiralabra_maven.datastructures;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author yessergire
 */
public class SetTest {

    Set<Integer> set;

    @Before
    public void setUp() {
        set = new Set<Integer>();
    }

    /**
     * Test of add method, of class Set.
     */
    @Test
    public void testAddAndRemove() {
        set.add(1);
        set.add(2);
        assertTrue(set.contains(1));
        assertTrue(set.contains(2));

        set.add(1);
        assertEquals(2, set.size());

        set.remove(1);
        assertFalse(set.contains(1));
        assertTrue(set.contains(2));
        assertEquals(1, set.size());
    }

    /**
     * Test of isEmpty method, of class Set.
     */
    @Test
    public void testIsEmpty() {
        assertTrue(set.isEmpty());

        set.add(1);
        assertFalse(set.isEmpty());

        set.remove(1);
        assertTrue(set.isEmpty());
    }

    /**
     * Test of iterator method, of class Set.
     */
    @Test
    public void testIterator() {
        assertNotNull(set.iterator());
        assertFalse(set.iterator().hasNext());
        
        set.add(1);
        assertTrue(set.iterator().hasNext());
        
        set.remove(1);
        assertFalse(set.iterator().hasNext());
    }

}
