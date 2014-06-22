/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package lib.datastructures;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;


public class LinkedQueueTest {
    LinkedQueue<Integer> q;
    
    public LinkedQueueTest() {
    }
    
    @Before
    public void setUp() {
        q = new LinkedQueue<Integer>();
    }
    
    @Test
    public void testEnqueueAndDequeue() {
        q.enqueue(1);
        assertTrue(q.dequeue() == 1);
        q.enqueue(2);
        q.enqueue(3);
        assertTrue(q.dequeue() == 2);
        assertTrue(q.dequeue() == 3);
        assertTrue(q.dequeue() == null);
    }
    
    @Test
    public void testSize(){
        assertTrue(q.size() == 0);
        q.enqueue(1);
        assertTrue(q.size() == 1);
        q.enqueue(2);
        q.enqueue(3);
        q.dequeue();
        assertTrue(q.size() == 2);
        q.dequeue();
        q.dequeue();
        assertTrue(q.size() == 0);
    }
    
    @Test
    public void testForLoop(){
        q.enqueue(1);
        q.enqueue(2);
        q.enqueue(3);
        q.enqueue(4);
        q.enqueue(5);
        int i = 1;
        for(int k: q){
            assertTrue(k == i);
            i++;
        }
    }

    
    
    
}
