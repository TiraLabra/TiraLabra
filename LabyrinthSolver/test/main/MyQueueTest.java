/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Juri Kuronen
 */
public class MyQueueTest {

    public MyQueue<Integer> testQueue;

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
    public void headAndTailMoveCorrectly() {
        testQueue = new MyQueue<>(5);
        // [X X X X X]
        assertEquals(0, testQueue.head);
        assertEquals(0, testQueue.tail);
        testQueue.enqueue(5);
        // [H T X X X]
        assertEquals(0, testQueue.head);
        assertEquals(1, testQueue.tail);
        assertEquals(1, testQueue.size());
        assertEquals(5, (int) testQueue.items[0]);
        assertEquals(null, testQueue.items[1]);
        testQueue.enqueue(9);
        // [H 9 T X X]
        assertEquals(0, testQueue.head);
        assertEquals(2, testQueue.tail);
        assertEquals(2, testQueue.size());
        assertEquals(5, (int) testQueue.items[0]);
        assertEquals(9, (int) testQueue.items[1]);
        assertEquals(null, testQueue.items[2]);
        testQueue.enqueue(7);
        // [H 9 7 T X]
        assertEquals(0, testQueue.head);
        assertEquals(3, testQueue.tail);
        assertEquals(3, testQueue.size());
        assertEquals(5, (int) testQueue.items[0]);
        assertEquals(9, (int) testQueue.items[1]);
        assertEquals(7, (int) testQueue.items[2]);
        assertEquals(null, testQueue.items[3]);
        int dequeue = testQueue.dequeue();
        // [X H 7 T X]
        assertEquals(1, testQueue.head);
        assertEquals(3, testQueue.tail);
        assertEquals(2, testQueue.size());
        assertEquals(5, dequeue);
        assertEquals(9, (int) testQueue.items[1]);
        assertEquals(7, (int) testQueue.items[2]);
        assertEquals(null, testQueue.items[3]);
        testQueue.dequeue();
        testQueue.dequeue();
        // [X X X HT X]
        assertTrue(testQueue.empty());
        testQueue.enqueue(1);
        testQueue.enqueue(2);
        // [T X X H 2]
        assertEquals(3, testQueue.head);
        assertEquals(0, testQueue.tail);
        assertEquals(2, testQueue.size());
        assertEquals(1, (int) testQueue.items[3]);
        assertEquals(2, (int) testQueue.items[4]);
        testQueue.enqueue(3);
        testQueue.enqueue(4);
        // [3 4 T H 2]
        assertEquals(3, testQueue.head);
        assertEquals(2, testQueue.tail);
        assertEquals(4, testQueue.size());
        assertEquals(1, (int) testQueue.items[3]);
        assertEquals(2, (int) testQueue.items[4]);
        assertEquals(3, (int) testQueue.items[0]);
        assertEquals(4, (int) testQueue.items[1]);
    }

    @Test
    public void headAndTailPastMaxSize() {
        headAndTailMoveCorrectly();
        testQueue.enqueue(5);
        // [3 4 5 HT 2]! --> [H 2 3 4 5 T X X X X]
        assertEquals(0, testQueue.head);
        assertEquals(5, testQueue.tail);
        assertEquals(5, testQueue.size());
        for (int i = 0; i < 5; i++) {
            assertEquals(i + 1, (int) testQueue.items[i]);
        }
        for (int i = 5; i < 10; i++) {
            assertEquals(null, testQueue.items[i]);
        }
    }
}
