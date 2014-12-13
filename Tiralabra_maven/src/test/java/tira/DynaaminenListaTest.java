/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tira;

import java.util.ArrayList;
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
public class DynaaminenListaTest {

    private ArrayList<String> lista;

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
     * Test of add method, of class DynaaminenLista.
     */
    @Test
    public void testAdd() {
        System.out.println("add");
        String e = strings[0];
        DynaaminenLista<String> instance = new DynaaminenLista();

        assertTrue(!instance.contains(e) && instance.size() == 0 && instance.isEmpty());

        instance.add(e);

        assertTrue(instance.contains(e) && instance.size() == 1 && !instance.isEmpty());
    }

    /**
     * Test of get method, of class DynaaminenLista.
     */
    @Test
    public void testGet() {
        System.out.println("get");

        DynaaminenLista<String> instance = new DynaaminenLista();
        for (String s : strings) {
            instance.add(s);
        }
        int i = 15;
        String expResult = strings[i];
        String result = instance.get(i);
        assertEquals(expResult, result);

    }

    /**
     * Test of remove method, of class DynaaminenLista.
     */
    @Test
    public void testRemove() {
        System.out.println("remove");
        DynaaminenLista<String> instance = new DynaaminenLista();
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
     * Test of contains method, of class DynaaminenLista.
     */
    @Test
    public void testContains() {
        System.out.println("contains");
        DynaaminenLista<String> instance = new DynaaminenLista();
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
     * Test of indexOf method, of class DynaaminenLista.
     */
    @Test
    public void testIndexOf() {

        System.out.println("indexOf");
        int i = 15;
        String expResult = strings[i];
        DynaaminenLista<String> instance = new DynaaminenLista();

        int result = instance.indexOf(expResult);
        assertEquals(-1, result);

        for (String s : strings) {
            instance.add(s);
        }

        result = instance.indexOf(expResult);
        assertEquals(i, result);
    }

    /**
     * Test of size method, of class DynaaminenLista.
     */
    @Test
    public void testSize() {
        System.out.println("size");
        DynaaminenLista<String> instance = new DynaaminenLista();
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
     * Test of isEmpty method, of class DynaaminenLista.
     */
    @Test
    public void testIsEmpty() {
        System.out.println("isEmpty");
        DynaaminenLista instance = new DynaaminenLista();
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
     * Test of iterator method, of class DynaaminenLista.
     */
    @Test
    public void testIterator() {
        System.out.println("iterator");
        DynaaminenLista<String> instance = new DynaaminenLista();
        for (String s : strings) {
            instance.add(s);
        }        
        int index = 0;
        for ( String s : instance ) {
            String result = s;
            String expResult = strings[index];
            assertEquals(expResult, result);
            index++;
        }   
    }

}
