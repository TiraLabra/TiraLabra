package Collections;

import java.util.Iterator;

/**
 * Linked list, provides constant add operation and linear contain and remove
 * operations. Guaranteed to not contain null pointers.
 *
 * @param <T> Type of the objects on the list.
 */
public final class LinkedList<T> implements Iterable<T>, ICollection<T> {

    private final Member<T> head;
    private int count;

    /**
     * Creates new empty linked list.
     */
    public LinkedList() {
        this.head = new Member<>(null);
    }

    @Override
    public void remove(final T item) {
        if (item == null) {
            return;
        }
        final Member<T> itemMember = get(item);
        if (itemMember != null) {
            final Member<T> previous = itemMember.previous;
            final Member<T> next = itemMember.next;
            if (itemMember.hasNext()) {
                next.previous = previous;
                previous.next = next;
            } else {
                previous.next = null;
            }
            count--;
        }
    }

    /**
     * Finds and item from the list.
     *
     * @param item Item to find
     * @return The found item, or null if not found.
     */
    private Member<T> get(final T item) {
        Member<T> current = head;
        while (current.hasNext()) {
            final Member<T> next = current.next;
            if (next.value.equals(item)) {
                return next;
            }
            current = current.next;
        }
        return null;
    }

    @Override
    public boolean contains(final T item) {
        if (item == null) {
            return false;
        }
        return get(item) != null;
    }

    @Override
    public void add(final T item) {
        if (item == null) {
            return;
        }
        final Member<T> added = new Member<>(item);
        added.next = head.next;
        added.previous = head;
        if (head.next != null) {
            head.next.previous = added;
        }
        head.next = added;
        count++;
    }

    @Override
    public int size() {
        return count;
    }

    @Override
    public Iterator<T> iterator() {
        return new ListIterator<>(head);
    }

    /**
     * The linkedlist iterator.
     *
     * @param <T> The list member type.
     */
    private final class ListIterator<T> implements Iterator<T> {

        private Member<T> current;

        private ListIterator(final Member<T> head) {
            this.current = head;
        }

        @Override
        public boolean hasNext() {
            return current.next != null;
        }

        @Override
        public T next() {
            current = current.next;
            return current.value;
        }

        @Override
        public void remove() {
            current.previous.next = current.next;
            if (current.next != null) {
                current.next.previous = current.previous;
            }
            count--;
        }
    }

    /**
     * Linked list member container.
     *
     * @param <T> Linked list type.
     */
    private final class Member<T> {

        private final T value;
        private Member<T> next;
        private Member<T> previous;

        private Member(final T value) {
            this.value = value;
        }

        private boolean hasNext() {
            return next != null;
        }
    }
}
