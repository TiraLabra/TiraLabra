package com.mycompany.tiralabra_maven.datastructures;


public class PriorityQueue {

    private final AbstractHeap heap;

    public PriorityQueue(AbstractHeap heap) {
        this.heap = heap;
    }

    public void enqueue(Valuable n) {
        heap.insert(n);
    }

    public Valuable dequeue() {
        return heap.extractTop();
    }

    public int size() {
        return heap.heapsize();
    }

    public boolean contains(Valuable n) {
        return heap.contains(n);
    }

    public void remove(Valuable n) {
        heap.remove(n);
    }
    
    public static PriorityQueue createMinPriorityQueue() {
        return new PriorityQueue(new MinHeap());
    }
    
    public static PriorityQueue createMaxPriorityQueue() {
        return new PriorityQueue(new MaxHeap());
    }

    public boolean isEmpty() {
        return heap.heapsize == 0;
    }
}
