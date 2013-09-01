/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.tiralabra_maven;

import com.mycompany.tiralabra_maven.data_structures.Node;
import com.mycompany.tiralabra_maven.data_structures.Stack;
import junit.framework.TestCase;

/**
 *
 * @author Joel Nummelin
 */
public class StackTest extends TestCase{
    private Stack stack;
    
    
    
    
    @Override
    public void setUp(){
        stack = new Stack();
    }
    
    
    public void testPuttingStuffOnStack(){
        assertEquals(stack.peek(), null);
        stack.put(new Node(0 , 0));
        assertEquals(stack.peek().getMove(), 0);
        assertEquals(stack.peek().getResult(), 0);
        stack.pop();
        assertEquals(stack.peek(), null);
    }
    
    public  void testStackSize(){
        assertEquals(stack.size(), 0);
        stack.put(new Node(1, 2));
        stack.put(new Node(1, 1));
        assertEquals(stack.size(), 2);
        stack.pop();
        assertEquals(stack.size(), 1);
    }
    
    public void testStackBehaviour(){
        stack.put(new Node(0, 0));
        stack.put(new Node(0, 1));
        stack.put(new Node(1, 0));
        stack.put(new Node(1, 1));
        assertEquals(stack.pop(), new Node(1, 1));
        assertEquals(stack.pop(), new Node(1, 0));
        assertEquals(stack.pop(), new Node(0, 1));
        assertEquals(stack.pop(), new Node(0, 0));
    }
    
}
