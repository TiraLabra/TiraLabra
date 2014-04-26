package com.mycompany.tiralabra_maven.datastructures;

import com.mycompany.tiralabra_maven.datastructures.Heap;
import org.junit.Test;
import static org.junit.Assert.*;

public class HeapTest {

    Heap heap = new Heap() {
        protected void heapify(int i) {
        }

        @Override
        protected void increaseKey(int i, int key) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
