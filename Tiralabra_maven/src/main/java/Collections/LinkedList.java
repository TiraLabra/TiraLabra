package Collections;

public final class LinkedList<T> {

    private final Member<T> head;

    public LinkedList() {
        this.head = new Member<>();
    }

    public void remove(final T item) {
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

    private Member<T> get(final T item) {
        Member<T> current = head;
        while (current.hasNext()) {
            final Member<T> next = current.next;
            if (next.equals(item)) {
                return next;
            }
            current = current.next;
        }
        return null;
    }

    public boolean contains(final T item) {
        return get(item) != null;
    }

    public void add(final T item) {
        Member<T> current = head;
        while (current.hasNext()) {
            current = current.next;
        }
        final Member<T> added = new Member<>();
        added.previous = current;
        current.next = added;
    }

    private final class Member<T> {

        private Member<T> next;
        private Member<T> previous;

        public boolean hasNext() {
            return next != null;
        }
    }
}
