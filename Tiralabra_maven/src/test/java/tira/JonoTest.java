/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tira;

import java.util.ArrayDeque;
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
public class JonoTest {

    private static ArrayDeque<String> arrayDeque;
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
     * Test of enqueue method, of class Jono.
     */
    @Test
    public void testEnqueue() {
        System.out.println("enqueue");
        Object e = null;
        Jono instance = new Jono();
        arrayDeque = new ArrayDeque();
        for (String s : strings) {
            instance.enqueue(s);
            arrayDeque.addLast(s);
        }

        assertTrue(instance.size() == arrayDeque.size());
    }

    /**
     * Test of add method, of class Jono.
     */
    @Test
    public void testAdd() {
        testEnqueue();
    }

    /**
     * Test of dequeue method, of class Jono.
     */
    @Test
    public void testDequeue() {
        System.out.println("dequeue");
        Jono<String> instance = new Jono<String>();
        Object expResult = null;
        Object result = instance.dequeue();
        assertEquals(expResult, result);

        instance.enqueue(strings[0]);
        result = instance.dequeue();
        assertEquals(strings[0], result);

    }

    /**
     * Test of poll method, of class Jono.
     */
    @Test
    public void testPoll() {
        testDequeue();
    }

    /**
     * Test of isEmpty method, of class Jono.
     */
    @Test
    public void testIsEmpty() {
        System.out.println("isEmpty");
        Jono instance = new Jono();
        boolean expResult = true;
        boolean result = instance.isEmpty();
        assertEquals(expResult, result);

        instance.enqueue(strings[0]);
        result = instance.isEmpty();
        assertEquals(false, result);
        
        instance.poll();
        result = instance.isEmpty();
        assertEquals(true, result);        

    }

    /**
     * Test of size method, of class Jono.
     */
    @Test
    public void testSize() {
        System.out.println("size");
        Jono instance = new Jono();
        int expResult = 0;
        int result = instance.size();
        assertEquals(expResult, result);
        
        for (String s : strings) {
            instance.enqueue(s);   
        }
        
        expResult = n;
        result = instance.size();
        assertEquals(expResult, result);
    }


}
