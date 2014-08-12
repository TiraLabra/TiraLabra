/*
 * Aks copyright from the author Marko <markoma@iki.fi>.
 * Creation date: 12.8.2014 
 */
package TestSuite.Engine;

import TestSuite.Algos.Insertionsort;
import TestSuite.Algos.Quicksort;
import TestSuite.Algos.Selectionsort;
import TestSuite.Arrays.RandomNoDuplicates;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * TestFactory will be responsible for completing the final tests
 *
 * @author Marko <markoma@iki.fi>
 */
public class TestFactory {

    private final Runner runner;
    private final ArrayList<String> results;
    private final int repeat;
    private final int[] small;
    private final int[] big;

    public TestFactory(int repeat) {
        this.runner = new Runner(repeat);
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
        printToFile("results");
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
        printToFile("results");
    }

    public void printResults() {
        for (String s : results) {
            System.out.println(s);
        }
    }

    /**
     * File writer. If file exists, results will be added to the end of the
     * file.
     *
     * @param fileName filename as parameter
     */
    public void printToFile(String fileName) {
        
        boolean newfile = true;
        
        try {

            File f = new File(fileName + ".csv");
            if (f.exists() && !f.isDirectory()) {
                System.out.println("Existing file found. ");
                System.out.print("Overwrite file?(y/n/enter will add res to the end)");
                String s = new Scanner(System.in).nextLine();
                if (s.equals("y")) {
                    newfile = false;
                } else if (s.equals("n")) {
                    return;
                }
            }
            PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(f, newfile)));

            for (String result : results) {
                writer.println(result);
            }

            writer.close();

        } catch (FileNotFoundException ex) {
            Logger.getLogger(TestFactory.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(TestFactory.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(TestFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
