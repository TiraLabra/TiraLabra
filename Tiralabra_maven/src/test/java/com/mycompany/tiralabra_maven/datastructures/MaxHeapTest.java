package com.mycompany.tiralabra_maven.datastructures;

import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

public class MaxHeapTest extends HeapTest {

    @Before
    public void setup() {
        heap = new MaxHeap(new Valuable[]{});
    }

    @Test
    public void testHeapify() {
        Integer[] array = new Integer[]{16, 4, 10, 14, 7, 9, 3, 2, 8, 1};
        heap = new MaxHeap(toNodeList(array));
        heap.heapify(1);
        assertOk(new Integer[]{16, 14, 10, 8, 7, 9, 3, 2, 4, 1});
    }

    @Test
    public void testBuildMaxHeap() {
        Integer[] array = new Integer[]{4, 1, 3, 2, 16, 9, 10, 14, 8, 7};
        heap = new MaxHeap(toNodeList(array));
        heap.buildHeap();
        assertOk(new Integer[]{16, 14, 10, 8, 7, 9, 3, 2, 4, 1});
    }

    @Test
    public void testExtractTop() {
        Integer[] array = new Integer[]{4, 1, 3, 2, 16, 9, 10, 14, 8, 7};
        heap = new MaxHeap(toNodeList(array));
        heap.buildHeap();
        Integer top = (Integer) heap.extractTop().key();
        assertEquals(9, heap.heapsize());
        assertEquals(16, top.intValue());
        assertOk(new Integer[]{14, 8, 10, 4, 7, 9, 3, 2, 1, 1});
    }

    @Test
    public void testInsertSmall() {
        Integer[] array = new Integer[]{4, 1, 3, 2, 16, 9, 10, 14, 8, 7};
        heap = new MaxHeap(toNodeList(array));
        heap.buildHeap();
        heap.extractTop();
        heap.insert(new Node(5));
        assertEquals(10, heap.heapsize());
        assertOk(new Integer[]{14, 8, 10, 4, 7, 9, 3, 2, 1, 5});
    }

    @Test
    public void testInsertLarge() {
        Integer[] array = new Integer[]{4, 1, 3, 2, 16, 9, 10, 14, 8, 7};
        heap = new MaxHeap(toNodeList(array));
        heap.buildHeap();
        heap.extractTop();
        heap.insert(new Node(15));
        assertEquals(10, heap.heapsize());
        assertOk(new Integer[]{15, 14, 10, 4, 8, 9, 3, 2, 1, 7});
    }

    @Test
    public void testInsertMoreThanArrayLength() {
        heap = new MaxHeap(toNodeList(new Integer[]{4, 1, 3, 2, 16, 9, 10, 14, 8, 7}));
        heap.buildHeap();
        heap.insert(new Node(17));
        assertEquals(11, heap.heapsize());
        assertArrayEquals(toNodeList(20, new Integer[]{17, 16, 10, 8, 14, 9, 3, 2, 4, 1, 7}), heap.getArray());
    }
}
