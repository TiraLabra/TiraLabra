package com.mycompany.tiralabra_maven.datastructures;

/**
 * A priority queue that uses a heap to priorities its elements.
 * @author Yessergire Mohamed
 */
public class PriorityQueue {

    private final AbstractHeap heap;

    /**
     *
     * @param heap
     */
    public PriorityQueue(AbstractHeap heap) {
        this.heap = heap;
    }

    /**
     *
     * @param n
     */
    public void enqueue(Valuable n) {
        heap.insert(n);
    }

    /**
     *
     * @return
     */
    public Valuable dequeue() {
        return heap.extractTop();
    }

    /**
     *
     * @return
     */
    public int size() {
        return heap.heapsize();
    }

    /**
     *
     * @param n
     * @return
     */
    public boolean contains(Valuable n) {
        return heap.contains(n);
    }

    /**
     *
     * @param n
     */
    public void remove(Valuable n) {
        heap.remove(n);
    }
    
    /**
     *
     * @return
     */
    public static PriorityQueue createMinPriorityQueue() {
        return new PriorityQueue(new MinHeap());
    }
    
    /**
     *
     * @return
     */
    public static PriorityQueue createMaxPriorityQueue() {
        return new PriorityQueue(new MaxHeap());
    }

    /**
     *
     * @return
     */
    public boolean isEmpty() {
        return heap.heapsize == 0;
    }
}
