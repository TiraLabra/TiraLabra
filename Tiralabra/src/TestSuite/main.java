/*
 * Aks copyright from the author Marko <markoma@iki.fi>.
 * Creation date: 2.8.2014 
 */
package TestSuite;

import TestSuite.Algos.Quicksort;
import TestSuite.Engine.ArrayFactor;
import TestSuite.Engine.Runner;

/**
 *
 * @author Marko <markoma@iki.fi>
 */
public class main {

    public static void main(String[] args) {

        TestSuite.Engine.Runner runner = new Runner(100);
        
        runner.run(ArrayFactor.randomArrayNoDuplicatesMaxIsSize(1000000), new Quicksort());
    }
}
