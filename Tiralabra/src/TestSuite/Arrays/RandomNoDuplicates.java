/*
 * Aks copyright from the author Marko <markoma@iki.fi>.
 * Creation date: 9.8.2014 
 */
package TestSuite.Arrays;

import java.util.Random;

/**
 *
 * @author Marko <markoma@iki.fi>
 */
public class RandomNoDuplicates extends Arr {

    private final int size;
    private final Random r;

    public RandomNoDuplicates(int size) {
        this.size = size;
        this.r = new Random();
    }

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
    @Override
    public String toString(){
        return "random, no-duplicates";
    }
}
