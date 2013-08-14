package fi.jw.cs.tiralabra;

import java.util.Comparator;

/**
 * @author Jan Wikholm <jw@jw.fi>
 * @since 2013-08-13
 */
public class SimplePriorityQueue<T extends Comparable<T>> {

    MinimumHeap<T> heap;

    public SimplePriorityQueue() {
        heap = new MinimumHeap<T>();
    }

    public SimplePriorityQueue(Comparator<T> comparator) {
        heap = new MinimumHeap<T>(comparator);
    }

    public SimplePriorityQueue(SimplePriorityQueue<T> source) {
        heap = new MinimumHeap<T>(source.getHeap());
    }

    public void add(T t) {
        heap.insert(t);
    }

    public T poll() {
        return heap.deleteMinimum();
    }

    public int size() {
        return heap.getSize();
    }

    public boolean isEmpty() {
        return heap.getSize() == 0;
    }

    public MinimumHeap<T> getHeap() {
        return heap;
    }
}
