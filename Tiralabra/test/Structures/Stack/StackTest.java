/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Structures.Stack;

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
public class StackTest {
    private Stack<String> t;
    public StackTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        this.t=new Stack<String>();
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of push method, of class Stack.
     */
    @Test
    public void standartTest() {
        this.t.push("Kalle");
        assertEquals(false,this.t.isEmpty());
        assertEquals("Kalle",this.t.pop());
        assertEquals(true,this.t.isEmpty());
        this.t.push("Helena");
        this.t.push("Tero");
        assertEquals("Tero",this.t.pop());
        assertEquals("Helena",this.t.pop());
        assertEquals(true,this.t.isEmpty());
    }
}