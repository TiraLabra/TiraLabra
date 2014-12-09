package com.mycompany.tiralabra_maven;

import com.mycompany.tiralabra_maven.DataStructures.MyPriorityQueue;
import junit.framework.TestCase;

public class MyPriorityQueueTest extends TestCase {

    /** To be added after MinHeap works fully
     */
    public void testDummy()
    {
        MyPriorityQueue queue = new MyPriorityQueue(5);
        assertTrue(queue.getHeapSize() == 0);
    }
}




