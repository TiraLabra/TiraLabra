/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Structures.Hashtable;

import Structures.LinkedList.LinkedList;
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
public class HashtableTest {
    private Hashtable<String,Integer> ht;
    public HashtableTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        this.ht=new Hashtable<String,Integer>();
    }
    
    @After
    public void tearDown() {
    }
    @Test
    public void standartTest() {
        this.ht.put("Kalle",11);
        assertEquals(11, (int)this.ht.get("Kalle"));
        this.ht.put("Pekka",51);
        assertEquals(51, (int)this.ht.get("Pekka"));
        this.ht.put("Simo",19);
        assertEquals(19, (int)this.ht.get("Simo"));
        this.ht.put("Salla",61);
        assertEquals(61, (int)this.ht.get("Salla"));
    }
    @Test
    public void keyCollisionTest() {
        this.ht.put("Kalle",71);
        assertEquals(71, (int)this.ht.get("Kalle"));
        this.ht.put("Pekka",19);
        assertEquals(19, (int)this.ht.get("Pekka"));
    }
    @Test
    public void removeAndContainsKeyTest(){
        this.ht.put("Kalle",11);
        this.ht.put("Pekka",51);
        this.ht.put("Salla",61);
        this.ht.remove("Kalle");
        assertEquals(false, this.ht.containsKey("Kalle"));
        assertEquals(true, this.ht.containsKey("Pekka"));
        this.ht.remove("Salla");
        assertEquals(false, this.ht.containsKey("Salla"));
    }
}