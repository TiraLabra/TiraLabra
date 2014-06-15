/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Juri Kuronen
 */
public class MyQueueTest {

    MyQueue<Integer> testQueue;

    @Before
    public void setUp() {
        testQueue = new MyQueue<>();
    }

    @Test
    public void adding() {
        assertTrue(testQueue.empty());
        for (int i = 0; i < 10; i++) {
            testQueue.enqueue(i);
            assertTrue(!testQueue.empty());
            assertEquals(i + 1, testQueue.size());
        }
    }

    @Test
    public void addingPastQueueMaxSize() {
        assertTrue(testQueue.empty());
        for (int i = 0; i < 100; i++) {
            testQueue.enqueue(i);
            assertTrue(!testQueue.empty());
            assertEquals(i + 1, testQueue.size());
        }
    }

    @Test
    public void addingAndGetting() {
        adding();
        for (int i = 0; i < 10; i++) {
            assertEquals(10 - i, testQueue.size());
            assertEquals(i, (int) testQueue.dequeue());
        }
        assertTrue(testQueue.empty());
    }

    @Test
    public void addingAndGettingALot() {
        addingPastQueueMaxSize();
        for (int i = 0; i < 100; i++) {
            assertEquals(100 - i, testQueue.size());
            assertEquals(i, (int) testQueue.dequeue());
        }
        assertTrue(testQueue.empty());
    }

    @Test
    public void cantUpdateQueueSizeWithEmptyQueue() {
        int size = testQueue.size();
        testQueue.updateQueueSize();
        assertTrue(size == testQueue.size());
        testQueue.enqueue(5);
        testQueue.dequeue();
        size = testQueue.size();
        testQueue.updateQueueSize();
        assertTrue(size == testQueue.size());
    }

   @Test
    public void cantUpdateQueueSizeWithEmptyQueueAfterAdding() {
        testQueue.enqueue(5);
        testQueue.dequeue();
        int size = testQueue.size();
        testQueue.updateQueueSize();
        assertTrue(size == testQueue.size());
    }
    
    @Test
    public void queueCorrectlyEmptyAfterAdding() {
        testQueue.enqueue(5);
        testQueue.dequeue();
        assertTrue(testQueue.empty());
    }

    @Test
    public void headAndTailMoveCorrectlyWhenEnqueuing() {
        testQueue = new MyQueue<>(5);
        // [X X X X X]
        assertEquals(0, testQueue.getHead());
        assertEquals(0, testQueue.getTail());
        testQueue.enqueue(5);
        // [H T X X X]
        assertEquals(0, testQueue.getHead());
        assertEquals(1, testQueue.getTail());
        assertEquals(1, testQueue.size());
        assertEquals(5, (int) testQueue.get(0));
        assertEquals(null, testQueue.get(1));
        testQueue.enqueue(9);
        // [H 9 T X X]
        assertEquals(0, testQueue.getHead());
        assertEquals(2, testQueue.getTail());
        assertEquals(2, testQueue.size());
        assertEquals(5, (int) testQueue.get(0));
        assertEquals(9, (int) testQueue.get(1));
        assertEquals(null, testQueue.get(2));
        testQueue.enqueue(7);
        // [H 9 7 T X]
        assertEquals(0, testQueue.getHead());
        assertEquals(3, testQueue.getTail());
        assertEquals(3, testQueue.size());
        assertEquals(5, (int) testQueue.get(0));
        assertEquals(9, (int) testQueue.get(1));
        assertEquals(7, (int) testQueue.get(2));
        assertEquals(null, testQueue.get(3));
    }

    @Test
    public void headAndTailMoveCorrectlyWhenDequeing() {
        headAndTailMoveCorrectlyWhenEnqueuing();
        int dequeue = testQueue.dequeue();
        // [X H 7 T X]
        assertEquals(1, testQueue.getHead());
        assertEquals(3, testQueue.getTail());
        assertEquals(2, testQueue.size());
        assertEquals(5, dequeue);
        assertEquals(9, (int) testQueue.get(1));
        assertEquals(7, (int) testQueue.get(2));
        assertEquals(null, testQueue.get(3));
        testQueue.dequeue();
        testQueue.dequeue();
        // [X X X HT X]
        assertTrue(testQueue.empty());
    }

    @Test
    public void headAndTailMoveCorrectlyWhenEnqueuingPastQueueSize() {
        headAndTailMoveCorrectlyWhenDequeing();
        testQueue.enqueue(1);
        testQueue.enqueue(2);
        // [T X X H 2]
        assertEquals(3, testQueue.getHead());
        assertEquals(0, testQueue.getTail());
        assertEquals(2, testQueue.size());
        assertEquals(1, (int) testQueue.get(3));
        assertEquals(2, (int) testQueue.get(4));
        testQueue.enqueue(3);
        testQueue.enqueue(4);
        // [3 4 T H 2]
        assertEquals(3, testQueue.getHead());
        assertEquals(2, testQueue.getTail());
        assertEquals(4, testQueue.size());
        assertEquals(1, (int) testQueue.get(3));
        assertEquals(2, (int) testQueue.get(4));
        assertEquals(3, (int) testQueue.get(0));
        assertEquals(4, (int) testQueue.get(1));
    }

    @Test
    public void headAndTailMoveCorrectlyWhenQueueuingPastQueueMaxSize() {
        headAndTailMoveCorrectlyWhenEnqueuingPastQueueSize();
        testQueue.enqueue(5);
        // [3 4 5 HT 2]! --> [H 2 3 4 5 T X X X X]
        assertEquals(0, testQueue.getHead());
        assertEquals(5, testQueue.getTail());
        assertEquals(5, testQueue.size());
        for (int i = 0; i < 5; i++) {
            assertEquals(i + 1, (int) testQueue.get(i));
        }
        for (int i = 5; i < 10; i++) {
            assertEquals(null, testQueue.get(i));
        }
    }

    @Test
    public void dequeueFromMuchUsedList() {
        headAndTailMoveCorrectlyWhenQueueuingPastQueueMaxSize();
        for (int i = 0; i < 5; i++) {
            assertEquals(5 - i, testQueue.size());
            assertEquals(i + 1, (int) testQueue.dequeue());
            assertEquals(5, testQueue.getTail());
            assertEquals(i + 1, testQueue.getHead());
        }
        assertTrue(testQueue.empty());
        assertEquals(5, testQueue.getHead());
    }
}
