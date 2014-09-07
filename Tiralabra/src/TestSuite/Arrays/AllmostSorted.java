/*
 * Aks copyright from the author Marko <markoma@iki.fi>.
 * Creation date: 9.8.2014 
 */
package TestSuite.Arrays;

import java.util.Random;

/**
 * Allmost sorted array (ascending order)
 *
 * @author Marko <markoma@iki.fi>
 */
public class AllmostSorted extends Arr {

    private final int size;

    /**
     * initializes size of array
     *
     * @param size size
     */
    public AllmostSorted(int size) {
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
        randomizeTenPercent(arr);
        return arr;
    }
    /**
     * Randomizer
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
        return "allmost sorted";
    }
}
