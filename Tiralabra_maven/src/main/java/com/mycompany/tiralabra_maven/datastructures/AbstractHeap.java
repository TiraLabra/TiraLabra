package com.mycompany.tiralabra_maven.datastructures;

public abstract class AbstractHeap<T> {

    protected Object[] array;
    protected static final int DEFAULT_SIZE = 10;
    protected int heapsize;

    public int size() {
        return array.length;
    }

    public int heapsize() {
        return heapsize;
    }

    public T top() {
        return (T) array[0];
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
        Object tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }

    protected void buildHeap() {
        heapsize = array.length;
        for(int i = array.length/2 - 1; i >= 0 ; i--)
            heapify(i);
    }

    public T extractTop() {
        T top = top();
        array[0] = array[heapsize-1];
        heapsize--;
        heapify(0);
        return top;
    }

    abstract protected void increaseKey(int i, Object key);

    abstract protected void heapify(int i);

    public void insert(Object key) {
        heapsize++;
        if (heapsize > size()) {
            createNewArray();
        }
        increaseKey(heapsize-1, key);
    }

    private void createNewArray() {
        Object[] newArray = new Object[size() * 2];
        System.arraycopy(array, 0, newArray, 0, size());
        array = newArray;
    }

    protected Object[] getArray() {
        return array;
    }

    public boolean contains(T t) {
        return search(t) != -1;
    }

    private int search(T t) {
        for (int i = 0; i < heapsize; i++)
            if (t.equals(array[i])) return i;
        return -1;
    }
    
    public void remove(T t) {
        int i = search(t);
        if (i == -1) return;
        moveTo(i);
        heapsize--;
    }

    private void moveTo(int index) {
        for (int i = index + 1; i < heapsize; i++)
            array[i - 1] = array[i];
    }
}
