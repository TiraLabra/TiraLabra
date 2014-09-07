package Collections;

/**
 * A set of items, provides average constant time operations for add, remove and
 * contains. Guaranteed to not contain nulls.
 *
 * @param <T> The member type.
 */
public final class HashSet<T> implements ICollection<T> {

    private static final float fillRation = 0.5f;
    private static final int vectorMultiplier = 2;
    private Vector<LinkedList<T>> array;
    private int count;

    /**
     * Creates a new hashset with specified size.
     *
     * @param size The set size.
     */
    public HashSet(final int size) {
        this.array = createList(size);
    }

    /**
     * Creates a new hashset with default size.
     */
    public HashSet() {
        this(25);
    }

    @Override
    public void add(final T item) {
        if (item == null) {
            return;
        }
        final int hash = hash(item);
        if (array.get(hash) == null) {
            array.setAtt(hash, new LinkedList<T>());
        }
        final LinkedList<T> list = array.get(hash);
        if (list.contains(item)) {
            list.remove(item);
        } else {
            count++;
        }
        list.add(item);
        if (isOverFilled()) {
            reHash();
        }
    }

    T get(final T obj) {
        final int hash = hash(obj);
        final LinkedList<T> list = array.get(hash);
        if (list == null) {
            return null;
        }
        for (final T item : list) {
            if (item.equals(obj)) {
                return item;
            }
        }
        return null;
    }

    private Vector<LinkedList<T>> createList(final int size) {
        return (Vector<LinkedList<T>>) new Vector<>(new LinkedList<T>().getClass(), size);
    }

    private boolean isOverFilled() {
        return count * 1f / array.size() > fillRation;
    }

    private void reHash() {
        final HashSet<T> newSet = new HashSet<>(array.size() * vectorMultiplier);
        for (int i = 0; i < array.size(); i++) {
            LinkedList<T> list = array.get(i);
            if (list != null) {
                for (final T item : list) {
                    newSet.add(item);
                }
            }
        }
        array = newSet.array;
        count = newSet.count;
    }

    private int hash(final Object item) {
        return ((item.hashCode() % array.size()) + array.size()) % array.size();
    }

    @Override
    public void remove(final T item) {
        if (item == null) {
            return;
        }
        final int hash = hash(item);
        if (array.get(hash) == null) {
            return;
        }
        final LinkedList<T> list = array.get(hash);
        list.remove(item);
        if (list.size() == 0) {
            array.setAtt(hash, null);
        }
        count--;
    }

    @Override
    public boolean contains(final T item) {
        if (item == null) {
            return false;
        }
        return get(item) != null;
    }

    @Override
    public int size() {
        return count;
    }

    /**
     * Returns all the values in the set.
     *
     * @return All the values in the set.
     */
    public LinkedList<T> values() {
        final LinkedList<T> toReturn = new LinkedList<>();
        for (int i = 0; i < array.size(); i++) {
            final LinkedList<T> list = array.get(i);
            if (list != null) {
                for (final T item : list) {
                    toReturn.add(item);
                }
            }
        }
        return toReturn;
    }
}
