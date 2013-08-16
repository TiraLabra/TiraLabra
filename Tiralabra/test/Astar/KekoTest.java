/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Astar;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Ile
 */
public class KekoTest {
    
    public KekoTest() {
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
     * Test of lisaaAlkio method, of class Keko.
     */
    @Test
    public void testLisaaAlkio() {
        System.out.println("lisaaAlkio");
        Ruutu lisattava = null;
        Keko instance = null;
        boolean expResult = false;
        boolean result = instance.lisaaAlkio(lisattava);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of poistaPienin method, of class Keko.
     */
    @Test
    public void testPoistaPienin() {
        System.out.println("poistaPienin");
        Keko instance = null;
        Ruutu expResult = null;
        Ruutu result = instance.poistaPienin();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of onTyhja method, of class Keko.
     */
    @Test
    public void testOnTyhja() {
        System.out.println("onTyhja");
        Keko instance = null;
        boolean expResult = false;
        boolean result = instance.onTyhja();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of main method, of class Keko.
     */
    @Test
    public void testMain() {
        System.out.println("main");
        String[] args = null;
        Keko.main(args);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}
