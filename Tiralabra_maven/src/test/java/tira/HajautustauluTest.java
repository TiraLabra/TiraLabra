/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tira;

import java.util.HashMap;
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
public class HajautustauluTest {

    private static String[] stringsK;
    private static String[] stringsV;
    private static int n;

    private HashMap<String, String> hashMap = new HashMap();

    @BeforeClass
    public static void setUpClass() {
        n = 100;
        stringsK = new String[n];
        for (int i = 0; i < n; i++) {
            stringsK[i] = UUID.randomUUID().toString().substring(0, 6);
        }
        stringsV = new String[n];
        for (int i = 0; i < n; i++) {
            stringsV[i] = UUID.randomUUID().toString().substring(0, 6);
        }
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        for (int i = 0; i < n; i++) {
            hashMap.put(stringsK[i], stringsV[i]);
        }
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of put method, of class Hajautustaulu.
     */
    @Test
    public void testPut() {
        System.out.println("put");
        Hajautustaulu<String, String> instance = new Hajautustaulu();
        Object expResult = null;
        for (int i = 0; i < n; i++) {
            Object result = instance.put(stringsK[i], stringsV[i]);
            assertEquals(expResult, result);
        }
        int i = 15;
        expResult = stringsV[i];
        Object result = instance.put(stringsK[i], "huuhuuu");
        assertEquals(expResult, result);
    }

    /**
     * Test of contains method, of class Hajautustaulu.
     */
    @Test
    public void testContains() {
        System.out.println("contains");

        Hajautustaulu<String, String> instance = new Hajautustaulu();

        for (int i = 0; i < n; i++) {
            instance.put(stringsK[i], stringsV[i]);

        }
        int i = 15;
        String k = stringsK[i];
        boolean expResult = true;
        boolean result = instance.contains(k);
        assertEquals(expResult, result);

        assertEquals(instance.contains("HuuuHuuuuuh!"), false);
    }

    /**
     * Test of get method, of class Hajautustaulu.
     */
    @Test
    public void testGet() {
        System.out.println("get");
        
        Hajautustaulu<String, String> instance = new Hajautustaulu();

        for (int i = 0; i < n; i++) {
            instance.put(stringsK[i], stringsV[i]);

        }
        int i = 15;
        String k = stringsK[i];      
        String expResult = stringsV[i];  
        String result = instance.get(k);
        assertEquals(expResult, result);

    }

    /**
     * Test of remove method, of class Hajautustaulu.
     */
    @Test
    public void testRemove() {
        System.out.println("remove");
        Hajautustaulu<String, String> instance = new Hajautustaulu();

        for (int i = 0; i < n; i++) {
            instance.put(stringsK[i], stringsV[i]);
        }
        int i = 15;
        String k = stringsK[i];      
        String expResult = stringsV[i];  
        String result = instance.remove(k);
        assertEquals(expResult, result);
        assertEquals(false,instance.contains(k));// poistettu oikeasti?
    }

    /**
     * Test of keySet method, of class Hajautustaulu.
     */
    @Test
    public void testKeySet() {
        System.out.println("keySet");
        Hajautustaulu instance = new Hajautustaulu();
        Lista expResult = null;
        Lista result = instance.keySet();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isEmpty method, of class Hajautustaulu.
     */
    @Test
    public void testIsEmpty() {
        System.out.println("isEmpty");
        Hajautustaulu instance = new Hajautustaulu();
        boolean expResult = false;
        boolean result = instance.isEmpty();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of size method, of class Hajautustaulu.
     */
    @Test
    public void testSize() {
        System.out.println("size");
        Hajautustaulu instance = new Hajautustaulu();
        int expResult = 0;
        int result = instance.size();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
