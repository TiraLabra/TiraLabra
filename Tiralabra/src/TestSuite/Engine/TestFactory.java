/*
 * Aks copyright from the author Marko <markoma@iki.fi>.
 * Creation date: 12.8.2014 
 */
package TestSuite.Engine;

import TestSuite.Algos.*;
import TestSuite.Arrays.*;
import java.util.ArrayList;

/**
 * TestFactory will be responsible for completing the final tests. ArrayList is
 * used to store results, which are finally printed to text file using
 * FilePrinter. Implementation for four array order scenarios with small and big
 * sizes.
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
        this.small = new int[]{5, 10, 25, 50, 75, 100, 125, 150, 200, 250, 300, 400, 500};
        this.big = new int[]{500, 750, 1000, 1250, 1500, 2000, 3000, 5000, 7500, 10000};
    }

    /**
     * Runs tests for small size random arrays with all sorting algorithms.
     * Results will be added to arraylist, which is finally printed to text
     * file.
     */
    public void runRandomSmallArraysTestCycleForAll() {
        results.add("Random small arrays test. Test cycle repeated " + repeat + " times.");
        results.add(":Bubble sort:Quick sort:Insertion sort:Selection sort");

        for (int r : small) {
            results.add(r
                    + ":" + runner.run(new RandomNoDuplicates(r), new Bubblesort())
                    + ":" + runner.run(new RandomNoDuplicates(r), new Quicksort())
                    + ":" + runner.run(new RandomNoDuplicates(r), new Insertionsort())
                    + ":" + runner.run(new RandomNoDuplicates(r), new Selectionsort())
            );
        }
        System.out.println("Random small arrays tests completed.");
    }

    /**
     * Runs tests for big size random arrays with all sorting algorithms.
     * Results will be added to arraylist, which is finally printed to text
     * file.
     */
    public void runRandomBigArraysTestCycleForAll() {
        results.add("Random big arrays test. Test cycle repeated " + repeat + " times.");
        results.add(":Bubble sort:Quick sort:Insertion sort:Selection sort");

        for (int r : big) {
            results.add(r
                    + ":" + runner.run(new RandomNoDuplicates(r), new Bubblesort())
                    + ":" + runner.run(new RandomNoDuplicates(r), new Quicksort())
                    + ":" + runner.run(new RandomNoDuplicates(r), new Insertionsort())
                    + ":" + runner.run(new RandomNoDuplicates(r), new Selectionsort())
            );
        }
        System.out.println("Random big arrays tests completed.");
    }

    /**
     * Runs tests for small size sorted arrays with all sorting algorithms.
     * Results will be added to arraylist, which is finally printed to text
     * file.
     */
    public void runSortedSmallArraysTestCycleForAll() {
        results.add("Allmost sorted small arrays test. Test cycle repeated " + repeat + " times.");
        results.add(":Bubble sort:Quick sort:Insertion sort:Selection sort");

        for (int r : small) {
            results.add(r
                    + ":" + runner.run(new AllmostSorted(r), new Bubblesort())
                    + ":" + runner.run(new AllmostSorted(r), new Quicksort())
                    + ":" + runner.run(new AllmostSorted(r), new Insertionsort())
                    + ":" + runner.run(new AllmostSorted(r), new Selectionsort())
            );
        }
        System.out.println("Allmost sorted small arrays tests completed.");
    }

    /**
     * Runs tests for small size sorted arrays with all sorting algorithms.
     * Results will be added to arraylist, which is finally printed to text
     * file.
     */
    public void runSortedBigArraysTestCycleForAll() {
        results.add("Allmost sorted big arrays test. Test cycle repeated " + repeat + " times.");
        results.add(":Bubble sort:Quick sort:Insertion sort:Selection sort");

        for (int r : big) {
            results.add(r
                    + ":" + runner.run(new AllmostSorted(r), new Bubblesort())
                    + ":" + runner.run(new AllmostSorted(r), new Quicksort())
                    + ":" + runner.run(new AllmostSorted(r), new Insertionsort())
                    + ":" + runner.run(new AllmostSorted(r), new Selectionsort())
            );
        }
        System.out.println("Allmost sorted big arrays tests completed.");

    }

    /**
     * Runs tests for small size reversed arrays with all sorting algorithms.
     * Results will be added to arraylist, which is finally printed to text
     * file.
     */
    public void runReversedSmallArraysTestCycleForAll() {
        results.add("Reversed small arrays test. Test cycle repeated " + repeat + " times.");
        results.add(":Bubble sort:Quick sort:Insertion sort:Selection sort");

        for (int r : small) {
            results.add(r
                    + ":" + runner.run(new Reversed(r), new Bubblesort())
                    + ":" + runner.run(new Reversed(r), new Quicksort())
                    + ":" + runner.run(new Reversed(r), new Insertionsort())
                    + ":" + runner.run(new Reversed(r), new Selectionsort())
            );
        }
        System.out.println("Reversed small arrays tests completed.");
    }

    /**
     * Runs tests for small size reversed arrays with all sorting algorithms.
     * Results will be added to arraylist, which is finally printed to text
     * file.
     */
    public void runReversedBigArraysTestCycleForAll() {
        results.add("Reversed big arrays test. Test cycle repeated " + repeat + " times.");
        results.add(":Bubble sort:Quick sort:Insertion sort:Selection sort");

        for (int r : big) {
            results.add(r
                    + ":" + runner.run(new Reversed(r), new Bubblesort())
                    + ":" + runner.run(new Reversed(r), new Quicksort())
                    + ":" + runner.run(new Reversed(r), new Insertionsort())
                    + ":" + runner.run(new Reversed(r), new Selectionsort())
            );
        }
        System.out.println("Reversed big arrays tests completed.");

    }

    /**
     * Runs tests for small size Random few Uniques arrays with all sorting
     * algorithms. Results will be added to arraylist, which is finally printed
     * to text file.
     */
    public void runRandomFewUniquesSmallArraysTestCycleForAll() {
        results.add("Random few uniques small arrays test. Test cycle repeated " + repeat + " times.");
        results.add(":Bubble sort:Quick sort:Insertion sort:Selection sort");

        for (int r : small) {
            results.add(r
                    + ":" + runner.run(new RandomFewUnique(r), new Bubblesort())
                    + ":" + runner.run(new RandomFewUnique(r), new Quicksort())
                    + ":" + runner.run(new RandomFewUnique(r), new Insertionsort())
                    + ":" + runner.run(new RandomFewUnique(r), new Selectionsort())
            );
        }
        System.out.println("Random few uniques small arrays tests completed.");
    }

    /**
     * Runs tests for small size Random few Uniques arrays with all sorting
     * algorithms. Results will be added to arraylist, which is finally printed
     * to text file.
     */
    public void runRandomFewUniquesBigArraysTestCycleForAll() {
        results.add("Random few uniques big arrays test. Test cycle repeated " + repeat + " times.");
        results.add(":Bubble sort:Quick sort:Insertion sort:Selection sort");

        for (int r : big) {
            results.add(r
                    + ":" + runner.run(new RandomFewUnique(r), new Bubblesort())
                    + ":" + runner.run(new RandomFewUnique(r), new Quicksort())
                    + ":" + runner.run(new RandomFewUnique(r), new Insertionsort())
                    + ":" + runner.run(new RandomFewUnique(r), new Selectionsort())
            );
        }
        System.out.println("Random few uniques big arrays tests completed.");
    }

    /**
     * Result Printer. Will Clean array
     */
    public void printToFileAndClean() {
        printer.printToFile("results", results);
        results.clear();
    }

    private void printResults() {
        for (String s : results) {
            System.out.println(s);
        }
    }
}
