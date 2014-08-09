/*
 * Aks copyright from the author Marko <markoma@iki.fi>.
 * Creation date: 9.8.2014 
 */
package TestSuite.Arrays;

/**
 *
 * @author Marko <markoma@iki.fi>
 */
public class Reversed extends Arr {

    private final int size;

    public Reversed(int size) {
        this.size = size;
    }

    @Override
    public int[] get() {
        int[] arr = new int[size];

        for (int j = size - 1, i = 0; j >= 0; j--, i++) {
            arr[j] = i;
        }
        return arr;
    }

    @Override
    public String toString() {
        return "Reversed";
    }
}
