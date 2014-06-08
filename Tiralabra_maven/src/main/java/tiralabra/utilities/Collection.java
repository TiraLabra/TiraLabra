/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tiralabra.utilities;

/**
 *
 * @author atte
 * @param <E>
 */
public interface Collection<E> extends Iterable<E> {

    /**
     * Appends the object to the last position in the Collection.
     *
     * @param e
     */
    public void add(E e);

    /**
     * Removes the object from the Collection and moves each object after the
     * object in the array one position back.
     *
     * @param e
     * @return true - if the object was found and removed.
     */
    public boolean remove(E e);

    /**
     * Whether the Collection contains the specified object. For correct
     * results, the Object must have overridden the equals() -method.
     *
     * @param e
     * @return true - if the list contains the object
     */
    public boolean contains(E e);

    /**
     * Gets the object at the given index.
     * @return Object at the given index.
     */
    public E get(int i);
    
    /**
     * Returns the number of objects currently stored in the collection.
     * @return size
     */
    public int size();
    
    /**
     * Whether the Collection is empty.
     *
     * @return true - if the Collection is empty
     */
    public boolean isEmpty();

    /**
     * Doubles the holding capacity of the collection by creating a new array
     * with a two times the length of the current one and copying all objects
     * there.
     */
    public void growCapacity();

    /**
     * Returns all of the objects stored in the list as an array.
     *
     * @return
     */
    public E[] toArray();

    /**
     * Removes all objects from the Collection by discarding the current array
     * and creating a new one - with the original initialCapacity, if specified.
     */
    public void clear();
}
