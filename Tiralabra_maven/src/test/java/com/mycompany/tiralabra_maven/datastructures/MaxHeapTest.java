package com.mycompany.tiralabra_maven.datastructures;

import java.util.Arrays;
import org.junit.Test;
import static org.junit.Assert.*;

public class MaxHeapTest {

    MaxHeap heap;

    @Test
    public void testHeapify() {
        Integer[] array = new Integer[]{16, 4, 10, 14, 7, 9, 3, 2, 8, 1};
        heap = new MaxHeap(array);
        heap.heapify(1);
        assertArrayEquals(Arrays.toString(array), new Integer[]{16, 14, 10, 8, 7, 9, 3, 2, 4, 1}, array);
    }

    @Test
    public void testBuildMaxHeap() {
        Integer[] array = new Integer[]{4, 1, 3, 2, 16, 9, 10, 14, 8, 7};
        heap = new MaxHeap(array);
        heap.buildHeap();
        assertArrayEquals(new Integer[]{16, 14, 10, 8, 7, 9, 3, 2, 4, 1}, array);
    }

    @Test
    public void testExtractTop() {
        Integer[] array = new Integer[]{4, 1, 3, 2, 16, 9, 10, 14, 8, 7};
        heap = new MaxHeap(array);
        heap.buildHeap();
        Integer top = (Integer) heap.extractTop();
        assertEquals(9, heap.heapsize());
        assertEquals(16, top.intValue());
        assertArrayEquals(Arrays.toString(array), new Integer[]{14, 8, 10, 4, 7, 9, 3, 2, 1, 1}, array);
    }

    @Test
    public void testInsertSmall() {
        Integer[] array = new Integer[]{4, 1, 3, 2, 16, 9, 10, 14, 8, 7};
        heap = new MaxHeap(array);
        heap.buildHeap();
        heap.extractTop();
        heap.insert(5);
        assertEquals(10, heap.heapsize());
        assertArrayEquals(Arrays.toString(array), new Integer[]{14, 8, 10, 4, 7, 9, 3, 2, 1, 5}, array);
    }

    @Test
    public void testInsertLarge() {
        Integer[] array = new Integer[]{4, 1, 3, 2, 16, 9, 10, 14, 8, 7};
        heap = new MaxHeap(array);
        heap.buildHeap();
        heap.extractTop();
        heap.insert(15);
        assertEquals(10, heap.heapsize());
        assertArrayEquals(Arrays.toString(array), new Integer[]{15, 14, 10, 4, 8, 9, 3, 2, 1, 7}, array);
    }

    @Test
    public void testInsertMoreThanArrayLength() {
        heap = new MaxHeap(new Integer[]{4, 1, 3, 2, 16, 9, 10, 14, 8, 7});
        heap.buildHeap();
        heap.insert(17);
        assertEquals(11, heap.heapsize());
        Object[] array = heap.getArray();
        assertArrayEquals(Arrays.toString(array), new Integer[]{17, 16, 10, 8, 14, 9, 3, 2, 4, 1, 7, null,null,null,null,null,null,null,null,null}, array);
    }
}
