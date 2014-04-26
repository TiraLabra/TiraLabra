package com.mycompany.tiralabra_maven.datastructures;

import static com.mycompany.tiralabra_maven.datastructures.AbstractHeap.DEFAULT_SIZE;

public class MinHeap extends AbstractHeap {

    public MinHeap() {
        array = new Comparable[DEFAULT_SIZE];
    }

    public MinHeap(Comparable[] array) {
        this.array = array;
        heapsize = array.length;
    }

    @Override
    protected void heapify(int i) {
        int smallest;
        int left = left(i);
        int right = right(i);
        if (left < heapsize && array[left].hashCode() < array[i].hashCode())
            smallest = left;
        else smallest = i;
        if (right < heapsize && array[right].hashCode() < array[smallest].hashCode())
            smallest = right;
        if (smallest != i) {
            swap(i, smallest);
            heapify(smallest);
        }
    }

    @Override
    protected void increaseKey(int i, Object key) {
        array[i] = key;
        while(i > 0 && array[parent(i)].hashCode() > array[i].hashCode()) {
            swap(i, parent(i));
            i = parent(i);
        }
    }
}
