package Collections;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * A resizable array. Linear time add operation.
 *
 * @param <T> The vector member type.
 */
public final class Vector<T> {

    private T[] array;
    private int realSize;
    private final Class<T> holdingType;

    /**
     * Create a new vector and copies members from an old one.
     *
     * @param previous The old vector.
     */
    public Vector(final Vector<T> previous) {
        holdingType = previous.holdingType;
        array = Arrays.copyOf(previous.array, previous.array.length);
        realSize = previous.realSize;
    }

    /**
     * Create a new empty vector with default starting size.
     *
     * @param holdingType The Vector member type.
     */
    public Vector(final Class<T> holdingType) {
        this(holdingType, 0);
    }

    /**
     * Creates a new vector with specific starting size.
     *
     * @param holdingType The Vector member type.
     * @param startingSize The starting size.
     */
    public Vector(final Class<T> holdingType, final int startingSize) {
        this.holdingType = holdingType;
        this.array = arrayOfSize(startingSize);
        this.realSize = array.length;
    }

    /**
     * Adds a new item to the end of the vector.
     *
     * @param toAdd The new item to be added.
     */
    public void add(final T toAdd) {
        if (toAdd == null) {
            throw new NullPointerException();
        }
        if (realSize == array.length) {
            array = Arrays.copyOf(array, array.length + 1);
            array[array.length - 1] = toAdd;
            realSize = array.length;
        } else {
            array[realSize] = toAdd;
            realSize++;
        }
    }

    private T[] arrayOfSize(final int size) {
        return (T[]) Array.newInstance(holdingType, size);
    }

    /**
     * Returns item from the vector in the index.
     *
     * @param index The vector index.
     * @return The item.
     */
    public T get(final int index) {
        checkIndex(index);
        return array[index];
    }

    /**
     * Size of the vector.
     *
     * @return Size of the vector.
     */
    public int size() {
        return realSize;
    }

    /**
     * Switches positions of two vector members.
     *
     * @param first Index of the first member.
     * @param second Index of the second member.
     */
    public void switchAtIndex(final int first, final int second) {
        final T original = array[first];
        array[first] = array[second];
        array[second] = original;
    }

    /**
     * Removes last member from the vector.
     */
    public void removeLast() {
        realSize--;
    }

    /**
     * Set member at vectors index to given item.
     *
     * @param index The position.
     * @param toSet The item to set.
     */
    public void setAtt(final int index, final T toSet) {
        checkIndex(index);
        array[index] = toSet;
    }

    private void checkIndex(final int index) {
        if (index >= realSize) {
            throw new ArrayIndexOutOfBoundsException();
        }
    }

    /**
     * Copies the vector into an array.
     *
     * @return The array.
     */
    public T[] toArray() {
        return Arrays.copyOf(array, array.length);
    }
}
