package datastructures;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import datastructures.Node;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author joonaskylliainen
 */
public class NodeTest {
    
    Node node;
    
    public NodeTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        node = new Node('a', 5);
    }
    
    @After
    public void tearDown() {
    }
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    
    /**
     *toimiiko increaseFrequencyByOne-metodi.
     */
    @Test
    public void doesIncreaseByOneWork() {
        node.increaseFrequencyByOne();
        
        assertEquals(6, node.getFrequency());
    }
    
    /**
     * Toimiiko equals-metodi kun pitäisi olla true
     */
    @Test
    public void isEqualsTrueWhenEqual() {
        Node node2 = new Node('a', 5);
        
        assertEquals(true, node.equals(node2));
    }
    
    /**
     * toimiiko equals metodi kun pitäisi olla false
     */
    @Test
    public void isEqualsFalseWhenEqual() {
        Node node2 = new Node('b', 5);
        
        assertEquals(false, node.equals(node2));
    }
    /**
     * Toimiiko compareTo kun verrattava on pienempi
     */
    @Test
    public void doesCompareToWorkWhenSmaller() {
        Node node2 = new Node('b', 4);
        
        assertEquals(1, node.compareTo(node2));
    }
    /**
     * Toimiiko compareTo kun verrattava isompi
     */
    @Test
    public void doesCompareToWorkWhenBigger() {
        Node node2 = new Node('b', 6);
        
        assertEquals(-1, node.compareTo(node2));
    }
    /**
     * toimiiko compareTo kun verrattavat ovat samoja
     */
    @Test
    public void doesCompareToWorkWhenSame() {
        Node node2 = new Node('b', 5);
        
        assertEquals(0, node.compareTo(node2));
    }
    
}
