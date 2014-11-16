/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tira;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.UUID;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author E
 */
public class LinkitettyListaTest {

    private LinkedList<String> lista;

    private static String[] strings;
    private static int n;

    @BeforeClass
    public static void setUpClass() {
        n = 100;
        strings = new String[n];
        for (int i = 0; i < n; i++) {
            strings[i] = UUID.randomUUID().toString().substring(0, 6);
        }
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
     * Test of add method, of class LinkitettyLista.
     */
    @Test
    public void testAdd() {
        System.out.println("add");
        String e = strings[0];
        LinkitettyLista<String> instance = new LinkitettyLista();

        assertTrue(!instance.contains(e) && instance.size() == 0 && instance.isEmpty());

        instance.add(e);

        assertTrue(instance.contains(e) && instance.size() == 1 && !instance.isEmpty());
    }

    /**
     * Test of get method, of class LinkitettyLista.
     */
    @Test
    public void testGet() {
        System.out.println("get");

        LinkitettyLista<String> instance = new LinkitettyLista();
        for (String s : strings) {
            instance.add(s);
        }
        int i = 15;
        String expResult = strings[i];
        String result = instance.get(i);
        assertEquals(expResult, result);

    }

    /**
     * Test of remove method, of class LinkitettyLista.
     */
    @Test
    public void testRemove() {
        System.out.println("remove");
        LinkitettyLista<String> instance = new LinkitettyLista();
        for (String s : strings) {
            instance.add(s);
        }
        int i = 15;
        String expResult = strings[i];
        String result = instance.remove(i);
        assertEquals(expResult, result);

        assertTrue(!instance.contains(expResult)); // ei enää listassa

    }

    /**
     * Test of contains method, of class LinkitettyLista.
     */
    @Test
    public void testContains() {
        System.out.println("contains");
        LinkitettyLista<String> instance = new LinkitettyLista();
        for (String s : strings) {
            instance.add(s);
        }
        int i = 15;
        String expResult = strings[i];

        assertTrue(instance.contains(expResult));
        instance.remove(i);
        assertTrue(!instance.contains(expResult));
    }

    /**
     * Test of indexOf method, of class LinkitettyLista.
     */
    @Test
    public void testIndexOf() {

        System.out.println("indexOf");
        int i = 15;
        String expResult = strings[i];
        LinkitettyLista<String> instance = new LinkitettyLista();

        int result = instance.indexOf(expResult);
        assertEquals(-1, result);

        for (String s : strings) {
            instance.add(s);
        }

        result = instance.indexOf(expResult);
        assertEquals(i, result);
    }

    /**
     * Test of size method, of class LinkitettyLista.
     */
    @Test
    public void testSize() {
        System.out.println("size");
        LinkitettyLista<String> instance = new LinkitettyLista();
        int expResult = 0;
        int result = instance.size();
        assertEquals(expResult, result);

        expResult = n;
        for (String s : strings) {
            instance.add(s);
        }
        result = instance.size();
        assertEquals(expResult, result);

    }

    /**
     * Test of isEmpty method, of class LinkitettyLista.
     */
    @Test
    public void testIsEmpty() {
        System.out.println("isEmpty");
        LinkitettyLista instance = new LinkitettyLista();
        boolean expResult = true;
        boolean result = instance.isEmpty();
        assertEquals(expResult, result);

        instance.add("HUUHUU");
        expResult = false;
        result = instance.isEmpty();
        assertEquals(expResult, result);

        instance.remove(0);
        expResult = true;
        result = instance.isEmpty();
        assertEquals(expResult, result);
    }

    /**
     * Test of iterator method, of class LinkitettyLista.
     */
    @Test
    public void testIterator() {
        System.out.println("iterator");
        LinkitettyLista<String> instance = new LinkitettyLista();
        for (String s : strings) {
            instance.add(s);
        }
        int index = 0;
        for (String s : instance) {
            String result = s;
            String expResult = strings[index];
            assertEquals(expResult, result);
            index++;
        }
    }

    @Test
    public void testPeek() {
        System.out.println("peek");
        LinkitettyLista<String> instance = new LinkitettyLista();
        for (String s : strings) {
            instance.add(s);
        }
        String expResult = strings[0];
        String result = instance.peek();
        assertEquals(expResult, result);

        instance.poll();
        instance.poll();
        expResult = strings[2];
        result = instance.peek();
        assertEquals(expResult, result);
    }

    /**
     * Test of peekLast method, of class LinkitettyLista.
     */
    @Test
    public void testPeekLast() {
        System.out.println("peekLast");
        LinkitettyLista<String> instance = new LinkitettyLista();
        for (String s : strings) {
            instance.add(s);
        }
        String expResult = strings[n - 1];
        String result = instance.peekLast();
        assertEquals(expResult, result);

        instance.pollLast();
        instance.pollLast();
        expResult = strings[n - 3];
        result = instance.peekLast();
        assertEquals(expResult, result);
    }

    /**
     * Test of poll method, of class LinkitettyLista.
     */
    @Test
    public void testPoll() {
        System.out.println("poll");
        LinkitettyLista<String> instance = new LinkitettyLista();
        for (String s : strings) {
            instance.add(s);
        }
        String expResult = strings[0];
        String result = instance.poll();
        assertEquals(expResult, result);

        assertTrue(!instance.contains(result)); // poistettiin oikeasti

        instance.pollLast(); // ei pitäisi vaikuttaa

        instance.poll();
        expResult = strings[2];
        result = instance.poll();
        assertEquals(expResult, result);

        assertTrue(!instance.contains(result)); // poistettiin oikeasti
    }

    /**
     * Test of pollLast method, of class LinkitettyLista.
     */
    @Test
    public void testPollLast() {
        System.out.println("pollLast");
        LinkitettyLista<String> instance = new LinkitettyLista();
        for (String s : strings) {
            instance.add(s);
        }
        String expResult = strings[n - 1];
        String result = instance.pollLast();
        assertEquals(expResult, result);

        assertTrue(!instance.contains(result)); // poistettiin oikeasti

        instance.poll(); // ei pitäisi vaikuttaa

        instance.pollLast();
        expResult = strings[n - 3];
        result = instance.pollLast();
        assertEquals(expResult, result);

        assertTrue(!instance.contains(result)); // poistettiin oikeasti
    }

    /**
     * Test of remove method, of class LinkitettyLista.
     */
    @Test
    public void testRemove_GenericType() {
        System.out.println("remove");
        LinkitettyLista<String> instance = new LinkitettyLista();
        for (String s : strings) {
            instance.add(s);
        }
        int i=15;
        String expResult = strings[i];
        String result    = instance.remove(expResult);
        
        assertEquals(expResult, result);
        assertTrue(!instance.contains(result));
    }

}
