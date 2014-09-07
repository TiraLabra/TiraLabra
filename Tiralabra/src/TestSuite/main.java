/*
 * Aks copyright from the author Marko <markoma@iki.fi>.
 * Creation date: 2.8.2014 
 */
package TestSuite;

import TestSuite.Engine.TestFactory;
import TestSuite.UI.TextUI;

/**
 *
 * @author Marko <markoma@iki.fi>
 */
public class main {

    public static void main(String[] args) {
        TestSuite.Engine.TestFactory testFactory = new TestFactory(1000);
        TextUI ui = new TextUI(testFactory);
        ui.run();
    }
}
