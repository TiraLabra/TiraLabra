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

        TestSuite.Engine.TestFactory test1 = new TestFactory(10);
        test1.runRandomSmallArraysTestCycleForAll();

        /*TestSuite.Engine.TestFactory test2 = new TestFactory(1000);
         test2.runRandomBigArraysTestCycleForAll();*/
    }
}
