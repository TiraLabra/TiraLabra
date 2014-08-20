/*
 * Aks copyright from the author Marko <markoma@iki.fi>.
 * Creation date: 8.8.2014 
 */
package TestSuite.Engine;

import TestSuite.Algos.Algo;
import TestSuite.Arrays.Arr;
import java.util.Arrays;

/**
 * Algorithm test engine
 *
 * @author Marko <markoma@iki.fi>
 */
public class Runner {

    private final int repeat;

    /**
     *
     * @param repeat repeation series
     */
    public Runner(int repeat) {
        this.repeat = repeat;
    }

    /**
     * main method to run tests
     *
     * @param array Abstract array to sort
     * @param algo Abstract algorithm to sort with
     * @return result in nanoseconds
     */
    public long run(Arr array, Algo algo) {

        long sum = 0;

        for (int j = 0; j < repeat; j++) {
            sum += timing(array, algo);
        }

        //TODO print to text file
        /*
        System.out.println("sorting " + array + " array ");
        System.out.println(algo + "´s average runtime is " + (sum / repeat) + " nanoseconds");
        System.out.println("");
        */
        return sum / repeat;
    }

    /**
     * Timer method uses System.nanotime() for timing sorting algorithm Each run
     * is made 'clean' as array is reproduced before each timing.
     *
     * @param array Abstract array to sort
     * @param algo Abstract sorting algorithm
     * @return (long) result in nanoseconds
     */
    private static long timing(Arr array, Algo algo) {

        // reproduce of an array
        int[] a = array.get();
        System.out.println(Arrays.toString(a));

        long start = System.nanoTime();

        algo.sort(a);

        long end = System.nanoTime();
        System.out.println(Arrays.toString(a));

        return end - start;
    }
}
