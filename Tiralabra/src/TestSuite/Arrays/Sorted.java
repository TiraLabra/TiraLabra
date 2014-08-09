/*
 * Aks copyright from the author Marko <markoma@iki.fi>.
 * Creation date: 9.8.2014 
 */
package TestSuite.Arrays;

/**
 *
 * @author Marko <markoma@iki.fi>
 */
public class Sorted extends Arr {

    private final int size;

    public Sorted(int size) {
        this.size = size;
    }

    @Override
    public int[] get() {
        int[] arr = new int[size];

        for (int i = 0; i < size; i++) {
            arr[i] = i;
        }

        return arr;
    }

    @Override
    public String toString() {
        return "Sorted";
    }
}
