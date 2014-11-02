package A_Star;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 *
 * @author Lutikka
 */
public class Heap implements IA_Heap{
    private PriorityQueue queue;
    private boolean max;
    public static final boolean MIN_HEAP=false;
    public static final boolean MAX_HEAP=true;

    public Heap(boolean max, Comparator cmp) {
        this.max=max;
        queue = new PriorityQueue();     
    }

    public void insert(Object o) {
        queue.offer(o);
    }

    public Object peakMin() {
        return queue.peek();
    }

    public Object poolMin() {
        return queue.poll();
    }

    public void remove(Object o) {
        queue.remove(o);
    }
    
    
}
