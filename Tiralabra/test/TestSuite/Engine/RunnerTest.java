/*
 * Aks copyright from the author Marko <markoma@iki.fi>.
 * Creation date: 29.8.2014 
 */
package TestSuite.Engine;

import TestSuite.Algos.Algo;
import TestSuite.Algos.AlgoImpl;
import TestSuite.Algos.AlgoTest;
import TestSuite.Arrays.ArrImpl;
import TestSuite.Arrays.ArrTest;
import TestSuite.Arrays.Arraymaker;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Marko <markoma@iki.fi>
 */
public class RunnerTest {

    Runner runner;
    Algo testAlgo;

    @Before
    public void setUp() {
        runner = new Runner(100);
        testAlgo = new AlgoImpl();
    }

    /**
     * Test of timing method, of class Runner.
     */
    @Test
    public void testTiming() {
        long time = runner.timing(new ArrImpl(), testAlgo);
        assertTrue(time < 500000);
        assertTrue(time > 100);
    }
}
