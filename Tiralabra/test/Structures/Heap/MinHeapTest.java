/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Structures.Heap;

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
public class MinHeapTest {
    private MinHeap<String> mh;
    public MinHeapTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        this.mh=new MinHeap<String>(10);
    }
    
    @After
    public void tearDown() {
    }
    @Test
    public void standartTest() {
        this.mh.insert(15, "Kalle");
        this.mh.insert(5, "Pekka");
        this.mh.insert(14, "Simo");
        this.mh.insert(3, "Marja");
        assertEquals("Marja",this.mh.pop());
        this.mh.decrease("Kalle", 1);
        assertEquals("Kalle",this.mh.pop());
        this.mh.decrease("Pekka", 1);
        this.mh.insert(9, "Jutta");
        assertEquals("Pekka",this.mh.pop());
        assertEquals("Jutta",this.mh.pop());
        assertEquals(false,this.mh.isEmpty());
        assertEquals("Simo",this.mh.pop());
        assertEquals(true,this.mh.isEmpty());
    }
}