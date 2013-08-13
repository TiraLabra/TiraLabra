package fi.jw.cs.tiralabra;

/**
 * @author Jan Wikholm <jw@jw.fi>
 * @since 2013-08-13
 */
public class MinimumHeap {
    Comparable[] heap;
    int size;

    public MinimumHeap() {
        heap = new Comparable[100];
    }

    public void insert(Comparable item) {
        size++;
        if (size > heap.length) {
            Comparable[] newHeap = new Comparable[size * 2];
            for (int i = 0; i < heap.length; i++) {
                newHeap[i] = heap[i];
            }
            heap = newHeap;
        }
        int i = size;
        while (i > 0 && (heap[parent(i)].compareTo(item) > 0)) {
            heap[i] = heap[parent(i)];
            i = parent(i);
        }

        heap[i] = item;

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

    public boolean isFirstSmaller(Comparable a, Comparable b) {
        return (a.compareTo(b) < 0);
    }

    protected void swap(int a, int b) {
        Comparable temp = heap[a];
        heap[a] = heap[b];
        heap[b] = heap[a];
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

}
