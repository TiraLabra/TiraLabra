package com.mycompany.tiralabra_maven.datastructures;

public class MinHeap {

    int[] array;
    private static final int DEFAULT_SIZE = 10;
    private int heap_size;

    public MinHeap() {
        array = new int[DEFAULT_SIZE];
    }

    public MinHeap(int[] array) {
        this.array = array;
        buildHeap();
    }

    int size() {
        return 1;
    }

    int min() {
        return 1;
    }

    void insert(int i) {
    }

    protected int parent(int i) {
        return (i - 1) / 2;
    }

    protected int left(int i) {
        return i * 2 + 1;
    }

    protected int right(int i) {
        return (i + 1) * 2;
    }

    private void heapify(int i) {
        int largest;
        System.out.print("i: " + i);
        int left = left(i);
        int right = right(i);
        if (right <= heap_size) {
            if (array[left] > array[right])
                largest = left;
            else
                largest = right;

            if (array[i] < array[largest]) {
                swap(i, largest);
                System.out.print(", largest: " + largest);
                heapify(largest);
            }
        } else if (left == heap_size && array[i] < array[left]) {
            swap(i, left);
        }
        System.out.println("");
    }

    private void swap(int i, int j) {
        int tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }

    private void buildHeap() {
        heap_size = array.length - 1;
        for(int i = 0; i < array.length/2; i++)
            heapify(i);
    }
}
