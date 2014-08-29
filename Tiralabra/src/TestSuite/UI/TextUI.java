/*
 * Aks copyright from the author Marko <markoma@iki.fi>.
 * Creation date: 20.8.2014 
 */
package TestSuite.UI;

import TestSuite.Engine.TestFactory;
import java.util.Scanner;

/**
 * Text ui
 *
 * @author Marko <markoma@iki.fi>
 */
public class TextUI {

    private final Scanner reader;

    public TextUI() {
        this.reader = new Scanner(System.in);
    }

    /**
     * Main runner for UI.
     */
    public void run() {
        System.out.println("Welcome to Sorting array speed test!\n");

        // Main menu
        while (true) {
            printOptions();
            String result = reader.nextLine();

            if (result.equals("1")) {
                runSmallArrayTestSequence();
            } else if (result.equals("2")) {
                runBigArrayTestSequence();
            } else if (result.equals("x")) {
                System.exit(0);
            }
        }
    }

    /**
     * Prints options for main menu.
     */
    private void printOptions() {
        System.out.println("Choose your tests: ");
        System.out.println("(1) Run all small array tests");
        System.out.println("(2) Run all big array tests (may take long time)");
        System.out.println("(x) Exits");
        System.out.print("> ");
    }

    /**
     * Runs all small array tests
     */
    private void runSmallArrayTestSequence() {
        TestSuite.Engine.TestFactory test1 = new TestFactory(askRepeat(10000));

        System.out.println("Starting small array test sequence...");
        test1.runRandomSmallArraysTestCycleForAll();
        test1.runRandomFewUniquesSmallArraysTestCycleForAll();
        test1.runAllmostSortedSmallArraysTestCycleForAll();
        test1.runSortedSmallArraysTestCycleForAll();
        test1.runReversedSmallArraysTestCycleForAll();

        System.out.println("All tests completed. Printing to file 'SmallArrayTests.csv'");
        test1.printToFileAndClean("SmallArrayTests");
    }

    /**
     * Runs all big array tests
     */
    private void runBigArrayTestSequence() {
        TestSuite.Engine.TestFactory test2 = new TestFactory(askRepeat(1000));

        System.out.println("Starting big array test sequence...");
        test2.runRandomBigArraysTestCycleForAll();
        test2.runRandomFewUniquesBigArraysTestCycleForAll();
        test2.runAllmostSortedBigArraysTestCycleForAll();
        test2.runSortedBigArraysTestCycleForAll();
        test2.runReversedBigArraysTestCycleForAll();

        System.out.println("All tests completed. Printing to file 'BigArrayTest.csv'");
        test2.printToFileAndClean("BigArrayTest");
    }

    /**
     * Setter for test repeation times
     *
     * @param preset presetted value
     * @return return either preset or new value.
     */
    private int askRepeat(int preset) {
        System.out.println("Tests will run " + preset + " times.");

        System.out.println(
                "Recommended value for small arrays 1 - 100000. \n "
                + "Recommended for big arrays 1 - 1000 (will take \n"
                + "up to 60min with 1000 repeats).\n"
        );

        System.out.print("Set new value (give new value) or keep preset value (enter): ");

        while (true) {
            String result = reader.nextLine();
            if (result.equals("")) {
                System.out.println("Keeping preset value.");
                return preset;
            } else {
                try {
                    int val = Integer.parseInt(result);
                    System.out.println("Setting new value to: " + val);
                    return val;
                } catch (NumberFormatException ex) {
                    System.out.println("not numeric or empty");
                }
            }
        }
    }
}
