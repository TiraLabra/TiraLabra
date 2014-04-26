package com.mycompany.tiralabra_maven.datastructures;

import org.junit.Test;
import static org.junit.Assert.*;

public class HeapTest {

    AbstractHeap heap = new AbstractHeap() {
        protected void heapify(int i) {
        }

        @Override
        protected void increaseKey(int i, Object key) {
        }
    };

    @Test
    public void testParent() {
        assertEquals(0, heap.parent(1));
        assertEquals(0, heap.parent(2));

        assertEquals(1, heap.parent(3));
        assertEquals(1, heap.parent(4));

        assertEquals(2, heap.parent(5));
        assertEquals(2, heap.parent(6));
    }

    @Test
    public void testLeftRight() {
        assertEquals(1, heap.left(0));
        assertEquals(2, heap.right(0));

        assertEquals(3, heap.left(1));
        assertEquals(4, heap.right(1));

        assertEquals(5, heap.left(2));
        assertEquals(6, heap.right(2));
    }

}
