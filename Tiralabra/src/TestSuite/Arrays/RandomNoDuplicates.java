/*
 * Aks copyright from the author Marko <markoma@iki.fi>.
 * Creation date: 9.8.2014 
 */
package TestSuite.Arrays;

import java.util.Random;

/**
 * produces array with random numbers from 0..n with no duplicates or missing
 * numbers.
 *
 * @author Marko <markoma@iki.fi>
 */
public class RandomNoDuplicates extends Arr {

    private final int size;
    private final Random r;

    /**
     * initializes size and java random generator
     *
     * @param size
     */
    public RandomNoDuplicates(int size) {
        this.size = size;
        this.r = new Random();
    }

    /**
     * reproduces a new array with given size. fills wih numbers 0..n, then
     * inits thru array and randomizes order.
     *
     * @return randomized array
     */
    @Override
    public int[] get() {
        int[] a = new int[size];

        for (int j = 0; j < a.length; j++) {
            a[j] = j;
        }

        for (int i = 0; i < a.length; i++) {
            int rndNmb = r.nextInt(size);
            int temp = a[i];
            a[i] = a[rndNmb];
            a[rndNmb] = temp;
        }
        return a;
    }

    /**
     *
     * @return name of array
     */
    @Override
    public String toString() {
        return "random, no-duplicates";
    }
}
