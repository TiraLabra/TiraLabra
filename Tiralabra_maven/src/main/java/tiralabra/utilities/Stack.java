/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tiralabra.utilities;

/**
 * A generic Stack to hold objects in.
 *
 * @author atte
 * @param <E> Object
 */
public class Stack<E> extends AbstractCollection<E> {

    public Stack() {
        super();
    }

    public Stack(int initialCapacity) {
        super(initialCapacity);
    }

    /**
     * Pushes an object to the top of the stack.
     *
     * @param e
     */
    public E push(E e) {
        if (size == array.length) {
            growCapacity();
        }

        array[size] = e;
        size++;
        return e;
    }

    /**
     * Peeks at the object at the top of the stack.
     *
     * @return Object stored at the top the stack.
     */
    public E peek() {
        if (size == 0) {
            throw new IndexOutOfBoundsException("Peeked an empty OperationStack.");
        }

        return (E) array[size - 1];
    }

    /**
     * Returns and removes from the stack the object currently at the top of the
     * stack.
     *
     * @return
     */
    public E pop() {
        if (size == 0) {
            throw new IndexOutOfBoundsException("Popped an empty OperationStack.");
        }
        size--;
        E t = (E) array[size];
        array[size] = null;
        return t;
    }

    @Override
    public boolean add(E e) {
        return push(e) != null;
    }

}
