/*
 * Aks copyright from the author Marko <markoma@iki.fi>.
 * Creation date: 2.8.2014 
 */
package TestSuite;

import TestSuite.Algos.Insertionsort;
import TestSuite.Algos.Quicksort;
import TestSuite.Algos.Selectionsort;
import TestSuite.Arrays.RandomNoDuplicates;
import TestSuite.Arrays.Reversed;
import TestSuite.Arrays.Sorted;
import TestSuite.Engine.Runner;

/**
 *
 * @author Marko <markoma@iki.fi>
 */
public class main {

    public static void main(String[] args) {

        TestSuite.Engine.Runner runner = new Runner(1000);

        int size = 10;

//        runner.run(new Sorted(size), new Quicksort());
//        runner.run(new Reversed(size), new Quicksort());
        runner.run(new RandomNoDuplicates(size), new Quicksort());
//
//        runner.run(new Sorted(size), new Insertionsort());
//        runner.run(new Reversed(size), new Insertionsort());
        runner.run(new RandomNoDuplicates(size), new Insertionsort());
//        
        //   runner.run(new Sorted(size), new Selectionsort());
        //   runner.run(new Reversed(size), new Selectionsort());
        runner.run(new RandomNoDuplicates(size), new Selectionsort());

    }
}
