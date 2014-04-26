package com.mycompany.tiralabra_maven.datastructures;

public abstract class Heap {

    protected int[] array;
    protected static final int DEFAULT_SIZE = 10;
    protected int heapsize;

    public int size() {
        return array.length;
    }

    public int heapsize() {
        return heapsize;
    }

    public int top() {
        return array[0];
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

    protected void swap(int i, int j) {
        int tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }

    protected void buildHeap() {
        heapsize = array.length;
        for(int i = array.length/2 - 1; i >= 0 ; i--)
            heapify(i);
    }

    public int extractTop() {
        int top = top();
        array[0] = array[heapsize-1];
        heapsize--;
        heapify(0);
        return top;
    }

    abstract protected void increaseKey(int i, int key);

    abstract protected void heapify(int i);

    public void insert(int key) {
        heapsize++;
        if (heapsize > size()) {
            createNewArray();
        }
        increaseKey(heapsize-1, key);
    }

    private void createNewArray() {
        int[] newArray = new int[size()*2];
        for(int i = 0; i < size(); i++) {
            newArray[i] = array[i];
        }
        array = newArray;
    }

    protected int[] getArray() {
        return array;
    }
}
