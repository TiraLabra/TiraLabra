package com.mycompany.tiralabra_maven.datastructures;

public abstract class AbstractHeap {

    protected Valuable[] array;
    protected static final int DEFAULT_SIZE = 10;
    protected int heapsize;

    public int size() {
        return array.length;
    }

    public int heapsize() {
        return heapsize;
    }

    public Valuable top() {
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
        Valuable tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }

    protected void buildHeap() {
        heapsize = array.length;
        for(int i = array.length/2 - 1; i >= 0 ; i--)
            heapify(i);
    }

    public Valuable extractTop() {
        Valuable top = top();
        array[0] = array[heapsize-1];
        heapsize--;
        heapify(0);
        return top;
    }

    abstract protected void increaseKey(int i, Valuable n);

    abstract protected void heapify(int i);

    public void insert(Valuable key) {
        heapsize++;
        if (heapsize > size()) {
            createNewArray();
        }
        increaseKey(heapsize-1, key);
    }

    private void createNewArray() {
        Valuable[] newArray = new Valuable[size() * 2];
        System.arraycopy(array, 0, newArray, 0, size());
        array = newArray;
    }

    protected Valuable[] getArray() {
        return array;
    }

    public boolean contains(Valuable n) {
        return search(n) != -1;
    }

    private int search(Valuable n) {
        for (int i = 0; i < heapsize; i++)
            if (n.equals(array[i])) return i;
        return -1;
    }
    
    public void remove(Valuable n) {
        int i = search(n);
        if (i == -1) return;
        moveTo(i);
        heapsize--;
    }

    private void moveTo(int index) {
        for (int i = index + 1; i < heapsize; i++)
            array[i - 1] = array[i];
    }
}
