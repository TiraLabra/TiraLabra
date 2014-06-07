/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tiralabra.utilities;

import java.util.Arrays;
import java.util.Iterator;

/**
 * A generic Stack to hold objects in.
 *
 * @author atte
 * @param <T> Object
 */
public class Stack<T> implements Collection<T> {

    /**
     * Array which holds the stack.
     */
    private Object[] stack;
    /**
     * An index to keep track of which position of an array we're at.
     */
    private int size;
    /**
     * Worst case needed capacity for holding flips, in which each move flips 20
     * pieces (impossible).
     */
    private static final int defaultCapacity = 1344;
    private final int initialCapacity;

    public Stack(int initialCapacity) {
        stack = new Object[initialCapacity];
        this.initialCapacity = initialCapacity;
        size = 0;
    }

    public Stack() {
        this(defaultCapacity);
    }

    /**
     * Pushes an object to the top of the stack.
     *
     * @param t
     */
    public void push(T t) {
        if (size == stack.length) {
            growCapacity();
        }

        stack[size] = t;
        size++;
    }

    /**
     * Peeks at the object at the top of the stack.
     *
     * @return Object stored at the top the stack.
     */
    public T peek() {
        if (size == 0) {
            throw new IndexOutOfBoundsException("Peeked an empty OperationStack.");
        }

        return (T) stack[size - 1];
    }

    /**
     * Whether the stack is empty.
     *
     * @return
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Returns and removes from the stack the object currently at the top of the
     * stack.
     *
     * @return
     */
    public T pop() {
        if (size == 0) {
            throw new IndexOutOfBoundsException("Popped an empty OperationStack.");
        }
        size--;
        T t = (T) stack[size];
        stack[size] = null;
        return t;
    }

    /**
     * Returns the size of the stack (or how many objects are currently stored
     * there).
     *
     * @return size
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Grows the size of the stack to double of the original value.
     */
    @Override
    public void growCapacity() {
        int newSize = size() * 2;
        stack = Arrays.copyOf(stack, newSize);
    }

    @Override
    public void clear() {
        this.stack = new Object[initialCapacity];
    }

    @Override
    public void add(T t) {
        push(t);
    }

    @Override
    public boolean remove(T t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean contains(T t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public T[] toArray() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Iterator<T> iterator() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
