/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sorters;

import java.util.Arrays;
import junit.framework.TestCase;

/**
 *
 * @author Admin
 */
public class GlobalMethodsTest extends TestCase {

    public GlobalMethodsTest(String testName) {
        super(testName);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public void testaaSimppeliExchange() {
        int[] testiTaulukko = {1, 2, 3, 4, 5};
        int[] tulosTaulukko = {5, 2, 3, 4, 1};
        GlobalMethods.exchange(testiTaulukko, 0, 4);
        assertTrue(Arrays.equals(tulosTaulukko, testiTaulukko));

    }

    public void testaaMonimutkainenExchange() {
        int[] testiTaulukko = {1, 2, 3, 4, 5};
        int[] tulosTaulukko = {3, 5, 1, 2, 4};
        GlobalMethods.exchange(testiTaulukko, 0, 2);
        GlobalMethods.exchange(testiTaulukko, 1, 4);
        GlobalMethods.exchange(testiTaulukko, 3, 4);
        assertTrue(Arrays.equals(tulosTaulukko, testiTaulukko));
    }

}
