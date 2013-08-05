
package com.mycompany.tiralabra_maven.tietorakenteet;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class JonoTest {
    
    private Jono    jono;
    private Object  testiObjekti;
    
    public JonoTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        jono        = new Jono<Object>();
        testiObjekti  = new Object();
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testAdd() {
        assertTrue(jono.add(testiObjekti));
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testOffer() {
        jono.offer(testiObjekti);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testRemove() {
        jono.remove();
    }

    @Test
    public void testPoll() {
        jono.add(testiObjekti);
        Object paluuarvo = jono.poll();
        assertEquals(testiObjekti, paluuarvo);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testElement() {
        jono.element();
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testPeek() {
        jono.peek();
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testSize() {
        jono.size();
    }

    @Test
    public void testIsEmpty() {
        jono.add(testiObjekti);
        assertFalse(jono.isEmpty());
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testContains() {
        jono.contains(testiObjekti);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testIterator() {
        jono.iterator();
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testToArray_0args() {
        jono.toArray();
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testToArray_GenericType() {
        jono.toArray(new Object[1]);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testRemove_Object() {
        jono.remove(testiObjekti);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testContainsAll() {
        jono.containsAll(new Jono());
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testAddAll() {
        jono.addAll(new Jono());
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testRemoveAll() {
        jono.removeAll(new Jono());
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testRetainAll() {
        jono.retainAll(new Jono());
    }

    @Test
    public void testClear() {
        jono.add(testiObjekti);
        jono.clear();
        assertTrue(jono.isEmpty());
    }
}
