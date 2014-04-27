package com.mycompany.tiralabra_maven.datastructures;

import org.junit.Test;
import static org.junit.Assert.*;

public abstract class HeapTest {

    protected AbstractHeap heap;

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

    protected Valuable[] toNodeList(Integer... integers) {
        return toNodeList(integers.length, integers);
    }

    protected Valuable[] toNodeList(int length, Integer... integers) {
        Node[] nodes = new Node[length];
        int max = (length < integers.length) ? length : integers.length;
        for (int i = 0; i < max; i++) {
            nodes[i] = new Node(integers[i]);
        }
        return nodes;
    }

    protected void assertOk(Integer[] integers) {
        assertArrayEquals(toNodeList(integers), heap.getArray());
    }

}
