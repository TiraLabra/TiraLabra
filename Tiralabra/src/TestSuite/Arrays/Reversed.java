/*
 * Aks copyright from the author Marko <markoma@iki.fi>.
 * Creation date: 9.8.2014 
 */
package TestSuite.Arrays;

/**
 * Full reversed (descending) order array
 *
 * @author Marko <markoma@iki.fi>
 */
public class Reversed extends Arr {

    private final int size;

    /**
     * initializes size of array
     *
     * @param size size
     */
    public Reversed(int size) {
        this.size = size;
    }

    /**
     * fills array from highs column to lowest with ascending numbering 1..n.
     *
     * @return array
     */
    @Override
    public int[] get() {
        int[] arr = new int[size];

        for (int j = size - 1, i = 0; j >= 0; j--, i++) {
            arr[j] = i;
        }
        return arr;
    }

    /**
     *
     * @return name of the array
     */
    @Override
    public String toString() {
        return "reversed";
    }
}
