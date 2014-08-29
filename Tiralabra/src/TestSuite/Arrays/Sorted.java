/*
 * Aks copyright from the author Marko <markoma@iki.fi>.
 * Creation date: 9.8.2014 
 */
package TestSuite.Arrays;

/**
 * Fully sorted array (ascending order)
 *
 * @author Marko <markoma@iki.fi>
 */
public class Sorted extends Arr {

    private final int size;

    /**
     * initializes size of array
     *
     * @param size size
     */
    public Sorted(int size) {
        this.size = size;
    }

    /**
     * Fills initialized array with ascending numbering 1..n. Then Randomizes
     * 10%.
     *
     * @return array
     */
    @Override
    public int[] get() {
        int[] arr = new int[size];

        for (int i = 0; i < size; i++) {
            arr[i] = i;
        }
        return arr;
    }

    /**
     *
     * @return name of the array
     */
    @Override
    public String toString() {
        return "sorted";
    }
}
