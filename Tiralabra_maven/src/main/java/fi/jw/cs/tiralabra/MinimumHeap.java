package fi.jw.cs.tiralabra;

import java.util.Comparator;

/**
 * Minimum heap implementation by using an array and simple index operations to travel parent-child axis.
 * <p>
 * The index operations in addition to insert/heapify are based on the maximum heap pseudo code from the data
 * structures 2013 spring course.
 * </p>
 * <p>
 * Elements are ordered in by natural ordering (hence <code>Comparable</code>) or by a Comparator of the right type
 * </p>
 *
 * @author Jan Wikholm <jw@jw.fi>
 * @see Comparable
 * @see Comparator
 * @since 2013-08-13
 */
public class MinimumHeap<T extends Comparable> {

    /**
     * The value of this initial size was purely arbitrary.
     */
    public static final int INITIAL_ARRAY_SIZE = 100;

    private T[] heap;
    private int size;
    Comparator<T> comparator;

    public MinimumHeap() {
        heap = (T[]) new Comparable[INITIAL_ARRAY_SIZE];
    }

    public MinimumHeap(Comparator cmp) {
        heap = (T[]) new Comparable[INITIAL_ARRAY_SIZE];
        comparator = cmp;
    }

    /**
     * Create a copy of another heap instance
     *
     * @param source MinimumHeap to copy as a new instance
     */
    public MinimumHeap(MinimumHeap<T> source) {
        T[] other = source.getHeap();
        size = source.getSize();
        comparator = source.getComparator();
        heap = (T[]) new Comparable[size + 1];
        for (int i = 1; i <= size; i++)
            heap[i] = other[i];

    }


    public void insert(T item) {
        size++;

        makeSureDataFits();

        if (size == 1) {
            heap[1] = item;
        } else {
            int i = size;
            while (i > 1 && (isFirstSmaller(item, heap[parent(i)]))) {
                heap[i] = heap[parent(i)];
                i = parent(i);
            }

            heap[i] = item;
        }

    }

    @Override
    public String toString() {
        String str = "Heap{";
        for (int i = 1; i <= size; i++) {
            str += "\n" + i + " = " + heap[i];
        }
        System.out.println("};");
        return str;
    }

    /**
     * If heap array is not large enough, double it in size.
     */
    private void makeSureDataFits() {
        if (size >= heap.length) {
            T[] newHeap = (T[]) new Comparable[size * 2 + 1];
            for (int i = 1; i < heap.length; i++) {
                newHeap[i] = heap[i];
            }
            heap = newHeap;
        }
    }

    public void heapify(int i) {
        int l = left(i);
        int r = right(i);
        if (r <= size) {
            int smallest = (isFirstSmaller(heap[l], heap[r])) ? l : r;
            if (isFirstSmaller(heap[smallest], heap[i])) {
                swap(smallest, i);
                heapify(smallest);
            }
        } else if (l == size && isFirstSmaller(heap[l], heap[i])) {
            swap(i, l);
        }
    }

    /**
     * Take two Comparable objects and compare them naturally or with a Comparator. This is mainly to make code more
     * readable
     *
     * @param a first Comparable
     * @param b second Comparable
     * @return true if first is smaller...
     */
    public boolean isFirstSmaller(T a, T b) {
        return (comparator != null ? comparator.compare(a, b) < 0 : a.compareTo(b) < 0);
    }

    /**
     * Swaps two objects in place in heap at indices a and b
     *
     * @param a index of the first object to swap
     * @param b index of the second object to swap
     */
    protected void swap(int a, int b) {
        T temp = heap[a];
        heap[a] = heap[b];
        heap[b] = temp;
    }

    /**
     * Get the smallest object and make sure the heap stays sorted.
     *
     * @return smallest object.
     */
    public T deleteMinimum() {
        if (size > 0) {

            T c = heap[1];
            swap(1, size);
            size--;
            heapify(1);

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

    public Comparator<T> getComparator() {
        return comparator;
    }
}
