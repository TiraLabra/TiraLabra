/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package HikeTests.Suite;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 *
 * @author petri
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({HikeTests.DijkstraTest.class, HikeTests.LinkyListTests.class, HikeTests.MinHeapTests.class, HikeTests.ImageTableTests.class })
public class HikeTestSuite {

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }
    
}