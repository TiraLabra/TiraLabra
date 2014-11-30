/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tira;

import java.util.HashMap;
import java.util.Iterator;
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
public class HajautuslistaTest {

    private static String[] stringsK;
    // private static String[] stringsV;
    private static int n;

    // private HashMap<String, String> hashMap = new HashMap();
    @BeforeClass
    public static void setUpClass() {
        n = 100;
        stringsK = new String[n];
        for (int i = 0; i < n; i++) {
            stringsK[i] = UUID.randomUUID().toString().substring(0, 6);
        }
        /*
         stringsV = new String[n];
         for (int i = 0; i < n; i++) {
         stringsV[i] = UUID.randomUUID().toString().substring(0, 6);
         }
         */
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
     * Test of add method, of class Hajautuslista.
     */
    @Test
    public void testAdd() {
        System.out.println("add");
        int i = 15;
        String k = stringsK[i];
        Hajautuslista<String> instance = new Hajautuslista();
        String expResult = null;
        String result = instance.add(k);
        // paluuarvo null jos lista ei sisällä avainta
        assertEquals(expResult, result);

        expResult = k;
        result = instance.add(k);
        // paluuarvo on edellinen avain, jos se oli jo listassa
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        // fail("The test case is a prototype.");
    }

    /**
     * Test of get method, of class Hajautuslista.
     */
    @Test
    public void testGet() {
        System.out.println("get");
        int i = 15;
        String k = stringsK[i];
        Hajautuslista<String> instance = new Hajautuslista();
        String expResult = null;
        String result = instance.get(k);
        assertEquals(expResult, result);

        instance.add(k);
        
        expResult = k;
        result = instance.get(k);
        // String result = instance.add(k);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        // fail("The test case is a prototype.");
    }

    /**
     * Test of contains method, of class Hajautuslista.
     */
    @Test
    public void testContains() {
        System.out.println("contains");
        int i = 15;
        String k = stringsK[i];
        Hajautuslista<String> instance = new Hajautuslista();
        boolean expResult = false;
        boolean result = instance.contains(k);
        assertEquals(expResult, result);
        
        instance.add(k);
        assertEquals(true, instance.contains(k) );

        instance.remove(k);
        assertEquals(false, instance.contains(k) );        
        // fail("The test case is a prototype.");
    }

    /**
     * Test of remove method, of class Hajautuslista.
     */
    @Test
    public void testRemove() {
        System.out.println("remove");
        int i = 15;
        String k = stringsK[i];
        Hajautuslista<String> instance = new Hajautuslista();
        instance.add(k);
        assertEquals(true, instance.contains(k) );

        instance.remove(k);
        assertEquals(false, instance.contains(k) );     
        // TODO review the generated test code and remove the default call to fail.
        // fail("The test case is a prototype.");
    }

    /**
     * Test of keySet method, of class Hajautuslista.
     */
    @Test
    public void testKeySet() {
        System.out.println("keySet");
        Hajautuslista<String> instance = new Hajautuslista();
        Lista<String> expResult  = new DynaaminenLista();
        for(String k : stringsK ) {
            instance.add(k);
            expResult.add(k);
        }
        Lista<String> result = instance.keySet();
        for(String k : stringsK ) {
            assertTrue(result.contains(k));
        }
        for (String k : result ) {
            assertTrue(expResult.contains(k) );
        }
        // assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        // fail("The test case is a prototype.");
    }

    /**
     * Test of debugPrint method, of class Hajautuslista.
     */
    @Test
    public void testDebugPrint() {
        System.out.println("debugPrint");
        Hajautuslista instance = new Hajautuslista();
        // String expResult = "";
        String result = instance.debugPrint();
        assertTrue(result!=null);
        // TODO review the generated test code and remove the default call to fail.
        // fail("The test case is a prototype.");
    }

    /**
     * Test of iterator method, of class Hajautuslista.
     */
    @Test
    public void testIterator() {
        System.out.println("iterator");
        Hajautuslista<String> instance = new Hajautuslista();
        Lista<String> expResult  = new DynaaminenLista();
        for(String k : stringsK ) {
            instance.add(k);
            expResult.add(k);
        }
        Iterator<String> iterator = instance.iterator();
        for ( Object s : instance ) {
            assertTrue( expResult.contains((String)s));
        } 
        // TODO review the generated test code and remove the default call to fail.
        // fail("The test case is a prototype.");
    }

}
