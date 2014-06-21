package util;

import java.util.Random;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class MyPriorityHeapTest {

    MyPriorityHeap testHeap;
    Random r;

    @Before
    public void setUp() {
        testHeap = new MyPriorityHeap();
        r = new Random();
    }

    @Test
    public void adding() {
        for (int i = 0; i < 15; i++) {
            testHeap.insert(99, r.nextInt(50), 0);
            assertEquals(i + 1, testHeap.size());
        }
    }

    @Test
    public void heapPropertyMaintainedOnAdding() {
        int oldMin = Integer.MAX_VALUE;
        for (int i = 0; i < 15; i++) {
            int rand = r.nextInt(50);
            testHeap.insert(99, rand, 0);
            int min = testHeap.get(0);
            assertTrue(min <= rand);
            assertTrue(min <= oldMin);
            oldMin = min;
        }
    }

    @Test
    public void addingPastHeapSize() {
        for (int j = 0; j < 152; j++) {
            int rand = r.nextInt(50);
            testHeap.insert(99, rand, 0);
            assertTrue(testHeap.size() == j + 1);
        }
    }

    @Test
    public void removingMakesHeapEmpty() {
        testHeap.insert(99, 1, 0);
        testHeap.insert(99, 2, 0);
        testHeap.removeMin();
        testHeap.removeMin();
        assertTrue(testHeap.empty());
    }

    @Test
    public void removingFromEmptyHeapDoesntWork() {
        removingMakesHeapEmpty();
        assertEquals(null, testHeap.removeMin());
        assertTrue(testHeap.empty());
    }

    @Test
    public void heapPropertyMaintainedOnRemoving() {
        addingPastHeapSize();
        while (!testHeap.empty()) {
            int min = testHeap.get(0);
            int removed = testHeap.removeMin().getValue();
            assertTrue(min <= removed);
        }
    }
}
