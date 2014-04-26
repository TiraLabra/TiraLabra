package com.mycompany.tiralabra_maven.datastructures;

public class MaxHeap extends Heap {

    public MaxHeap() {
        array = new int[DEFAULT_SIZE];
    }

    public MaxHeap(int[] array) {
        this.array = array;
        heapsize = array.length;
    }

    @Override
    protected void heapify(int i) {
        int largest;
        int left = left(i);
        int right = right(i);
        if (left < heapsize && array[left] > array[i])
            largest = left;
        else largest = i;
        if (right < heapsize && array[right] > array[largest])
            largest = right;
        if (largest != i) {
            swap(i, largest);
            heapify(largest);
        }
    }

    @Override
    protected void increaseKey(int i, int key) {
        array[i] = key;
        while(i > 0 && array[parent(i)] < array[i]) {
            swap(i, parent(i));
            i = parent(i);
        }
    }
}
