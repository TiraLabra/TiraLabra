/*
 * Aks copyright from the author Marko <markoma@iki.fi>.
 * Creation date: 2.8.2014 
 */
package TestSuite;

import TestSuite.Engine.TestFactory;

/**
 *
 * @author Marko <markoma@iki.fi>
 */
public class main {

    public static void main(String[] args) {

        TestSuite.Engine.TestFactory test1 = new TestFactory(10000);
        test1.runRandomSmallArraysTestCycleForAll();
        test1.runSortedSmallArraysTestCycleForAll();
        test1.printToFileAndClean();
    }
}
