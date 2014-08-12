/*
 * Aks copyright from the author Marko <markoma@iki.fi>.
 * Creation date: 12.8.2014 
 */
package TestSuite.Engine;

import TestSuite.Algos.Insertionsort;
import TestSuite.Algos.Quicksort;
import TestSuite.Algos.Selectionsort;
import TestSuite.Arrays.RandomNoDuplicates;
import TestSuite.Arrays.Sorted;
import java.util.ArrayList;

/**
 * TestFactory will be responsible for completing the final tests ArrayList is
 * used to store results
 *
 * @author Marko <markoma@iki.fi>
 */
public class TestFactory {

    private final Runner runner;
    private final ArrayList<String> results;
    private final int repeat;
    private final int[] small;
    private final int[] big;
    private final FilePrinter printer;

    public TestFactory(int repeat) {
        this.runner = new Runner(repeat);
        this.printer = new FilePrinter();
        this.repeat = repeat;
        this.results = new ArrayList<String>();
        this.small = new int[]{2, 5, 10, 25, 50, 75, 100, 250, 500};
        this.big = new int[]{500, 750, 1000, 1250, 1500, 2000, 3000, 5000, 7500, 10000};
    }

    /**
     * Runs tests for small size random arrays with all sorting algorithms.
     * Results will be added to arraylist, which is finally printed to text
     * file.
     */
    public void runRandomSmallArraysTestCycleForAll() {
        results.add("Random small arrays test. Test cycle repeted " + repeat + " times.");
        results.add(":Quick sort:Insertion sort:Selection sort");

        for (int r : small) {
            results.add(r
                    + ":" + runner.run(new RandomNoDuplicates(r), new Quicksort())
                    + ":" + runner.run(new RandomNoDuplicates(r), new Insertionsort())
                    + ":" + runner.run(new RandomNoDuplicates(r), new Selectionsort())
            );
        }
        System.out.println("Random small arrays tests succefully completed.");
        printer.printToFile("results", results);
    }

    /**
     * Runs tests for big size random arrays with all sorting algorithms.
     * Results will be added to arraylist, which is finally printed to text
     * file.
     */
    public void runRandomBigArraysTestCycleForAll() {
        results.add("Random big arrays test. Test cycle repeted " + repeat + " times.");
        results.add(":Quick sort:Insertion sort:Selection sort");

        for (int r : big) {
            results.add(r
                    + ":" + runner.run(new RandomNoDuplicates(r), new Quicksort())
                    + ":" + runner.run(new RandomNoDuplicates(r), new Insertionsort())
                    + ":" + runner.run(new RandomNoDuplicates(r), new Selectionsort())
            );
        }
        System.out.println("Random big arrays tests succefully completed.");
        printer.printToFile("results", results);
    }

    /**
     * Runs tests for small size sorted arrays with all sorting algorithms.
     * Results will be added to arraylist, which is finally printed to text
     * file.
     */
    public void runSortedSmallArraysTestCycleForAll() {
        results.add("Sorted small arrays test. Test cycle repeted " + repeat + " times.");
        results.add(":Quick sort:Insertion sort:Selection sort");

        for (int r : small) {
            results.add(r
                    + ":" + runner.run(new Sorted(r), new Quicksort())
                    + ":" + runner.run(new Sorted(r), new Insertionsort())
                    + ":" + runner.run(new Sorted(r), new Selectionsort())
            );
        }
        System.out.println("Sorted small arrays tests succefully completed.");
        printer.printToFile("results", results);
    }
        /**
     * Runs tests for small size sorted arrays with all sorting algorithms.
     * Results will be added to arraylist, which is finally printed to text
     * file.
     */
    public void runSortedBigArraysTestCycleForAll() {
        results.add("Sorted big arrays test. Test cycle repeted " + repeat + " times.");
        results.add(":Quick sort:Insertion sort:Selection sort");

        for (int r : big) {
            results.add(r
                    + ":" + runner.run(new Sorted(r), new Quicksort())
                    + ":" + runner.run(new Sorted(r), new Insertionsort())
                    + ":" + runner.run(new Sorted(r), new Selectionsort())
            );
        }
        System.out.println("Sorted big arrays tests succefully completed.");
        printer.printToFile("results", results);
    }

    public void printResults() {
        for (String s : results) {
            System.out.println(s);
        }
    }
}
