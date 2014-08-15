package Collections;

import java.lang.reflect.Array;
import java.util.Arrays;

public final class Vector<T> {

    private T[] array;
    private final Class<T> holdingType;

    public Vector(final Vector<T> previous) {
        holdingType = previous.holdingType;
        array = Arrays.copyOf(previous.array, previous.array.length);
    }

    public Vector(final Class<T> holdingType) {
        this.holdingType = holdingType;
        this.array = arrayOfSize(0);
    }

    public void add(final T toAdd) {
        if (toAdd == null) {
            throw new NullPointerException();
        }
        array = Arrays.copyOf(array, array.length + 1);
        array[array.length - 1] = toAdd;
    }

    private T[] arrayOfSize(final int size) {
        return (T[]) Array.newInstance(holdingType, size);
    }

    public T get(final int index) {
        return array[index];
    }

    public int size() {
        return array.length;
    }
}
