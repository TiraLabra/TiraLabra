/*
 * Aks copyright from the author Marko <markoma@iki.fi>.
 * Creation date: 9.8.2014 
 */
package TestSuite.Arrays;

import java.util.Random;

/**
 * Full reversed (descending) order array
 *
 * @author Marko <markoma@iki.fi>
 */
public class AllmostReversed extends Arr {

    private final int size;

    /**
     * initializes size of array
     *
     * @param size size
     */
    public AllmostReversed(int size) {
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
        randomizeTenPercent(arr);
        return arr;
    }

    /**
     * Array randomizer
     *
     * @param arr array to randomize
     */
    private void randomizeTenPercent(int[] arr) {
        Random r = new Random();
        for (int i = 0; i < size / 10; i++) {
            int rndNmb1 = r.nextInt(size);
            int rndNmb2 = r.nextInt(size);
            int temp = arr[rndNmb1];
            arr[rndNmb1] = arr[rndNmb2];
            arr[rndNmb2] = temp;
        }
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
