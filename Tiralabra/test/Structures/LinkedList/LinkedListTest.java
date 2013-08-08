/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Structures.LinkedList;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Kalle
 */
public class LinkedListTest {
    private LinkedList<String> ll;
    public LinkedListTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        this.ll=new LinkedList<String>();
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of add method, of class LinkedList.
     */
    @Test
    public void standartTest() {
        this.ll.add("Kalle");
        this.ll.add("Pekka");
        this.ll.add("Pirkko");
        assertEquals("Pirkko",this.ll.removeTail());
        assertEquals(false, this.ll.isEmpty());
        assertEquals("Pekka",this.ll.removeTail());
        assertEquals("Kall",this.ll.removeTail());
        assertEquals(true, this.ll.isEmpty());
    }
}