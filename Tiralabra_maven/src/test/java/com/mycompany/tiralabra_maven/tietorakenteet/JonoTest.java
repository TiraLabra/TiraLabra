/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.tiralabra_maven.tietorakenteet;

import java.util.Collection;
import java.util.Iterator;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;

/**
 *
 * @author johnny
 */
@Ignore // Kekseneräinen testiluokka.
public class JonoTest {
    
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
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of add method, of class Jono.
     */
    @org.junit.Test
    public void testAdd() {
        System.out.println("add");
        Object e = null;
        Jono instance = new Jono();
        boolean expResult = false;
        boolean result = instance.add(e);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of offer method, of class Jono.
     */
    @org.junit.Test
    public void testOffer() {
        System.out.println("offer");
        Object e = null;
        Jono instance = new Jono();
        boolean expResult = false;
        boolean result = instance.offer(e);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of remove method, of class Jono.
     */
    @org.junit.Test
    public void testRemove_0args() {
        System.out.println("remove");
        Jono instance = new Jono();
        Object expResult = null;
        Object result = instance.remove();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of poll method, of class Jono.
     */
    @org.junit.Test
    public void testPoll() {
        System.out.println("poll");
        Jono instance = new Jono();
        Object expResult = null;
        Object result = instance.poll();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of element method, of class Jono.
     */
    @org.junit.Test
    public void testElement() {
        System.out.println("element");
        Jono instance = new Jono();
        Object expResult = null;
        Object result = instance.element();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of peek method, of class Jono.
     */
    @org.junit.Test
    public void testPeek() {
        System.out.println("peek");
        Jono instance = new Jono();
        Object expResult = null;
        Object result = instance.peek();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of size method, of class Jono.
     */
    @org.junit.Test
    public void testSize() {
        System.out.println("size");
        Jono instance = new Jono();
        int expResult = 0;
        int result = instance.size();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isEmpty method, of class Jono.
     */
    @org.junit.Test
    public void testIsEmpty() {
        System.out.println("isEmpty");
        Jono instance = new Jono();
        boolean expResult = false;
        boolean result = instance.isEmpty();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of contains method, of class Jono.
     */
    @org.junit.Test
    public void testContains() {
        System.out.println("contains");
        Object o = null;
        Jono instance = new Jono();
        boolean expResult = false;
        boolean result = instance.contains(o);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of iterator method, of class Jono.
     */
    @org.junit.Test
    public void testIterator() {
        System.out.println("iterator");
        Jono instance = new Jono();
        Iterator expResult = null;
        Iterator result = instance.iterator();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of toArray method, of class Jono.
     */
    @org.junit.Test
    public void testToArray_0args() {
        System.out.println("toArray");
        Jono instance = new Jono();
        Object[] expResult = null;
        Object[] result = instance.toArray();
        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

//    /**
//     * Test of toArray method, of class Jono.
//     */
//    @org.junit.Test
//    public void testToArray_GenericType() {
//        System.out.println("toArray");
//        T[] a = null;
//        Jono instance = new Jono();
//        Object[] expResult = null;
//        Object[] result = instance.toArray(a);
//        assertArrayEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }

    /**
     * Test of remove method, of class Jono.
     */
    @org.junit.Test
    public void testRemove_Object() {
        System.out.println("remove");
        Object o = null;
        Jono instance = new Jono();
        boolean expResult = false;
        boolean result = instance.remove(o);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of containsAll method, of class Jono.
     */
    @org.junit.Test
    public void testContainsAll() {
        System.out.println("containsAll");
        Collection<?> c = null;
        Jono instance = new Jono();
        boolean expResult = false;
        boolean result = instance.containsAll(c);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addAll method, of class Jono.
     */
    @org.junit.Test
    public void testAddAll() {
        System.out.println("addAll");
        Collection<? extends Object> c = null;
        Jono instance = new Jono();
        boolean expResult = false;
        boolean result = instance.addAll(c);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of removeAll method, of class Jono.
     */
    @org.junit.Test
    public void testRemoveAll() {
        System.out.println("removeAll");
        Collection<?> c = null;
        Jono instance = new Jono();
        boolean expResult = false;
        boolean result = instance.removeAll(c);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of retainAll method, of class Jono.
     */
    @org.junit.Test
    public void testRetainAll() {
        System.out.println("retainAll");
        Collection<?> c = null;
        Jono instance = new Jono();
        boolean expResult = false;
        boolean result = instance.retainAll(c);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of clear method, of class Jono.
     */
    @org.junit.Test
    public void testClear() {
        System.out.println("clear");
        Jono instance = new Jono();
        instance.clear();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}
