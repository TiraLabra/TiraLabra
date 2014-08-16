package Collections;

public final class PriorityQueue<T extends Comparable<T>> {

    private final Vector<T> array;

    public PriorityQueue(final Class<T> holdingType) {
        array = new Vector<>(holdingType);
    }

    public void enqueue(final T item) {
        array.add(item);
        int index = array.size() - 1;
        while (index > 0 && array.get(parent(index)).compareTo(item) < 0) {
            array.setAtt(index, array.get(parent(index)));
            index = parent(index);
        }
        array.setAtt(index, item);
    }

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

    public int size() {
        return array.size();
    }

    private T head() {
        return array.get(0);
    }

    private int parent(final int index) {
        return index / 2;
    }

    private int left(final int index) {
        return 2 * index;
    }

    private int right(final int index) {
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
