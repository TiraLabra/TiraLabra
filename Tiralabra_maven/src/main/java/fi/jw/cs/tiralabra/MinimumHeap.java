package fi.jw.cs.tiralabra;

import java.util.Comparator;

/**
 * @author Jan Wikholm <jw@jw.fi>
 * @since 2013-08-13
 */
public class MinimumHeap<T extends Comparable> {
    private T[] heap;
    private int size;
    Comparator<T> comparator;

    public MinimumHeap() {
        heap = (T[]) new Comparable[100];
    }

    public MinimumHeap(Comparator cmp) {
        heap = (T[]) new Comparable[100];
        comparator = cmp;
    }

    public MinimumHeap(MinimumHeap<T> source) {
        T[] other = source.getHeap();
        size = source.getSize();
        heap = (T[]) new Comparable[size];
        for (int i = 0; i < size; i++)
            heap[i] = other[i];

    }

    public void insert(T item) {
        size++;
        if (size > heap.length) {
            T[] newHeap = (T[]) new Comparable[size * 2];
            for (int i = 0; i < heap.length; i++) {
                newHeap[i] = heap[i];
            }
            heap = newHeap;
        }
        if (size == 1) {
            heap[0] = item;
        } else {
            int i = size - 1;
            while (i > 0 && (isFirstSmaller(item, heap[parent(i)]))) {
                heap[i] = heap[parent(i)];
                i = parent(i);
            }

            heap[i] = item;
        }

    }

    public void heapify(int i) {
        int l = left(i);
        int r = right(i);
        if (r < size) {
            int smallest = (isFirstSmaller(heap[l], heap[r])) ? l : r;
            if (isFirstSmaller(heap[smallest], heap[i])) {
                swap(smallest, i);
                heapify(smallest);
            }
        } else if (l == size - 1 && isFirstSmaller(heap[l], heap[i])) {
            swap(i, l);
        }
    }

    public boolean isFirstSmaller(T a, T b) {
        return (comparator != null ? comparator.compare(a, b) < 0 : a.compareTo(b) < 0);
    }

    protected void swap(int a, int b) {
        T temp = heap[a];
        heap[a] = heap[b];
        heap[b] = temp;
    }

    public T deleteMinimum() {
        if (size > 0) {

            T c = heap[0];
            size--;
            if (size > 0) {
                swap(0, size);
                heapify(0);
            }

            return c;
        } else {
            return null;
        }
    }

    int parent(int i) {
        return (i / 2);
    }

    int left(int i) {
        return i * 2;
    }

    int right(int i) {
        return (i * 2) + 1;
    }

    public int getSize() {
        return size;
    }

    public T[] getHeap() {
        return heap;
    }
}
