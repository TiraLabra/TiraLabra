package fi.jw.cs.tiralabra;

import java.util.Comparator;

/**
 * The methods of a priority queue as needed by the <code>Huffman</code> class.
 *
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

    /**
     * Create a new priority queue based on another instance
     *
     * @param source another <code>SimplePriorityQueue</code> instance
     */
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
