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

    private static Scanner reader;
    private final TestFactory testFactory;

    public TextUI(TestFactory testFactory) {
        this.testFactory = testFactory;
        TextUI.reader = new Scanner(System.in);
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
        
        testFactory.setRepeat(askRepeat(testFactory.getRepeat()));

        System.out.println("Starting small array test sequence...");
        testFactory.runRandomSmallArraysTestCycleForAll();
        testFactory.runRandomFewUniquesSmallArraysTestCycleForAll();
        testFactory.runAllmostSortedSmallArraysTestCycleForAll();
        testFactory.runSortedSmallArraysTestCycleForAll();
        testFactory.runReversedSmallArraysTestCycleForAll();

        System.out.println("All tests completed. Printing to file 'SmallArrayTests.csv'");
        testFactory.printToFileAndClean("SmallArrayTests");
    }

    /**
     * Runs all big array tests
     */
    private void runBigArrayTestSequence() {
        
        testFactory.setRepeat(askRepeat(testFactory.getRepeat()));

        System.out.println("Starting big array test sequence...");
        testFactory.runRandomBigArraysTestCycleForAll();
        testFactory.runRandomFewUniquesBigArraysTestCycleForAll();
        testFactory.runAllmostSortedBigArraysTestCycleForAll();
        testFactory.runSortedBigArraysTestCycleForAll();
        testFactory.runReversedBigArraysTestCycleForAll();

        System.out.println("All tests completed. Printing to file 'BigArrayTest.csv'");
        testFactory.printToFileAndClean("BigArrayTest");
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
    /**
     * if (f.exists() && !f.isDirectory()) will ask for overwrite
     * @return if yes returns true
     */
    public static boolean existingFile() {
        System.out.println("Existing file found. ");
        System.out.print("Overwrite file?(y/enter will add to the end)");
        return !reader.nextLine().equals("y");
    }
    /**
     * Print successfull message
     * @param fileName 
     */
    public static void printSuccessfull(String fileName) {
        System.out.println("Printed to file: " + fileName + ".csv\n");
    }
}
