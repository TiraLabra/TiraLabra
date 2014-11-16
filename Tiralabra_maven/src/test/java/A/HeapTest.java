/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package A;

import java.util.Comparator;
import junit.framework.TestCase;

/**
 *
 * @author Lutikka
 */
public class HeapTest extends TestCase {

    public HeapTest(String testName) {
        super(testName);

    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public void testInsertionIncreasesHeapSize() {
        IA_Heap heap = constructHeap();
        heap.insert(3);
        assertFalse("Heap size did not increase from addition of a element", heap.isEmpty());
    }
    
    public void testInsertionIncreasesHeapSizeCorrectly() {
        IA_Heap heap = constructHeap();
        for (int i = -1; i < 1000; i++) {
            heap.insert(i);
        assertEquals("Heap size does not return correct size of elements after n additions", i+2,heap.size());
        }
        
    }

    public void testInsertionOfXNodesAndRemovalOfXNodes() {
        IA_Heap heap = constructHeap();
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < i; j++) {
                heap.insert(j - 100);
            }
            assertEquals("heap size was wrong after insertion of all nodes", i, heap.size());
            for (int j = 0; j < i; j++) {
                heap.remove(j - 100);
            }
            assertEquals("heap size was wrong after deletion of all nodes", 0, heap.size());
        }
    }

    public void testMinHeapReturnsTheMiniumElement() {
        IA_Heap heap = constructHeap();
        for (int i = 1000; i >= -1000; i--) {
            heap.insert(i);
        }
        for (int i = -1000; i <= 1000; i++) {
            Integer a = (Integer)heap.pollMin();
            assertEquals("Heap didn't return the minium element"+i, i, a.intValue());
        }
        assertTrue("Heap was not empty after pooling of all elements",heap.isEmpty());
    }

    /**
     * Constructs new Heap and returns it
     *
     * @return IA_Heap
     */
    private static IA_Heap constructHeap() {
        return new Heap(new Comparator() {

            public int compare(Object o1, Object o2) {
                Integer n1 = (Integer) o1;
                Integer n2 = (Integer) o2;
                return n1 - n2;
            }

        });
    }
}
