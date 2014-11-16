package A;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Implementation of a min/max-heap using PriorityQueue. It is possible to
 * switch a max heap to min heap by using complement of the comparator of max
 * heap.
 *
 */
public class Heap implements IA_Heap {

    private PriorityQueue queue;

    /**
     * Construct new a heap.
     *
     * @param cmp
     */
    public Heap(Comparator cmp) {
        queue = new PriorityQueue(cmp);
    }

    /**
     * insert a element to the heap
     *
     * @param o
     */
    public void insert(Object o) {
        queue.add(o);
    }

    /**
     * returns the element with minium value defined by comparator.
     *
     * @return
     */
    public Object peakMin() {
        return queue.peek();
    }

    /**
     * returns and removes the element with minium value defined by comparator.
     *
     * @return
     */
    public Object pollMin() {
        return queue.poll();
    }

    /**
     * removes object o from this heap
     *
     * @param o
     */
    public void remove(Object o) {
        queue.remove(o);
    }

    /**
     * returns a boolean value on whether this heap is empty or not
     *
     * @return
     */
    public boolean isEmpty() {
        return queue.isEmpty();
    }

    public int size(){
        return queue.size();
    }
}
