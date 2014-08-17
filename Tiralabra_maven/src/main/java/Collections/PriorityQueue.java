package Collections;

/**
 * Priority queue with heap implementation. A queue where the member with
 * highest value is first and lowest is last. Value is taken from the Comparable
 * interface. Provides logarithmic enqueue and dequeue operations.
 *
 * @param <T> The members of the queue. Must implement Comparable<T> interface.
 */
public final class PriorityQueue<T extends Comparable<T>> {

    private final Vector<T> array;

    /**
     * Create a new, empty priority queue.
     *
     * @param holdingType The queue member type.
     */
    public PriorityQueue(final Class<T> holdingType) {
        array = new Vector<>(holdingType);
    }

    /**
     * Adds a new member to the queue.
     *
     * @param item To add.
     */
    public void enqueue(final T item) {
        array.add(item);
        int index = array.size() - 1;
        while (index > 0 && array.get(parent(index)).compareTo(item) < 0) {
            array.setAtt(index, array.get(parent(index)));
            index = parent(index);
        }
        array.setAtt(index, item);
    }

    /**
     * Returns the member with highest value from the queue and removes it. If
     * the queue is empty an exception is thrown.
     *
     * @return The member with highest value in the queue.
     */
    public T dequeue() {
        if (array.size() == 0) {
            throw new ArrayIndexOutOfBoundsException("The priority queue is empty");
        }
        final T head = head();
        array.setAtt(0, array.get(array.size() - 1));
        array.removeLast();
        heapify(0);
        return head;
    }

    /**
     * The amount of members in the queue.
     *
     * @return The amount of members in the queue.
     */
    public int size() {
        return array.size();
    }

    private T head() {
        return array.get(0);
    }

    private static int parent(final int index) {
        return index / 2;
    }

    private static int left(final int index) {
        return 2 * index;
    }

    private static int right(final int index) {
        return 2 * index + 1;
    }

    private void heapify(final int index) {
        final int left = left(index);
        final int right = right(index);
        if (right <= array.size()) {
            final int largest = largest(left, right);
            if (array.get(index).compareTo(array.get(largest)) < 0) {
                array.switchAtIndex(index, largest);
                heapify(largest);
            }
        } else if (left == array.size() - 1 && array.get(index).compareTo(array.get(left)) < 0) {
            array.switchAtIndex(index, left);
        }
    }

    private int largest(final int left, final int right) {
        if (right == array.size()) {
            return left;
        }
        final T leftObject = array.get(left);
        final T rightObject = array.get(right);
        if (leftObject.compareTo(rightObject) > 0) {
            return left;
        }
        return right;
    }
}
