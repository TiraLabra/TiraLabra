package Collections;

/**
 * A Generic collection of objects of given type.
 *
 * @param <T> Type of the collection.
 */
public interface ICollection<T> {

    /**
     * Add a new item to the collection.
     *
     * @param item To add.
     */
    public void add(final T item);

    /**
     * Removes an item from the collection.
     *
     * @param item To Remove.
     */
    public void remove(final T item);

    /**
     * Is the following item in the collection?
     *
     * @param item Item to find.
     * @return Is the following item in the collection
     */
    public boolean contains(final T item);

    /**
     * Size of the collection.
     *
     * @return Size of the collection.
     */
    public int size();
}
