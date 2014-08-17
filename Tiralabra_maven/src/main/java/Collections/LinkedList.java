package Collections;

/**
 * Linked list, provides constant add operation and linear contain and remove
 * operations. Guaranteed to not contain null pointers.
 *
 * @param <T> Type of the objects on the list.
 */
public final class LinkedList<T> {

    private final Member<T> head;

    /**
     * Creates new empty linked list.
     */
    public LinkedList() {
        this.head = new Member<>(null);
    }

    /**
     * Removes the item from the list.
     *
     * @param item The item to remove.
     */
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

    /**
     * Does to list contain the item?
     *
     * @param item Item to search.
     * @return Does to list contain the item.
     */
    public boolean contains(final T item) {
        if (item == null) {
            return false;
        }
        return get(item) != null;
    }

    /**
     * Adds a new item to the list.
     *
     * @param item To be added.
     */
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

        public Member(final T value) {
            this.value = value;
        }

        public boolean hasNext() {
            return next != null;
        }
    }
}
