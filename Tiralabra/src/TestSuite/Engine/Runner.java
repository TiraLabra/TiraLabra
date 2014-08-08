/*
 * Aks copyright from the author Marko <markoma@iki.fi>.
 * Creation date: 8.8.2014 
 */
package TestSuite.Engine;

import TestSuite.Algos.Algo;
import java.util.Arrays;
import java.util.Random;

/**
 *
 * @author Marko <markoma@iki.fi>
 */
public class Runner implements CommandInterface {

    private final int repeat;

    public Runner(int repeat) {
        this.repeat = repeat;
    }

    @Override
    public void run(int[] array, Algo algo) {

        long sum = 0;

        for (int j = 0; j < repeat; j++) {

            sum += timing(array, algo);
        }

        //TODO: print to text file
        System.out.println(algo + "´s average runtime is " + (sum / repeat) + " milliseconds");
    }

    private static long timing(int[] a, Algo algo) {

        //System.out.println(Arrays.toString(a));

        long start = System.currentTimeMillis();

        algo.sort(a);

        long result = (System.currentTimeMillis() - start);
        //System.out.println(Arrays.toString(a));

        return result;
    }
}
