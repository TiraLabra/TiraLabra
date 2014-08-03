/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package smartyahtzee;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author essalmen
 */
public class DieTest {
    
    public DieTest() {
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
     * Test of lock method, of class Die.
     */
    @Test
    public void testLock() {
        System.out.println("lock");
        Die instance = new Die();
        boolean expResult = true;
        boolean result = instance.lock();
        assertEquals(expResult, result);
    }

    /**
     * Test of isLocked method, of class Die.
     */
    @Test
    public void testIsLocked() {
        System.out.println("isLocked");
        Die instance = new Die();
        boolean expResult = false;
        boolean result = instance.isLocked();
        assertEquals(expResult, result);
    }

    /**
     * Test of getNumber method, of class Die.
     */
    @Test
    public void testGetNumber() {
        System.out.println("getNumber");
        Die instance = new Die();
        instance.setNumber(4);
        int expResult = 4;
        int result = instance.getNumber();
        assertEquals(expResult, result);
    }

    /**
     * Test of unlock method, of class Die.
     */
    @Test
    public void testUnlock() {
        System.out.println("unlock");
        Die instance = new Die();
        instance.unlock();
        assertFalse(instance.isLocked());
    }

    /**
     * Test of setNumber method, of class Die.
     */
    @Test
    public void testSetNumber() {
        System.out.println("setNumber");
        int number = 1;
        Die instance = new Die();
        instance.setNumber(number);
        assertEquals(1, instance.getNumber());
        
    }
    
}
