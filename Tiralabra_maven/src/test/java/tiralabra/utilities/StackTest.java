/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tiralabra.utilities;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author atte
 */
public class StackTest {
    
    private Stack<Integer> stack;
    
    @Before
    public void setUp() {
        stack = new Stack();
    }
    
    @Test
    public void pushingPoppingBothWork() {
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        assertEquals(4, (long) stack.pop());
        assertEquals(3, (long) stack.pop());
        assertEquals(2, (long) stack.pop());
        assertEquals(1, (long) stack.pop());
    }
    
    @Test
    public void peekingWorks() {
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        assertEquals(4, (long) stack.peek());
        stack.pop();
        assertEquals(3, (long) stack.peek());
        stack.pop();
        assertEquals(2, (long) stack.peek());
        stack.pop();
        assertEquals(1, (long) stack.peek());
    }
    
    @Test
    public void stackGrowsCapacity() {
        stack = new Stack(2);
        stack.push(1);
        stack.push(2);
        stack.push(3);
        assertEquals(3, stack.size());
    }
}
