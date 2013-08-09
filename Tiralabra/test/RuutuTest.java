/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import Astar.Ruutu;
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
public class RuutuTest {
    
    public RuutuTest() {
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
     * Test of vertaileFarvoja method, of class Ruutu.
     */
    @Test
    public void testVertaileFarvoja() {
        System.out.println("vertaileFarvoja");
        Ruutu vertailtava = null;
        Ruutu instance = null;
        int expResult = 0;
        int result = instance.vertaileFarvoja(vertailtava);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setParent method, of class Ruutu.
     */
    @Test
    public void testSetParent() {
        System.out.println("setParent");
        Ruutu vanhempi = null;
        Ruutu instance = null;
        instance.setParent(vanhempi);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getParent method, of class Ruutu.
     */
    @Test
    public void testGetParent() {
        System.out.println("getParent");
        Ruutu instance = null;
        Ruutu expResult = null;
        Ruutu result = instance.getParent();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getX method, of class Ruutu.
     */
    @Test
    public void testGetX() {
        System.out.println("getX");
        Ruutu instance = null;
        int expResult = 0;
        int result = instance.getX();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getY method, of class Ruutu.
     */
    @Test
    public void testGetY() {
        System.out.println("getY");
        Ruutu instance = null;
        int expResult = 0;
        int result = instance.getY();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getH method, of class Ruutu.
     */
    @Test
    public void testGetH() {
        System.out.println("getH");
        Ruutu instance = null;
        int expResult = 0;
        int result = instance.getH();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setG method, of class Ruutu.
     */
    @Test
    public void testSetG() {
        System.out.println("setG");
        int g = 0;
        Ruutu instance = null;
        instance.setG(g);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getG method, of class Ruutu.
     */
    @Test
    public void testGetG() {
        System.out.println("getG");
        Ruutu instance = null;
        int expResult = 0;
        int result = instance.getG();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getF method, of class Ruutu.
     */
    @Test
    public void testGetF() {
        System.out.println("getF");
        Ruutu instance = null;
        int expResult = 0;
        int result = instance.getF();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setLoytyykoAvoimesta method, of class Ruutu.
     */
    @Test
    public void testSetLoytyykoAvoimesta() {
        System.out.println("setLoytyykoAvoimesta");
        boolean b = false;
        Ruutu instance = null;
        instance.setLoytyykoAvoimesta(b);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of onkoAvoimessa method, of class Ruutu.
     */
    @Test
    public void testOnkoAvoimessa() {
        System.out.println("onkoAvoimessa");
        Ruutu instance = null;
        boolean expResult = false;
        boolean result = instance.onkoAvoimessa();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setLoytyykoSuljetusta method, of class Ruutu.
     */
    @Test
    public void testSetLoytyykoSuljetusta() {
        System.out.println("setLoytyykoSuljetusta");
        boolean b = false;
        Ruutu instance = null;
        instance.setLoytyykoSuljetusta(b);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of onkoSuljetussa method, of class Ruutu.
     */
    @Test
    public void testOnkoSuljetussa() {
        System.out.println("onkoSuljetussa");
        Ruutu instance = null;
        boolean expResult = false;
        boolean result = instance.onkoSuljetussa();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}
