package com.mycompany.tiralabra_maven.datastructures;

public class MaxHeap extends AbstractHeap {

    public MaxHeap() {
        array = new Comparable[DEFAULT_SIZE];
    }

    public MaxHeap(Comparable[] array) {
        this.array = array;
        heapsize = array.length;
    }

    @Override
    protected void heapify(int i) {
        int largest;
        int left = left(i);
        int right = right(i);
        if (left < heapsize && array[left].hashCode() > array[i].hashCode())
            largest = left;
        else largest = i;
        if (right < heapsize && array[right].hashCode() > array[largest].hashCode())
            largest = right;
        if (largest != i) {
            swap(i, largest);
            heapify(largest);
        }
    }

    @Override
    protected void increaseKey(int i, Object key) {
        array[i] = key;
        while(i > 0 && array[parent(i)].hashCode() < array[i].hashCode()) {
            swap(i, parent(i));
            i = parent(i);
        }
    }
}
