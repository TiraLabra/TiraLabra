package com.mycompany.tiralabra_maven.datastructures;

import java.util.Arrays;
import org.junit.Test;
import static org.junit.Assert.*;

public class MinHeapTest {

    AbstractHeap heap;

    @Test
    public void testHeapify() {
        Integer[] array = new Integer[]{1, 17, 3, 2, 19, 36, 7, 25, 100};
        heap = new MinHeap(array);
        heap.heapify(1);
        assertArrayEquals(new Integer[]{1, 2, 3, 17, 19, 36, 7, 25, 100}, array);
    }

    @Test
    public void testBuildMinHeap() {
        Integer[] array = new Integer[]{25, 17, 3, 2, 19, 36, 7, 1, 100};
        heap = new MinHeap(array);
        heap.buildHeap();
        assertArrayEquals(new Integer[]{1, 2, 3, 17, 19, 36, 7, 25, 100}, array);
    }

    @Test
    public void testExtractTop() {
        Integer[] array = new Integer[]{1, 2, 3, 17, 19, 36, 7, 25, 100};
        heap = new MinHeap(array);
        heap.buildHeap();
        int top = (Integer) heap.extractTop();
        assertEquals(8, heap.heapsize());
        assertEquals(1, top);
        assertArrayEquals(new Integer[]{2, 17, 3, 25, 19, 36, 7, 100, 100}, array);
    }

    @Test
    public void testInsertLarge() {
        Integer[] array = new Integer[]{1, 2, 3, 17, 19, 36, 7, 25, 100};
        heap = new MinHeap(array);
        heap.buildHeap();
        heap.extractTop();
        heap.insert(35);
        assertEquals(9, heap.heapsize());
        assertArrayEquals(new Integer[]{2, 17, 3, 25, 19, 36, 7, 100, 35}, array);
    }

    @Test
    public void testInsertSmall() {
        Integer[] array = new Integer[]{1, 2, 3, 17, 19, 36, 7, 25, 100};
        heap = new MinHeap(array);
        heap.buildHeap();
        heap.extractTop();
        heap.insert(-1);
        assertEquals(9, heap.heapsize());
        assertArrayEquals(Arrays.toString(array),new Integer[]{-1, 2, 3, 17, 19, 36, 7, 100, 25}, array);
    }

    @Test
    public void testInsertMoreThanArrayLength() {
        heap = new MinHeap(new Integer[]{1, 2, 3, 17, 19, 36, 7, 25, 100});
        heap.buildHeap();
        heap.insert(150);
        assertEquals(10, heap.heapsize());
        Object[] array = heap.getArray();
        assertArrayEquals(new Integer[]{1, 2, 3, 17, 19, 36, 7, 25, 100, 150, null,null,null,null,null,null,null,null}, array);
    }
}
