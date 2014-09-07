/*
 * Aks copyright from the author Marko <markoma@iki.fi>.
 * Creation date: 9.8.2014 
 */
package TestSuite.Arrays;

import java.util.Random;

/**
 * produces array with random numbers from 0..4 with duplicates.
 *
 * @author Marko <markoma@iki.fi>
 */
public class RandomFewUnique extends Arr {

    private final int size;
    private final Random r;

    /**
     * initializes size and java random generator
     *
     * @param size
     */
    public RandomFewUnique(int size) {
        this.size = size;
        this.r = new Random();
    }

    /**
     * reproduces a new array with given size. fills wih random numbers 0..4.
     *
     * @return randomized array with few uniques
     */
    @Override
    public int[] get() {
        int[] a = new int[size];

        for (int j = 0; j < a.length; j++) {
            a[j] = r.nextInt(5);
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
