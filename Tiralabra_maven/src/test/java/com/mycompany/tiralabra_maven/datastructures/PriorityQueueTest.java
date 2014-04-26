package com.mycompany.tiralabra_maven.datastructures;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class PriorityQueueTest {

    private PriorityQueue queue;

    @Before
    public void setUp() {
        queue = PriorityQueue.createMinPriorityQueue();
    }

    @Test
    public void testAddAndRemoveWithOneElement() {
        queue.enqueue(1);
        assertEquals(1, queue.dequeue());
    }

}
