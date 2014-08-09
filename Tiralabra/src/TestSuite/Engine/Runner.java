/*
 * Aks copyright from the author Marko <markoma@iki.fi>.
 * Creation date: 8.8.2014 
 */
package TestSuite.Engine;

import TestSuite.Algos.Algo;
import TestSuite.Arrays.Arr;
import java.util.Arrays;

/**
 *
 * @author Marko <markoma@iki.fi>
 */
public class Runner {

    private final int repeat;

    public Runner(int repeat) {
        this.repeat = repeat;
    }

    public void run(Arr array, Algo algo) {

        long sum = 0;

        for (int j = 0; j < repeat; j++) {
            sum += timing(array, algo);
        }

        //TODO: print to text file
        System.out.println("sorting " + array + " array ");
        System.out.println(algo + "´s average runtime is " + (sum / repeat) + " nanoseconds");
        System.out.println("");
    }

    private static long timing(Arr array, Algo algo) {

        //System.out.println(Arrays.toString(a));
        long start = System.nanoTime();

        algo.sort(array.get());

        long result = (System.nanoTime() - start);
        //System.out.println(Arrays.toString(a));

        return result;
    }
}
