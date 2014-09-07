package Collections;

public interface ICollection<T> {

    public void add(final T item);

    public void remove(final T item);

    public boolean contains(final T item);

    public int size();
}
