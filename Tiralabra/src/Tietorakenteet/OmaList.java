package Tietorakenteet;
// karsittu version List<T>:stä koska en halua toteuttaa koko rajapintaa
public interface OmaList<T> {

    public int size();

    public boolean isEmpty();

    public boolean contains(Object o);

    public boolean add(T e);

    public boolean remove(Object o);
    
    public void clear();

    public T get(int index);
    
    public T set(int index, Object element);

    public T remove(int index);

    public Object[] toArray();
}
