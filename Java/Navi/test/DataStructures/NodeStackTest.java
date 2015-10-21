package DataStructures;

import Terrain.CartesianTile;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

//@author Leevi
public class NodeStackTest {
    
    NodeStack stack;

    public NodeStackTest() {
        
        stack = new NodeStack(1);
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
     * Test of push method, of class NodeStack.
     */
    @Test
    public void testPush() {
        
        Node node = new Node(0, 0, CartesianTile.ROAD);
        stack.push(node);
        
        assertEquals(false, stack.isEmpty());
        
    }

    /**
     * Test of pop method, of class NodeStack.
     */
    @Test
    public void testPop() {
        
        Node node = new Node(0, 0, CartesianTile.ROAD);
        stack.push(node);
        Node testNode = stack.pop();
        
        assertEquals(true, stack.isEmpty());
        
    }

    /**
     * Test of peek method, of class NodeStack.
     */
    @Test
    public void testPeek() {
        
        Node node = new Node(0, 0, CartesianTile.ROAD);
        stack.push(node);
        Node testNode = stack.peek();
        
        assertEquals(false, stack.isEmpty());
        
    }

    /**
     * Test of isEmpty method, of class NodeStack.
     */
    @Test
    public void testIsEmpty() {
                
        assertEquals(true, stack.isEmpty());
        
    }

    /**
     * Test of isFull method, of class NodeStack.
     */
    @Test
    public void testIsFull() {
        
        for (int i = 0; i < 1; i++) {
            stack.push(new Node(0, 0, CartesianTile.ROAD));
        }
        
        assertEquals(true, stack.isFull());
        
    }

}