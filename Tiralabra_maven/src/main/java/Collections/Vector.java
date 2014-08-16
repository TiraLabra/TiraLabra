package Collections;

import java.lang.reflect.Array;
import java.util.Arrays;

public final class Vector<T> {

    private T[] array;
    private int realSize;
    private final Class<T> holdingType;

    public Vector(final Vector<T> previous) {
        holdingType = previous.holdingType;
        array = Arrays.copyOf(previous.array, previous.array.length);
        realSize = previous.realSize;
    }

    public Vector(final Class<T> holdingType) {
        this.holdingType = holdingType;
        this.array = arrayOfSize(0);
        this.realSize = 0;
    }

    public void add(final T toAdd) {
        if (toAdd == null) {
            throw new NullPointerException();
        }
        if (realSize == array.length) {
            array = Arrays.copyOf(array, array.length + 1);
            array[array.length - 1] = toAdd;
        } else {
            array[realSize - 1] = toAdd;
        }
        realSize++;
    }

    private T[] arrayOfSize(final int size) {
        return (T[]) Array.newInstance(holdingType, size);
    }

    public T get(final int index) {
        checkIndex(index);
        return array[index];
    }

    public int size() {
        return realSize;
    }

    public void switchAtIndex(final int first, final int second) {
        final T original = array[first];
        array[first] = array[second];
        array[second] = original;
    }

    public void removeLast() {
        realSize--;
    }

    public void setAtt(final int index, final T toSet) {
        checkIndex(index);
        array[index] = toSet;
    }

    private void checkIndex(final int index) {
        if (index >= realSize) {
            throw new ArrayIndexOutOfBoundsException();
        }
    }
}
