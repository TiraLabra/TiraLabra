package com.mycompany.tiralabra_maven.datastructures;

import static com.mycompany.tiralabra_maven.datastructures.AbstractHeap.DEFAULT_SIZE;

public class MinHeap extends AbstractHeap {

    public MinHeap() {
        array = new Valuable[DEFAULT_SIZE];
    }

    public MinHeap(Valuable[] array) {
        this.array = array;
        heapsize = array.length;
    }

    @Override
    protected void heapify(int i) {
        int smallest;
        int left = left(i);
        int right = right(i);
        if (left < heapsize && array[left].key() < array[i].key())
            smallest = left;
        else smallest = i;
        if (right < heapsize && array[right].key() < array[smallest].key())
            smallest = right;
        if (smallest != i) {
            swap(i, smallest);
            heapify(smallest);
        }
    }

    @Override
    protected void increaseKey(int i, Valuable key) {
        array[i] = key;
        while(i > 0 && array[parent(i)].key() > array[i].key()) {
            swap(i, parent(i));
            i = parent(i);
        }
    }
}
