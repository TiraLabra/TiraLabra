/*
 * Aks copyright from the author Marko <markoma@iki.fi>.
 * Creation date: 2.8.2014 
 */
package TestSuite;

import TestSuite.Algos.Insertionsort;
import TestSuite.Algos.Quicksort;
import TestSuite.Engine.ArrayFactory;
import TestSuite.Engine.Runner;

/**
 *
 * @author Marko <markoma@iki.fi>
 */
public class main {

    public static void main(String[] args) {

        TestSuite.Engine.Runner runner = new Runner(1000);

        runner.run(ArrayFactory.sorted(1000000), new Quicksort());
        runner.run(ArrayFactory.reversed(1000000), new Quicksort());
        runner.run(ArrayFactory.randomArrayNoDuplicatesMaxIsSize(1000000), new Quicksort());
        
        runner.run(ArrayFactory.sorted(100000), new Insertionsort());
        runner.run(ArrayFactory.reversed(100000), new Insertionsort());
        runner.run(ArrayFactory.randomArrayNoDuplicatesMaxIsSize(100000), new Insertionsort());

    }
}
