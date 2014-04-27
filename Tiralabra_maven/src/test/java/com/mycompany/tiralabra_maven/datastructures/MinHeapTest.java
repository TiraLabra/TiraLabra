package com.mycompany.tiralabra_maven.datastructures;

import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

public class MinHeapTest extends HeapTest {

    @Before
    public void setup() {
        heap = new MaxHeap(new Valuable[]{});
    }

    @Test
    public void testHeapify() {
        Integer[] array = new Integer[]{1, 17, 3, 2, 19, 36, 7, 25, 100};
        heap = new MinHeap(toNodeList(array));
        heap.heapify(1);
        assertOk(new Integer[]{1, 2, 3, 17, 19, 36, 7, 25, 100});
    }

    @Test
    public void testBuildMinHeap() {
        Integer[] array = new Integer[]{25, 17, 3, 2, 19, 36, 7, 1, 100};
        heap = new MinHeap(toNodeList(array));
        heap.buildHeap();
        assertOk(new Integer[]{1, 2, 3, 17, 19, 36, 7, 25, 100});
    }

    @Test
    public void testExtractTop() {
        Integer[] array = new Integer[]{1, 2, 3, 17, 19, 36, 7, 25, 100};
        heap = new MinHeap(toNodeList(array));
        heap.buildHeap();
        int top = (Integer) heap.extractTop().key();
        assertEquals(8, heap.heapsize());
        assertEquals(1, top);
        assertOk(new Integer[]{2, 17, 3, 25, 19, 36, 7, 100, 100});
    }

    @Test
    public void testInsertLarge() {
        Integer[] array = new Integer[]{1, 2, 3, 17, 19, 36, 7, 25, 100};
        heap = new MinHeap(toNodeList(array));
        heap.buildHeap();
        heap.extractTop();
        heap.insert(new Node(35));
        assertEquals(9, heap.heapsize());
        assertOk(new Integer[]{2, 17, 3, 25, 19, 36, 7, 100, 35});
    }

    @Test
    public void testInsertSmall() {
        Integer[] array = new Integer[]{1, 2, 3, 17, 19, 36, 7, 25, 100};
        heap = new MinHeap(toNodeList(array));
        heap.buildHeap();
        heap.extractTop();
        heap.insert(new Node(-1));
        assertEquals(9, heap.heapsize());
        assertOk(new Integer[]{-1, 2, 3, 17, 19, 36, 7, 100, 25});
    }

    @Test
    public void testInsertMoreThanArrayLength() {
        Integer[] array = new Integer[]{1, 2, 3, 17, 19, 36, 7, 25, 100};
        heap = new MinHeap(toNodeList(array));
        heap.buildHeap();
        heap.insert(new Node(150));
        assertEquals(10, heap.heapsize());
        assertArrayEquals(toNodeList(18, new Integer[]{1, 2, 3, 17, 19, 36, 7, 25, 100, 150}), heap.getArray());
    }
}
