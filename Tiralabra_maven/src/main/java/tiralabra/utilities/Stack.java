/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tiralabra.utilities;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

/**
 * A generic Stack to hold objects in.
 *
 * @author atte
 * @param <E> Object
 */
public class Stack<E> {

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
    private int initialCapacity;

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
     * @param e
     */
    public void push(E e) {
        if (size == stack.length) {
            growCapacity();
        }

        stack[size] = e;
        size++;
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

        return (E) stack[size - 1];
    }

    /**
     * Whether the stack is empty.
     *
     * @return
     */
    public boolean isEmpty() {
        return size == 0;
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
        E e = (E) stack[size];
        stack[size] = null;
        return e;
    }

    /**
     * Returns the size of the stack (or how many objects are currently stored
     * there).
     *
     * @return size
     */
    public int size() {
        return size;
    }

    /**
     * Grows the size of the stack to double of the original value.
     */
    private void growCapacity() {
        int newSize = size() * 2;
        stack = Arrays.copyOf(stack, newSize);
    }

    private void clear() {
        this.stack = new Object[initialCapacity];
    }
}
