package com.mycompany.tiralabra_maven.datastructures;

/**
 * A max heap.
 * @author Yessergire Mohamed
 */
public class MaxHeap extends AbstractHeap {

    /**
     *
     */
    public MaxHeap() {
        array = new Valuable[DEFAULT_SIZE];
    }

    /**
     *
     * @param array
     */
    public MaxHeap(Valuable[] array) {
        this.array = array;
        heapsize = array.length;
    }

    /**
     *
     * @param i
     */
    @Override
    protected void heapify(int i) {
        int largest;
        int left = left(i);
        int right = right(i);
        if (left < heapsize && array[left].key() > array[i].key())
            largest = left;
        else largest = i;
        if (right < heapsize && array[right].key() > array[largest].key())
            largest = right;
        if (largest != i) {
            swap(i, largest);
            heapify(largest);
        }
    }

    /**
     *
     * @param i
     * @param key
     */
    @Override
    protected void increaseKey(int i, Valuable key) {
        array[i] = key;
        while(i > 0 && array[parent(i)].key() < array[i].key()) {
            swap(i, parent(i));
            i = parent(i);
        }
    }
}
